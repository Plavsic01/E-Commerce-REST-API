package com.plavsic.ecommerce.repository.user;

import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.model.user.Role;

import java.util.Optional;

public interface RoleRepository extends GenericRepository<Role> {
    Optional<Role> findByName(String name);
}
