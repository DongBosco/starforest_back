package com.project.starforest.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Entity
@Table(name="Product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="imageList")
@Log4j2
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productName;
	private String brandName;
	private int price;
	private int type;  //0 =>텐트  1=>음식  2=>diy
	private int salesVolume = 0;
	@ElementCollection
	@Builder.Default
	private List<ProductImage> imageList = new ArrayList<>();

	public void addImage(ProductImage image) {
		int index = this.imageList.size();
		if(image.getImageIndex() != index){
			image.changeImageIndex(index+1);
		}
		this.imageList.add(image);
	}

	public void changeProductName(String productName) {
		this.productName = productName;
	}

	public void changeBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void changeType(int type) {
		if(type>2 || type<0){
			log.info("로그인 유형이 이상합니다. 확인하십시오.");
		}
		this.type = type;
	}
	public void changeSalesVolume(int salesVolume) {
		this.salesVolume += salesVolume;
		if (salesVolume < 0) {
			salesVolume = 0;
			log.info(this.productName+"의 판매수량이 이상합니다. 점검하십시오. ");
		}
	}

	public void changePrice(int price) {
		if(price < 0) {
			log.info(this.productName + "의 가격이 이상합니다. 점검하십시오");
			this.price = 0;
		}
		this.price = price;
	}

	public void clearList() {
		this.imageList.clear();	
	}

}
