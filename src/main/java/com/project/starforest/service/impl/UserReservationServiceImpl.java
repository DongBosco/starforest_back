package com.project.starforest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Reservation;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.ReservationedRepository;
import com.project.starforest.service.UserReservationService;

@Service
public class UserReservationServiceImpl implements UserReservationService{
	@Autowired
	private ReservationedRepository reservationedRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Reservation> getMyAllReservationList(String email){
				
		Member member = memberRepository.findByEmail(email);
		
		return reservationedRepository.findByEmail(member);
	};
	
	
	
}
