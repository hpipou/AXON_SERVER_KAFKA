package com.kafka.axon.query.projector;


import com.kafka.axon.common.event.ProductAddStockEvent;
import com.kafka.axon.common.event.ProductCreatedEvent;
import com.kafka.axon.common.event.ProductSubStockEvent;
import com.kafka.axon.query.model.Product;
import com.kafka.axon.query.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@ProcessingGroup("Products")
@RequiredArgsConstructor
@Slf4j
public class ProductProjector {

    private final ProductRepository productRepository;

    @EventHandler
    public void handle(ProductCreatedEvent event, @Timestamp Instant instant) {
        log.info("Handling ProductCreatedEvent");
        Product product = Product.builder()
                .productId(event.getId())
                .price(event.getPrice())
                .stock(event.getStock())
                .description(event.getDescription())
                .name(event.getName())
                .createdAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                .updatedAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                .build();

        productRepository.save(product);
    }

    @EventHandler
    public void handle(ProductAddStockEvent event, @Timestamp Instant instant) {
        log.info("Handling ProductAddStockEvent");
        Product product = productRepository.findById(event.getId()).orElseThrow(() -> new EntityNotFoundException());
        product.addStock(event.getStock());
        product.updateTimestamp(instant);
    }

    @EventHandler
    public void handle(ProductSubStockEvent event, @Timestamp Instant instant) {
        log.info("Handling ProductSubStockEvent");
        Product product = productRepository.findById(event.getId()).orElseThrow(() -> new EntityNotFoundException());
        product.subStock(event.getStock());
        product.updateTimestamp(instant);
    }
}

