package com.kafka.axon.query.model;


import com.kafka.axon.common.enums.ProductState;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    private String productId;

    private String name;
    private Long price;
    private String description;
    private Long stock;

    @Enumerated(value = EnumType.STRING)
    private ProductState state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public Product(String productId, String name, Long price, String description, Long stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.state = ProductState.SALE;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addStock(Long stock) {
        this.stock += stock;
    }

    public void subStock(Long stock){
        if (this.stock < stock) throw new InvalidParameterException();
        this.stock -= stock;
        if (this.stock == 0) this.state = ProductState.SOLD_OUT;
    }

    public void updateTimestamp(Instant instant){
        this.updatedAt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
