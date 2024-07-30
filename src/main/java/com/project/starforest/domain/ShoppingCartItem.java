package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(
		name="shopping_cart_item"
		,indexes= {
				@Index(name="idx_cartitem_cart", columnList="cart_id"),
				@Index(name="idx_cartitem_pno_cart", columnList="product_id, cart_id")
		}
		)
public class ShoppingCartItem {
	@Id
	private Long id;
	
	//
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id", insertable = false, updatable = false)
	private ShoppingCart shoppingCart;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "created_at", nullable = true)
	private Timestamp created_at;

	@Column(name = "updated_at", nullable = true)
	private Timestamp updated_at;

	public void changeQuantity(Integer quantity) {
		if (quantity != null && quantity > 0) {
			this.quantity = quantity;
		} else {
			throw new IllegalArgumentException("수량을 확인해주세요.");
		}
	}

	public void changeUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}
