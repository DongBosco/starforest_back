package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true, length = 20)
    private String order_number;

    @Column(name = "order_type", nullable = true)
    private Integer order_type;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "car_nm", referencedColumnName = "id", insertable = false, updatable = false)
//    private ShoppingCart shoppingCart;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "info_nm", referencedColumnName = "id", insertable = false, updatable = false)
//    private OrderInfo order_info;

    @Column
    private boolean is_payment;
    
    public void changeOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public void changeOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    public void changeCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}