package com.kafka.axon.command.dto;

import lombok.Data;

@Data
public class ProductCreateDto {

    private String name;
    private Long price;
    private String description;
    private Long stock;
}
