package com.kafka.axon.query.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    private String id;

    private String productId;
    private String productName;
    private Long count;
    private Long price;
    private Long totalPrice;
    private String orderUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public Order(String id, String productId, String productName, Long count, Long price, LocalDateTime createdAt, String orderUser){
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.count = count;
        this.totalPrice = price*count;
        this.orderUser = orderUser;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}

