package com.kafka.axon.common.event;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductSubStockEvent extends BaseEvent<String>{

    private Long stock;

    @Builder
    public ProductSubStockEvent(String id, Long stock){
        super(id);
        this.stock = stock;
    }
}
