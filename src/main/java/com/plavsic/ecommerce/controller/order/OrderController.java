package com.plavsic.ecommerce.controller.order;

import com.plavsic.ecommerce.generic.controller.GenericController;
import com.plavsic.ecommerce.dto.order.OrderDTO;
import com.plavsic.ecommerce.model.order.Order;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends GenericController<OrderDTO, Order> {

    @Autowired
    public OrderController(AbstractService<OrderDTO, Order> service) {
        super(service);
    }
}
