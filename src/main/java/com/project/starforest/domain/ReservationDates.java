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
    private int id;

    @Column(name = "campsiteId", nullable = false)
    private Integer campsiteId;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "createdAt", nullable = true)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campsiteId", referencedColumnName = "id", insertable = false, updatable = false)
    private CampSite campSite;

    public void changeStartDate(Date startDate) {
        if (startDate != null) {
            this.startDate = startDate;
        } else {
            throw new IllegalArgumentException("입실 날짜를 입력해주세요.");
        }
    }

    public void changeEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = endDate;
        } else {
            throw new IllegalArgumentException("퇴실 날짜를 입력해주세요.");
        }
    }

    public void changeCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}