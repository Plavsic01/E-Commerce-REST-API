package com.plavsic.ecommerce.dto.cart;

import com.plavsic.ecommerce.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartDTO {
    private UserDTO user;
    private List<CartItemDTO> cartItems;
}
