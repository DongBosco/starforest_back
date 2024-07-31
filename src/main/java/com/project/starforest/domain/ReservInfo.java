package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "reserv_info")
public class ReservInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "request", columnDefinition = "TEXT")
    private String request;

    @Column(name = "car_number", length = 20)
    private String car_number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reserv_id")
    private Reservation reservation;
    
    public void changeName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException( "�씠由꾩쓣 �엯�젰�빐二쇱꽭�슂.");
        }
    }
    public void changeTel(String tel) {
        if (tel != null && !tel.trim().isEmpty()) {
            this.tel = tel;
        } else {
            throw new IllegalArgumentException("�쟾�솕踰덊샇瑜� �엯�젰�빐 二쇱꽭�슂.");
        }
    }

    public void changeRequest(String request) {
        this.request = request;
    }

    public void changeCarNumber(String car_number) {
        this.car_number = car_number;
    }
}
