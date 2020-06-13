package com.example.sepm.sepm.Model;

import java.util.List;
import java.util.Optional;

import com.example.sepm.sepm.Model.data.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserid(Integer userid);

    Optional<Order> findById(Integer id);    

    @Query("SELECT o FROM Order o WHERE MONTH(date) = ?1")
    List<Order> findByMonth(Integer month);
}