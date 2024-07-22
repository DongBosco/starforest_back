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
    @Column(length = 20)
    private Long id;

    @Column(name = "name", length = 50, nullable = true)
    private String name;

    @Column(name = "tel", length = 20, nullable = true)
    private String tel;

    @Column(name = "total_price", nullable = true)
    private Integer total_price;

    @Column(name = "address1", length = 255, nullable = true)
    private String address1;

    @Column(name = "address2", length = 255, nullable = true)
    private String address2;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Column(name = "order_id" , length = 20, nullable = true)
//    private Order order_id;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeTel(String tel) {
        this.tel = tel;
    }

    public void changeTotal_price(Integer total_price) {
        if(total_price < 0){
            this.total_price = 0;
            log.info("가격이 잘못되었습니다.");
        }
        this.total_price = total_price;
    }

    public void changeAddress1(String address1) {
        this.address1 = address1;
    }

    public void changeAddress2(String address2) {
        this.address2 = address2;
    }
}
