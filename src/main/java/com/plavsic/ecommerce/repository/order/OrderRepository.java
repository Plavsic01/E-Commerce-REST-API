package com.plavsic.ecommerce.repository.order;

import com.plavsic.ecommerce.model.order.Order;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order> {
}
