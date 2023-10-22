package com.example.coffeeshop.repository;


import com.example.coffeeshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByOrderByPriceDesc();

    @Query("SELECT o FROM Order o JOIN o.employee u WHERE u.id <> :employeeId")
    Set<Order> findAllByEmployee_IdNot(@Param("employeeId") Long employeeId);
}
