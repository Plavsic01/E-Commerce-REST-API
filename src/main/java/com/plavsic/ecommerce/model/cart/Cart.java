package com.plavsic.ecommerce.model.cart;

import com.plavsic.ecommerce.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cart {
    private String username;
    private List<CartItem> cartItems = new ArrayList<>();
}
