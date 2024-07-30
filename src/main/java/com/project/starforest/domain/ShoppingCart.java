package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(
		name="shopping_cart"
		)
public class ShoppingCart {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_email", referencedColumnName = "email", insertable = false, updatable = false)
	private Member member;

	@Column(name = "total_price", nullable = true)
	private Integer total_price;

	@Column(name = "created_at", nullable = true)
	private Timestamp created_at;

	@Column(name = "updated_at", nullable = true)
	private Timestamp updated_at;

	@Column(name = "status", length = 20)
	private String status;

	public void changeTotal_price(Integer total_price) {
		if (total_price != null && total_price >= 0) {
			this.total_price = total_price;
		} else {
			throw new IllegalArgumentException("총합 금액을 확인해주세요");
		}
	}

	public void changeStatus(String status) {
		if (status != null && !status.trim().isEmpty()) {
			this.status = status;
		} else {
			throw new IllegalArgumentException( "주문 상태를 확인해주세요.");
		}
	}

	public void changeUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}