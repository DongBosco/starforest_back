package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "reservation_dates")
public class ReservationDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JoinColumn(name = "campsite_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campsite_id", referencedColumnName = "id")
    private CampSite campSite;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime created_at;
    
    @Transient
	private String message;

    public void changeStart_date(LocalDateTime start_date) {
        if (start_date != null) {
            this.start_date = start_date;
        } else {
            throw new IllegalArgumentException("�엯�떎 �궇吏쒕�� �엯�젰�빐二쇱꽭�슂.llllkp사사사");
        }
    }

    public void changeEnd_date(LocalDateTime end_date) {
        if (end_date != null) {
            this.end_date = end_date;
        } else {
            throw new IllegalArgumentException("�눜�떎 �궇吏쒕�� �엯�젰�빐二쇱꽭�슂.");
        }
    }

    public void changeCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}