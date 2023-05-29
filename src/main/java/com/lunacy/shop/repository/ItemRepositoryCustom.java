package com.lunacy.shop.repository;

import com.lunacy.shop.dto.ItemSearchDto;
import com.lunacy.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
  Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
