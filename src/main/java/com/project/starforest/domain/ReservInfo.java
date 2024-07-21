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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reservation reservation;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "request", columnDefinition = "TEXT")
    private String request;

    @Column(name = "car_number", length = 20)
    private String car_number;

    public void changeName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException( "이름을 입력해주세요.");
        }
    }
    public void changeTel(String tel) {
        if (tel != null && !tel.trim().isEmpty()) {
            this.tel = tel;
        } else {
            throw new IllegalArgumentException("전화번호를 입력해 주세요.");
        }
    }

    public void changeRequest(String request) {
        this.request = request;
    }

    public void changeCarNumber(String car_number) {
        this.car_number = car_number;
    }
}