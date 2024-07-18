package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@ToString
public class ProductImage {
	@Id
	private int id;

	@Column(name = "imageIndex")
	private Integer imageIndex;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pno", referencedColumnName = "id")
	private Product product;

	@Column(columnDefinition = "TEXT")
	private String imageURL;

	@Column(name = "createdAt")
	private Timestamp createdAt;


	public void changeId(int id) {
		this.id = id;
	}

	public void changeImageIndex(int imageIndex) {
		if (imageIndex <0){
			this.imageIndex = -1;
		}
		this.imageIndex = imageIndex;
		if (this.imageIndex < 0){
			log.info("image index를 확인하세요! ");
		}
	}

	public void changeProduct(Product product) {
		this.product = product;
	}

	public void changeImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public void changeCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
