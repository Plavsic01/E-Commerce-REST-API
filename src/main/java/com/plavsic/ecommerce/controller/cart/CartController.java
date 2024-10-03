package com.plavsic.ecommerce.controller.cart;

import com.plavsic.ecommerce.model.book.Book;
import com.plavsic.ecommerce.model.cart.Cart;
import com.plavsic.ecommerce.model.cart.CartItem;
import com.plavsic.ecommerce.service.cart.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.toString();
    }


    @PostMapping
    public void addToCart(@RequestParam("bookId") Long bookId,
                            @RequestParam("quantity") int quantity,
                            @AuthenticationPrincipal UserDetails userDetails,
                            HttpSession session) {

        cartService.addToCart(session, bookId, quantity, userDetails.getUsername());
    }

    @DeleteMapping
    public void deleteCart(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam("bookId") Long bookId,
                           HttpSession session) {
        cartService.removeFromCart(session,bookId);
    }

}
