package com.kafka.axon.command.aggregate;


import com.kafka.axon.command.command.ProductAddStockCommand;
import com.kafka.axon.command.command.ProductCreateCommand;
import com.kafka.axon.common.enums.ProductState;
import com.kafka.axon.common.event.ProductAddStockEvent;
import com.kafka.axon.common.event.ProductCreatedEvent;
import com.kafka.axon.common.event.ProductSubStockEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
@Getter
@Setter
public class ProductAggregate {

    @AggregateIdentifier
    private String id;

    private String name;
    private Long price;
    private Long stock;
    private String description;

    private ProductState state;


    @CommandHandler
    public ProductAggregate(ProductCreateCommand command) {
        log.info("receive ProductCreateCommand");
        ProductCreatedEvent event = ProductCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .price(command.getPrice())
                .description(command.getDescription())
                .stock(command.getStock())
                .build();

        log.info(event.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        log.info("An ProductCreatedEvent occured");
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.stock = event.getStock();
        this.description = event.getDescription();
        this.state = ProductState.SALE;
    }


    @CommandHandler
    public void on(ProductAddStockCommand command){
        ProductAddStockEvent event = ProductAddStockEvent.builder()
                .id(command.getId())
                .stock(command.getStock())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductAddStockEvent event){
        this.stock+=event.getStock();
    }

    @EventSourcingHandler
    public void on(ProductSubStockEvent event) throws Exception {
        if (this.stock >= event.getStock()) this.stock -= event.getStock();
        if (this.stock == 0) this.state = ProductState.SOLD_OUT;
    }
}
