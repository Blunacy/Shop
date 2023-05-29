package com.lunacy.shop.entity;

import com.lunacy.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
  @Id
  @GeneratedValue
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private LocalDateTime orderDate; //주문일

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus; //주문상태

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<>();

  private LocalDateTime regTime;

  private LocalDateTime updateTime;
}
