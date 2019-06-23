package com.lambdaschool.tripsplit.repository;

import com.lambdaschool.tripsplit.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
