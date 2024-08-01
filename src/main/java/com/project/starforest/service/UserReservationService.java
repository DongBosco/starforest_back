package com.project.starforest.service;

import java.util.List;

import com.project.starforest.domain.Reservation;

public interface UserReservationService {
	List<Reservation> getMyAllReservationList(String email);
}
