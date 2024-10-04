package com.plavsic.ecommerce.controller.cart;

import com.plavsic.ecommerce.model.cart.Cart;
import com.plavsic.ecommerce.service.cart.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(HttpSession session) {
        Cart cart = cartService.getCart(session);
        if (cart == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @PostMapping
    public void addToCart(@RequestParam("bookId") Long bookId,
                            @RequestParam("quantity") int quantity,
                            @AuthenticationPrincipal UserDetails userDetails,
                            HttpSession session) {

        cartService.addToCart(session, bookId, quantity, userDetails.getUsername());
    }

    @DeleteMapping
    public void deleteCart(@RequestParam("bookId") Long bookId, HttpSession session) {
        cartService.removeFromCart(session,bookId);
    }

}
