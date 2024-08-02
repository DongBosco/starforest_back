package com.project.starforest.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number", nullable = true, length = 20)
    private String reservation_number;

    @JsonIgnore
    @JoinColumn(name = "campsite_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CampSite campsite_id;

    @JsonIgnore
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "info_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private ReservInfo  reservInfo;

    @Column(name = "start_date", nullable = true)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime end_date;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime created_at;
    @Column(name = "is_payment")
    private boolean is_payment;
    
    @Transient
  	private String message;
    
    public void changeIs_payment(boolean result) {
        this.is_payment = result;
    }

    public void changeReservation_number(String reservation_number) {
        this.reservation_number = reservation_number;
    }

    public void changeStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public void changeEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public void changeCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    public Reservation(LocalDateTime startDate, LocalDateTime endDate,LocalDateTime createdAt, CampSite campsite_id) {
		this.start_date = startDate;
		this.end_date = endDate;
		this.created_at = createdAt;
		this.campsite_id = campsite_id;
	}

	
}