package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "order")
public class Order {

    @Id
    private int id;

    @Column(name = "carNm", nullable = true)
    private Integer carNm;

    @Column(name = "orderNumber", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "orderType", nullable = true)
    private Integer orderType;

    @Column(name = "userId", nullable = true)
    private Integer userId;

    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "email", insertable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carNm", referencedColumnName = "id", insertable = false, updatable = false)
    private ShoppingCart shoppingCart;

    public void changeCarNm(Integer carNm) {
        this.carNm = carNm;
    }

    public void changeOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void changeOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void changeUserId(Integer userId) {
        this.userId = userId;
    }

    public void changeCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}