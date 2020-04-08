package com.zedapp.orderservice.repository;

import com.zedapp.orderservice.domain.Order;
import com.zedapp.orderservice.exception.ObjectNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    default Order findOrThrow(String id) throws ObjectNotFoundException {
        Order company = findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[ZEDAPP] Order with id = " + id + " not found"));
        return company;
    }

    Optional<Order> findByInternalId(String internalId);

    default Order findByInternalIdOrThrow(String internalId) throws ObjectNotFoundException {
        Order company = findByInternalId(internalId)
                .orElseThrow(() -> new ObjectNotFoundException("[ZEDAPP] Order with " +
                        "internalId = " + internalId + " not found"));
        return company;
    }
}
