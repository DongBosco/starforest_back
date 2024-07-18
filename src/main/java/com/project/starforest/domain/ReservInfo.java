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
    private int id;

    @Column(name = "ReservId", nullable = false)
    private Integer reservId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "request", columnDefinition = "TEXT")
    private String request;

    @Column(name = "carNumber", length = 20)
    private String carNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ReservId", referencedColumnName = "id", insertable = false, updatable = false)
    private Reservation reservation;

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

    public void changeCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}