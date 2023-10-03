package com.coffeeshop.service;

import com.coffeeshop.helper.LoggedUser;
import com.coffeeshop.model.dto.AddOrderDto;
import com.coffeeshop.model.dto.OrderDto;
import com.coffeeshop.model.entity.Order;
import com.coffeeshop.model.entity.User;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final CategoryService categoryService;
    private final OrderRepository orderRepository;
    private final LoggedUser loggedUser;

    private final UserRepository userRepository;

    public OrderService(CategoryService categoryService, OrderRepository orderRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.orderRepository = orderRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(AddOrderDto addOrderDto) {
        Optional<User> user = this.userRepository.findById(loggedUser.getId());
        if(user.isEmpty()){
            return false;
        }
        Order order = new Order();
        order.setName(addOrderDto.getName());
        order.setOrderTime(addOrderDto.getOrderTime());
        order.setDescription(addOrderDto.getDescription());
        order.setPrice(addOrderDto.getPrice());
        order.setCategory(this.categoryService.findByName(addOrderDto.getCategory()));
        order.setEmployee(user.get());
        this.orderRepository.save(order);
        return true;
    }

    public List<OrderDto> getAllSorted() {
        return this.orderRepository.getAllByOrderByPriceDesc()
                .stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }



    public void readyOrder(Long id) {

        this.orderRepository.deleteById(id);
    }


}
