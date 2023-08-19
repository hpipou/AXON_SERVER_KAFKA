package com.kafka.axon.common.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductCreatedEvent extends BaseEvent<String>{

    private String name;
    private String description;
    private Long price;
    private Long stock;

    @Builder
    public ProductCreatedEvent(String id, String name, String description, Long price, Long stock){
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

}