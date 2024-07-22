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
    private Long id;

    @Column(name = "reservation_number", nullable = true, length = 20)
    private String reservation_number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campsite_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CampSite campsite_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "email", insertable = false, updatable = false)
    private Member user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ReservInfo  reservInfo;

    @Column(name = "start_date", nullable = true)
    private Date start_date;

    @Column(name = "end_date", nullable = true)
    private Date end_date;

    @Column(name = "created_at", nullable = true)
    private Date created_at;

    public void changeReservation_number(String reservation_number) {
        this.reservation_number = reservation_number;
    }

    public void changeStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void changeEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void changeCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}