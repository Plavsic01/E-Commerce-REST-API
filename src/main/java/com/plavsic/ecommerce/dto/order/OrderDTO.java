package com.plavsic.ecommerce.dto.order;

import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.model.order.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDTO {
    private Long id;
    private UserDTO user;
    private LocalDateTime orderDate;
    private String note;
    @NotNull
    private String address;
    @NotNull
    private int totalPrice;
    private Status status;
    private List<OrderItemDTO> items;
}
