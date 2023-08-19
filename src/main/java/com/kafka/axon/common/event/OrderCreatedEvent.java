package com.kafka.axon.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreatedEvent extends BaseEvent<String>{

    private String productId;
    private String orderUser;
    private Long count;


    @Builder
    public OrderCreatedEvent(String id, String productId, String orderUser, Long count){
        super(id);
        this.productId = productId;
        this.orderUser = orderUser;
        this.count = count;
    }

}
