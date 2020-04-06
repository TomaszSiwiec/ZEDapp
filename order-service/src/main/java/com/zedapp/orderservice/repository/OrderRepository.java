package com.zedapp.orderservice.repository;

import com.zedapp.orderservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
