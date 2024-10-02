package com.plavsic.ecommerce.dto.order;

import com.plavsic.ecommerce.dto.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemDTO {
    private Long id;
    private BookDTO book;
    private int quantity;
}
