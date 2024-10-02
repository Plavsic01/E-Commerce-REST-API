package com.plavsic.ecommerce.repository.user;

import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

}
