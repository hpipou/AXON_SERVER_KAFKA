package com.kafka.axon.command.aggregate;


import com.kafka.axon.command.command.OrderCreateCommand;
import com.kafka.axon.common.event.OrderCreatedEvent;
import com.kafka.axon.common.event.ProductSubStockEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
@NoArgsConstructor
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private String productId;
    private Long count;
    private String orderUser;


    @CommandHandler
    public OrderAggregate(OrderCreateCommand command) {
        log.info("receive OrderCreateCommand");
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .id(command.getId())
                .productId(command.getProductId())
                .orderUser(command.getOrderUser())
                .count(command.getCount())
                .build();

        ProductSubStockEvent productSubStockEvent = ProductSubStockEvent.builder()
                .id(command.getProductId())
                .stock(command.getCount())
                .build();
        AggregateLifecycle.apply(orderCreatedEvent)
                .andThenApply(()-> productSubStockEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        log.info("An OrderCreatedEvent occured");
        this.id = event.getId();
        this.productId = event.getProductId();
        this.orderUser = event.getOrderUser();
        this.count = event.getCount();
    }
}

