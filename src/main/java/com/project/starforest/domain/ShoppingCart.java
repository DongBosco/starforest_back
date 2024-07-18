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
		,		indexes = {@Index(name="idex_cart_email", columnList="member_owner")}
		)
public class ShoppingCart {

	@Id
	private int id;

	@Column(name = "userId", nullable = true)
	private Integer userId;

	@Column(name = "totalPrice", nullable = true)
	private Integer totalPrice;

	@Column(name = "createdAt", nullable = true)
	private Timestamp createdAt;

	@Column(name = "updatedAt", nullable = true)
	private Timestamp updatedAt;

	@Column(name = "status", length = 20)
	private String status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "email", insertable = false, updatable = false)
	private Member member;

	public void changeTotalPrice(Integer totalPrice) {
		if (totalPrice != null && totalPrice >= 0) {
			this.totalPrice = totalPrice;
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

	public void changeUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}