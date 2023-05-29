package com.lunacy.shop.repository;

import com.lunacy.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
  List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
