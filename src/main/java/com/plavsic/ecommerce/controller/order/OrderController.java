package com.plavsic.ecommerce.controller.order;

import com.plavsic.ecommerce.generic.controller.GenericController;
import com.plavsic.ecommerce.dto.order.OrderDTO;
import com.plavsic.ecommerce.model.order.Order;
import com.plavsic.ecommerce.generic.service.AbstractService;
import com.plavsic.ecommerce.service.order.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends GenericController<OrderDTO, Order> {

    private final OrderServiceImpl orderService;
    private final HttpSession httpSession;

    @Autowired
    public OrderController(
            AbstractService<OrderDTO,Order> service,
            OrderServiceImpl orderService,
            HttpSession httpSession) {
        super(service);
        this.orderService = orderService;
        this.httpSession = httpSession;
    }

    @Override
    public ResponseEntity<OrderDTO> create(OrderDTO entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        OrderDTO obj = orderService.createOrder(entity,httpSession);
        if(obj != null) {
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
