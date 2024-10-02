package com.plavsic.ecommerce.model.cart;

import com.plavsic.ecommerce.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItem {
    private Book book;
    private int quantity;
}
