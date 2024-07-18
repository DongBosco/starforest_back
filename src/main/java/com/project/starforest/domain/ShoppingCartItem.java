package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude={"cart","product"})
@Table(
		name="shopping_cart_item"
		,indexes= {
				@Index(name="idx_cartitem_cart", columnList="cart_id"),
				@Index(name="idx_cartitem_pno_cart", columnList="product_id, cart_id")
		}
		)
public class ShoppingCartItem {
	@Id
	private int id;

	@Column(name = "productId", nullable = false)
	private Integer productId;

	@Column(name = "cartId", nullable = false)
	private Integer cartId;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "createdAt", nullable = true)
	private Timestamp createdAt;

	@Column(name = "updatedAt", nullable = true)
	private Timestamp updatedAt;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId", referencedColumnName = "id", insertable = false, updatable = false)
	private ShoppingCart shoppingCart;

	public void changeQuantity(Integer quantity) {
		if (quantity != null && quantity > 0) {
			this.quantity = quantity;
		} else {
			throw new IllegalArgumentException("수량을 확인해주세요.");
		}
	}

	public void changeUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
