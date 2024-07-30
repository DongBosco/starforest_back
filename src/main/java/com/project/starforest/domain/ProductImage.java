package com.project.starforest.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@ToString
@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_index")
	private Integer image_index;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id",insertable = false, updatable = false)
	private Product product;

	@Column(columnDefinition = "TEXT")
	private String image_url;

	@Column(name = "created_at")
	private LocalDateTime created_at; 



	public void changeId(Long id) {
		this.id = id;
	}

	public void changeImage_index(int image_index) {
		if (image_index <0){
			this.image_index = -1;
		}
		this.image_index = image_index;
		if (this.image_index < 0){
			log.info("image index를 확인하세요! ");
		}
	}

	public void changeProduct(Product product) {
		this.product = product;
	}

	public void changeImage_url(String image_url) {
		this.image_url = image_url;
	}

	public void changeCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}
