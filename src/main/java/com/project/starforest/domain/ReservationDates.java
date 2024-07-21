package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campsite_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CampSite campSite;

    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Column(name = "end_date", nullable = false)
    private Date end_date;

    @Column(name = "created_at", nullable = true)
    private Date created_at;

    public void changeStart_date(Date start_date) {
        if (start_date != null) {
            this.start_date = start_date;
        } else {
            throw new IllegalArgumentException("입실 날짜를 입력해주세요.");
        }
    }

    public void changeEnd_date(Date end_date) {
        if (end_date != null) {
            this.end_date = end_date;
        } else {
            throw new IllegalArgumentException("퇴실 날짜를 입력해주세요.");
        }
    }

    public void changeCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}