package com.oms.orderservice.repo;

import com.oms.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepo extends JpaRepository<Order, Integer> {

    @Query(value = "select * from orderservice where id = :id", nativeQuery = true)
    public Order findByOrderID(@Param("id") int id);
}
