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
@Table(name = "reservation")
public class Reservation {

    @Id
    private int id;

    @Column(name = "reservationNumber", nullable = true, length = 20)
    private String reservationNumber;

    @Column(name = "campsiteId", nullable = true)
    private Integer campsiteId;

    @Column(name = "userId", nullable = true)
    private Integer userId;

    @Column(name = "startDate", nullable = true)
    private Date startDate;

    @Column(name = "endDate", nullable = true)
    private Date endDate;

    @Column(name = "createdAt", nullable = true)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campsiteId", referencedColumnName = "id", insertable = false, updatable = false)
    private CampSite campSite;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "email", insertable = false, updatable = false)
    private Member member;

    public void changeReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void changeStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void changeEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void changeCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}