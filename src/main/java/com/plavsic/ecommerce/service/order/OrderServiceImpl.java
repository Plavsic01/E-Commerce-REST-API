package com.plavsic.ecommerce.service.order;

import com.plavsic.ecommerce.dto.order.OrderDTO;
import com.plavsic.ecommerce.model.order.Order;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends AbstractService<OrderDTO, Order> {

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper, GenericRepository<Order> repository) {
        super(modelMapper, repository, OrderDTO.class, Order.class);
    }
}
