package com.kafka.axon.command.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddStockCommand extends BaseCommand<String> {

    private Long stock;

    @Builder
    public ProductAddStockCommand(String id, Long stock){
        super(id);
        this.stock = stock;
    }

}