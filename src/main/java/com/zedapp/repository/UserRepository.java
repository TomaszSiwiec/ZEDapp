package com.zedapp.repository;

import com.zedapp.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {
}
