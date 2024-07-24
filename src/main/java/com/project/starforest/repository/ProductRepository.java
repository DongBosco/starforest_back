package com.project.starforest.repository;

import com.project.starforest.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//데이터베이스와 소통하는애 
@Repository
// ProductRepository: product Entity에 대한 repository interface  (CRUD기능제공)
public interface ProductRepository extends JpaRepository<Product, Long> {  

	//select p, pi : Entity와 그에 연결된 이미지리스트의 첫번째 이미지를 선택 (image_index = 0)
					//from product p left join p.imageList pi : product Entity와 imageList를 LEFT JOIN으로 연결
															//where pi.image_index=0 and p.delFlag=false : 이미지인덱스=0, product Entitydml delflag=false인값만 선택
	//직접 Query를 작성해서 특정 조건에 맞는 데이터를 가져오는 기능 (상품에 연결된 이미지 리스트 중 첫번째 이미지를 가져오고, 삭제되지 않은 상품만 가져오기)
	@Query("select p,pi from Product p left join p.imageList pi where pi.image_index = 0 and p.delFlag = false")
	Page<Object[]> selectList(Pageable pageable);
	//ㄴ> 메서드: 상품리스트와 첫번째 이미지를 페이지 단위로 가져옴->한꺼번에x 페이지처럼나누어서가져오기
	
	//데이터수정시 사용(데이터업데이트)
	@Modifying
	//특정상품의 delfalg값을 변경, pno:상품의 고유Id / falg:delflag
	@Query("update Product p set p.delFlag = :flag where p.id = :pno")
	void updateToDelete(@Param("pno") Long pno,@Param("flag") boolean flag);
	//pno로 주어진 상품의 delflag 값을 flag로 바꿈(delfalg의 삭제여부를 나타내는값)
	
}
