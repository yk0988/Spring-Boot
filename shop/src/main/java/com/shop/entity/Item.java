package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //상픔 코드

    @Column(nullable = false, length = 50)
    private String itemNm;   //상품명

    @Column(nullable = false)
    private int price;   //가격

    @Column(nullable = false)
    private int stockNumber;   //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;   //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;   //상품 판매 상태

    private LocalDateTime regTiem;   //등록 시간

    private LocalDateTime updateTime;   //수정 시간

}
