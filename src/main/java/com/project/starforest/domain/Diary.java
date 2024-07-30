package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "diary")
public class Diary {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private Member user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 255)
    private String category;

    private LocalDateTime created_at;

    public void changeId(Long id) {
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

    public void changeCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}