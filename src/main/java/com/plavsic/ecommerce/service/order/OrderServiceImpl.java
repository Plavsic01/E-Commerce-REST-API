package com.plavsic.ecommerce.service.order;

import com.plavsic.ecommerce.dto.order.OrderDTO;
import com.plavsic.ecommerce.model.cart.Cart;
import com.plavsic.ecommerce.model.order.Order;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.generic.service.AbstractService;
import com.plavsic.ecommerce.model.order.OrderItem;
import com.plavsic.ecommerce.model.order.Status;
import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.repository.book.BookRepository;
import com.plavsic.ecommerce.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class OrderServiceImpl extends AbstractService<OrderDTO, Order> {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper,
                            GenericRepository<Order> repository,
                            UserRepository userRepository,
                            BookRepository bookRepository) {
        super(modelMapper, repository, OrderDTO.class, Order.class);
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO, HttpSession session) {
        orderDTO.setId(null);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            return null;
        }
        User user = userRepository.findByUsername(cart.getUsername()).orElse(null);

        if(user == null) {
            return null;
        }

        Order order = new Order();
        order.setAddress(orderDTO.getAddress());
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.IN_PROGRESS);
        cart.getCartItems().forEach(cartItem -> {
            bookRepository.findById(cartItem.getBook().getId()).ifPresent(book -> {
                if(book.getQuantity() >= cartItem.getQuantity()) {
                    book.setQuantity(book.getQuantity() - cartItem.getQuantity());
                    book = bookRepository.save(book);
                    order.getItems().add(new OrderItem(null,book,
                            cartItem.getQuantity()));
                }
            });
        });
        if(cart.getCartItems().size() != order.getItems().size()) {
            return null;
        }
        repository.save(order);
        session.removeAttribute("cart");
        return modelMapper.map(order, OrderDTO.class);
    }

}
