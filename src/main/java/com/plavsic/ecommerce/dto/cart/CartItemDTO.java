package com.plavsic.ecommerce.dto.cart;

import com.plavsic.ecommerce.dto.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemDTO {
    private BookDTO book;
    private int quantity;
}
