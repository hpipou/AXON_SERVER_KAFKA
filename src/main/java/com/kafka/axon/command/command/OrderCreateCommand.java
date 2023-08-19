package com.kafka.axon.command.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateCommand extends BaseCommand<String> {

    private String productId;
    private Long count;
    private String orderUser;

    @Builder
    public OrderCreateCommand(String id, String productId, Long count, String orderUser){
        super(id);
        this.productId = productId;
        this.count = count;
        this.orderUser = orderUser;
    }

}