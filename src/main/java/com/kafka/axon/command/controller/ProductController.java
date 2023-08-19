package com.kafka.axon.command.controller;


import com.kafka.axon.command.command.ProductAddStockCommand;
import com.kafka.axon.command.command.ProductCreateCommand;
import com.kafka.axon.command.dto.ProductAddStockDto;
import com.kafka.axon.command.dto.ProductCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        log.info("receive create request");
        String id = UUID.randomUUID().toString();
        ProductCreateCommand command = ProductCreateCommand.builder()
                .id(id)
                .name(productCreateDto.getName())
                .description(productCreateDto.getDescription())
                .price(productCreateDto.getPrice())
                .stock(productCreateDto.getStock())
                .build();
        log.info(command.getId());
        return commandGateway.send(command);
    }

    @PutMapping("/stock")
    public CompletableFuture<String> addStock(@RequestBody ProductAddStockDto dto){
        ProductAddStockCommand command = ProductAddStockCommand.builder()
                .id(dto.getProductId())
                .stock(dto.getStock())
                .build();
        return commandGateway.send(command);
    }

}

