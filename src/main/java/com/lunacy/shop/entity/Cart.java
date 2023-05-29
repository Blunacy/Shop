package com.lunacy.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart {
  @Id
  @Column(name = "cart_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;


}
