package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "diary")
public class Diary {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservationId", referencedColumnName = "id")
    private Reservation reservation;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 255)
    private String category;

    private Timestamp createdAt;

    public void changeId(int id) {
        this.id = id;
    }

    public void changeReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeCategory(String category) {
        this.category = category;
    }

    public void changeCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}