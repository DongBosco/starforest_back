package com.project.starforest.controller;


import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.CampReservationInfoDTO;
import com.project.starforest.dto.CampSearchDTO;
import com.project.starforest.dto.MapResponseDTO;
import com.project.starforest.dto.ReservationDto;
import com.project.starforest.dto.ViewMapResponseDTO;
import com.project.starforest.repository.MapTestRepository;
import com.project.starforest.repository.PointRepository;
import com.project.starforest.repository.ReservationRepository;
import com.project.starforest.service.impl.CampSearchService;
import com.project.starforest.service.impl.CoordinatesService;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/camp")
public class CampController {
	
	//-----------吏��룄----------//

		@Autowired
		private MapTestRepository mapTestRepository;
		
		@PostMapping("/view/map/{id}")
		public ResponseEntity<ViewMapResponseDTO> viewMap(
				@PathVariable("id") Long id
				) {
			log.info("+++++++++++++++++++ map +++++++++++++++++++++++++++");
			CampSite entity = mapTestRepository.findById(id).orElseThrow();
			log.info(entity.toString());
			ViewMapResponseDTO result = ViewMapResponseDTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.lineIntro(entity.getLine_intro())
					.intro(entity.getIntro())
					.allar(entity.getAllar())
					.featureNm(entity.getFeature_nm())
					.is_glamp(entity.is_glamp())
					.is_carvan(entity.is_carvan())
					.is_auto(entity.is_auto())
					.sigunguNm(entity.getSigungu_nm())
					.mapx(entity.getMapx())
					.mapy(entity.getMapy())
					.tel(entity.getTel())
					.homepage(entity.getHomepage())
					.price(entity.getPrice())
					.animalCmgCl(entity.getAnimal_cmg_cl())
					.glampInnerFclty(entity.getGlamp_inner_fclty())
					.caravInnerFclty(entity.getCarav_inner_fclty())
					.posblFcltyCl(entity.getPosbl_fclty_cl())
					.themaEnvrnCl(entity.getThema_envrn_cl())
					.sbrsCl(entity.getSbrs_cl())
					.eqpmnLendCl(entity.getEqpmn_lend_cl())
					.first_image_url(entity.getFirst_image_url())
					.add1(entity.getAdd1())
					.add2(entity.getAdd2())
					.brazierCl(entity.getBrazielr_cl())
					.build();
			log.info(result.toString());
			return ResponseEntity.ok(result);
		}	
	

		@Autowired
		private CoordinatesService coordinatesService;
		
		@Autowired
		private PointRepository pointRepository;

		@PostMapping("/coordinates")
	    public ResponseEntity<List<MapResponseDTO>> getNearbyPoints(@RequestBody CampSite mapEntity) {

	        List<CampSite> result = coordinatesService.findNearbyPoints(mapEntity);
	        
//	        log.info("result"+result.toString());
	        
	        //寃곌낵媛� �뾾�쓣�븣
	        if(result.isEmpty()) {
	        	return ResponseEntity.ok(null);
	        }
	        
	     // MapEntity 由ъ뒪�듃瑜� MapResponseDTO 由ъ뒪�듃濡� 蹂��솚�뀅�뀕
	        List<MapResponseDTO> response = result.stream()
	                .map(entity -> MapResponseDTO.builder()
	                        .id(entity.getId())
	                        .name(entity.getName())
	                        .first_image_url(entity.getFirst_image_url())
	                        .add1(entity.getAdd1())
	                        .is_auto(entity.is_auto())
	                        .is_carvan(entity.is_carvan())
	                        .is_glamp(entity.is_glamp())
	                        .homepage(entity.getHomepage())
	                        .price(entity.getPrice())
	                        .mapX(entity.getMapx())
	                        .mapY(entity.getMapy())
	                        .build())
	                .collect(Collectors.toList());
	 
	        
	        
	        return ResponseEntity.ok(response);
	    }
		
		@GetMapping("/maptest/{id}")
		public ResponseEntity<CampSite> testGet(@PathVariable("id") Long id) {
			log.info("--------------------------------------");
			log.info(org.hibernate.Version.getVersionString());

			log.info("id : "+id);
			CampSite result = pointRepository.findById(id).orElse(null);
			return ResponseEntity.ok(result);
		}
	
