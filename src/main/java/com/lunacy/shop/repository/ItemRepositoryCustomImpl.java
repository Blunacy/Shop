package com.lunacy.shop.repository;

import com.lunacy.shop.constant.ItemSellStatus;
import com.lunacy.shop.dto.ItemSearchDto;
import com.lunacy.shop.entity.Item;
import com.lunacy.shop.entity.QItem;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

  private JPAQueryFactory queryFactory;

  public ItemRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
    return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
  }

  private BooleanExpression regDtsAfter(String searchDateType) {
    LocalDateTime dateTime = LocalDateTime.now();

    if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
      return null;
    } else if (StringUtils.equals("id", searchDateType)) {
      dateTime = dateTime.minusDays(1);
    } else if (StringUtils.equals("1w", searchDateType)) {
      dateTime = dateTime.minusWeeks(1);
    } else if (StringUtils.equals("1m", searchDateType)) {
      dateTime = dateTime.minusMonths(1);
    } else if (StringUtils.equals("6m", searchDateType)) {
      dateTime = dateTime.minusMonths(6);
    }
    return QItem.item.regTime.after(dateTime);
  }

  private BooleanExpression searchByLike(String searchBy, String searchQuery) {

    if (StringUtils.equals("itemNm", searchBy)) {
      return QItem.item.itemNm.like("%" + searchQuery + "%");
    } else if (StringUtils.equals("createdBy", searchBy)) {
      return QItem.item.createdBy.like("%" + searchQuery + "%");
    }

    return null;
  }

  @Override
  public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

    List<Item> content = queryFactory
        .selectFrom(QItem.item)
        .where(regDtsAfter(itemSearchDto.getSearchDateType()),
            searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
            searchByLike(itemSearchDto.getSearchBy(),
                itemSearchDto.getSearchQuery()))
        .orderBy(QItem.item.id.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = queryFactory.select(Wildcard.count).from(QItem.item)
        .where(regDtsAfter(itemSearchDto.getSearchDateType()),
            searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
            searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
        .fetchOne()
        ;
    return new PageImpl<>(content, pageable, total);
  }
}
