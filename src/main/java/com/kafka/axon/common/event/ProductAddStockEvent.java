package com.kafka.axon.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddStockEvent extends BaseEvent<String> {

    //private String productId;
    private Long stock;

    @Builder
    public ProductAddStockEvent(String id, Long stock){
        super(id);
        this.stock = stock;
    }
}