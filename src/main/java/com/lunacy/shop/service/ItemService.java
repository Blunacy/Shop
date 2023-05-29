package com.lunacy.shop.service;

import com.lunacy.shop.dto.ItemFormDto;
import com.lunacy.shop.entity.Item;
import com.lunacy.shop.entity.ItemImg;
import com.lunacy.shop.repository.ItemImgRepository;
import com.lunacy.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemImgRepository itemImgRepository;
  private final ItemImgService itemImgService;

  public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
    // 상품 등록
    Item item = itemFormDto.createItem();
    itemRepository.save(item);

    // 이미지 등록
    for (int i=0; i<itemImgFileList.size();i++){
      ItemImg itemImg = new ItemImg();
      itemImg.setItem(item);
      if (i == 0)
        itemImg.setReqimgYn("Y");
      else
        itemImg.setReqimgYn("N");
      itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
    }
    return item.getId();
  }
}