	//--------------移댁뭅�삤 寃곗젣----------------//
//	public class PayController {
//		
//		  private final KakaoPayService kakaoPayService;
//		  private final PaymentRepository paymentRepository;
//
//		    @PostMapping("/kakaoPay")
//		    public KakaoPayReadyResponse kakaoPay() {
//		    	log.info("寃곗젣�떆�룄");
//		        return kakaoPayService.kakaoPayReady();
//		    }
//		    
//		    @GetMapping("/kakaoPaySuccess/{pg_token}")
//		    public ResponseEntity<PaymentApprovalResponse> kakaoPaySuccess(@PathVariable("pg_token") String pgToken) {
//		    	log.info("�듅�씤 �떆�룄");
//		        // 1. 移댁뭅�삤�럹�씠 API濡� 寃곗젣 �듅�씤 �슂泥�
//		        PaymentApprovalResponse approvalResponse = kakaoPayService.approvePayment(pgToken);
//		        
//		        // 2. �듅�씤 �쓳�떟�쓣 諛뷀깢�쑝濡� DB�뿉 寃곗젣 �젙蹂� ���옣
//		        PaymentEntity payment = PaymentEntity.builder()
//			            .orderId(approvalResponse.getPartner_order_id())
//			            .paymentToken(approvalResponse.getAid())
//			            .amount((long) approvalResponse.getAmount().getTotal())
//			            .status("�듅�씤")
//			            .paymentMethod(approvalResponse.getPayment_method_type())
//			            .approvedAt(LocalDateTime.parse(approvalResponse.getApproved_at()))
//			            .tid(approvalResponse.getTid())
//			            .itemName(approvalResponse.getItem_name())
//			            .build();
//			        
//			        paymentRepository.save(payment);
//			        log.info("���옣");
//			        log.info("payment"+payment);
//			        
//		        
//		        // 3. �겢�씪�씠�뼵�듃�뿉 �븘�슂�븳 �젙蹂� 諛섑솚
//		        return ResponseEntity.ok(approvalResponse);
//		    }
//	}
	
	//----------------�삁�빟-----------------//
		

		@Autowired
		private ReservationRepository reservationRepository;

		//�뜲�씠�꽣 由ъ뒪�듃 蹂닿린
		@GetMapping("/reservations")
		   public ResponseEntity<List<ReservationDates>> getAllReservations() {
		       List<ReservationDates> result = reservationRepository.findAll();
		       return ResponseEntity.ok(result);
			}
		    
		//�뜲�씠�꽣 ���옣
		@PostMapping("/reservation/1")
		public ResponseEntity<ReservationDates> createReservation(
		 		@RequestBody ReservationDto dto
		   		) {
		   	
//		   	log.info("�슂泥� 媛� : "+dto);
		    
		   	LocalDateTime startDate = dto.getStartDate();
		   	LocalDateTime endDate = dto.getEndDate();
	        
	        //�삁�빟 遺덇��뒫�븳 �궇吏쒖씪�븣 �넃�넃�넃�넃�넃
	        // �삁�빟 媛��뒫 �뿬遺� �솗�씤(�삁�빟 遺덇��떆 Reservation�삎�쑝濡� 硫붿꽭吏� �룷�븿�빐�꽌 諛섑솚 �븘�슂)
	        if (isReservation(startDate, endDate)) {
//	        	log.error("�삁�빟遺덇�");
	        	ReservationDates faleReservation = new ReservationDates();
	        	faleReservation.setStart_date(startDate);
	        	faleReservation.setEnd_date(endDate);
	        	faleReservation.setMessage("no reservation.");
	        	return ResponseEntity.ok().body(faleReservation);
	        }

	        
	        
//	        ReservationDates result = ReservationDates.builder()
//	        		.start_date(startDate)
//	        		.end_date(endDate)
//	        		.created_at(LocalDateTime.now())
//	        		.campSite(null)
//	        		.build();
//	        
		   	//�삁�빟 媛��뒫�븳 �궇吏쒖씪�븣 �넃�넃�넃�넃
	        dto.setCreatedAt(LocalDateTime.now());

		   	ReservationDates entity=dto.toEntity();
		    	
		   	//���옣
		    ReservationDates saveDate = reservationRepository.save(entity);
		        
//		    log.info("�삁�빟�궇吏� : "+entity);
		    return ResponseEntity.ok(saveDate);
		}
		
		//�삁�빟 媛��뒫�씪 �솗�씤
		private boolean isReservation(LocalDateTime start, LocalDateTime end) {
	        List<ReservationDates> overlappingReservations = reservationRepository.findOverlappingReservations(start, end);
	        return !overlappingReservations.isEmpty();
	    }
	
	//-------------------罹좏븨�옣 寃��깋---------------------//

		@Autowired
		private CampSearchService campSearchService;
		
		@GetMapping("/search")
		public ResponseEntity<List<CampSearchDTO>> getfindcamp(
				@RequestParam("query") String query
				) {
			
			List<CampSearchDTO> result = campSearchService.searchCamp(query);
			
					
			return ResponseEntity.ok(result);
		}
	
	//------------------�삁�빟------------------------//

		
		@GetMapping("/reservation/{id}")
		public ResponseEntity<CampReservationInfoDTO> getCampReservation(
				@PathVariable("id") Long id
				) {
			
			CampSite entity = mapTestRepository.findById(id).orElseThrow();
			//s
			CampReservationInfoDTO result = CampReservationInfoDTO.builder()
					.id(entity.getId())
					.is_glamp(entity.is_glamp())
					.is_carvan(entity.is_carvan())
					.is_auto(entity.is_auto())
					.first_image_url(entity.getFirst_image_url())
					.name(entity.getName())
					.add1(entity.getAdd1())
					.mapx(entity.getMapx())
					.mapy(entity.getMapy())
					.sigungu_nm(entity.getSigungu_nm())
					.build(); 
			
			return ResponseEntity.ok(result);
		}
}