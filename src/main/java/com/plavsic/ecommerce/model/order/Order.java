package com.plavsic.ecommerce.model.order;

import com.plavsic.ecommerce.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false)
    private User user;
//    @Column(nullable = false)
    private LocalDateTime orderDate;
//    @Column(nullable = false)
    private int totalPrice;
//    @Column(nullable = false)
    private Status status;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
//    @Column(nullable = false)
    private List<OrderItem> items;
}
