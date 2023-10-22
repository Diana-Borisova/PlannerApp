package com.example.coffeeshop.service;


import com.example.coffeeshop.model.dto.AddOrderDto;
import com.example.coffeeshop.model.dto.OrderDto;
import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    private final CategoryRepository categoryRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.orderRepository = orderRepository;

        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }




    public boolean create(AddOrderDto addOrderDto) {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        Category category = this.categoryRepository.findByName(addOrderDto.getCategoryEnum());
        Order order = new Order();
        order.setDescription(addOrderDto.getDescription());
        order.setOrderTime(addOrderDto.getOrderTime());
        order.setCategory(category);
        order.setEmployee(user);
        order.setName(addOrderDto.getName());
        order.setPrice(addOrderDto.getPrice());

        this.orderRepository.save(order);


        return true;
    }

    public List<Order> getAllOrders() {

        return  this.orderRepository.findAll().stream().collect(Collectors.toList());

    }



    public List<OrderDto> getAllSorted() {
        return this.orderRepository.getAllByOrderByPriceDesc().stream().map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }

    public void readyOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
//
//    public Set<Ship> getOtherShips() {
//        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
//        return this.shipRepository.findAllByUserIdNot(user.getId());
//    }
//
//    public void attack(BattleDto battleDto) throws Exception {
//        Optional<Ship> attacker = this.shipRepository.findById((long)battleDto.getAttackerId());
//        Optional<Ship> defender = this.shipRepository.findById((long)battleDto.getDefenderId());
//
//        if (attacker.isEmpty() || defender.isEmpty()) {
//            throw new Exception();
//        }
//
//
//        long newDefenderHealth = defender.get().getHealth() - attacker.get().getPower();
//
//        if (newDefenderHealth <= 0) {
//            this.shipRepository.delete(defender.get());
//        } else {
//            defender.get().setHealth(newDefenderHealth);
//            this.shipRepository.save(defender.get());
//        }
//    }
//
//    public List<Ship> getSortedShips() {
//     List<Ship> allShips = this.shipRepository.findAll();
//
//        return allShips.stream()
//                .sorted(Comparator.comparingLong(Ship::getHealth).reversed())
//                .collect(Collectors.toCollection(ArrayList::new));
//    }


}
