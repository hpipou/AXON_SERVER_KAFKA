package com.kafka.axon.command.dto;


import lombok.Data;

@Data
public class ProductAddStockDto {

    private String productId;
    private Long stock;

}
