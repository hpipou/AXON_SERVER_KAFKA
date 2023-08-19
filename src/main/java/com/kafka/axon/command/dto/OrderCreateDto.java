package com.kafka.axon.command.dto;


import lombok.Data;

@Data
public class OrderCreateDto {

    private String productId;
    private String orderUser;
    private Long count;

}
