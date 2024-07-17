package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@Log4j2
@ToString
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "imageIndex")
	private Integer imageIndex;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pno", referencedColumnName = "id")
	private Product product;

	@Column(columnDefinition = "TEXT")
	private String imageURL;

	@Column(name = "createdAt")
	private Timestamp createdAt;

	public ProductImage() {
	}

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
