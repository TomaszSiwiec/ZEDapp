package com.zedapp.purchaserservice.repository;

import com.zedapp.purchaserservice.domain.Purchaser;
import com.zedapp.purchaserservice.exception.ObjectNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaserRepository extends MongoRepository<Purchaser, String> {
    default Purchaser findOrThrow(String id) throws ObjectNotFoundException {
        Purchaser purchaser = findById(id).orElseThrow(() -> new ObjectNotFoundException("[ZEDAPP] Purchaser with _id = " + id + " not found"));
        return purchaser;
    }

    List<Purchaser> findByName(String name);

    List<Purchaser> findByLastname(String lastName);

    List<Purchaser> findByTelNumber(String telNumber);
}
