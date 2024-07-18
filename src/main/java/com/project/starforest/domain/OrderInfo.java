package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Log4j2
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @Column(name = "orderNm", nullable = false)
    private String orderNm;

    @Column(name = "name", length = 50, nullable = true)
    private String name;

    @Column(name = "tel", length = 20, nullable = true)
    private String tel;

    @Column(name = "totalPrice", nullable = true)
    private Integer totalPrice;

    @Column(name = "address1", length = 255, nullable = true)
    private String address1;

    @Column(name = "address2", length = 255, nullable = true)
    private String address2;

    // Foreign key relationship with the Order table
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderNm", referencedColumnName = "orderNumber", insertable = false, updatable = false)
    private Order order;

    // Change methods instead of traditional setters
    public void changeName(String name) {
        this.name = name;
    }

    public void changeTel(String tel) {
        this.tel = tel;
    }

    public void changeTotalPrice(Integer totalPrice) {
        if(totalPrice < 0){
            this.totalPrice = 0;
            log.info("가격이 잘못되었습니다.");
        }
        this.totalPrice = totalPrice;
    }

    public void changeAddress1(String address1) {
        this.address1 = address1;
    }

    public void changeAddress2(String address2) {
        this.address2 = address2;
    }
}
