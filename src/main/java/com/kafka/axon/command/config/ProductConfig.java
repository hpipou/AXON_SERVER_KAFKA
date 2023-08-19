package com.kafka.axon.command.config;


import com.kafka.axon.command.aggregate.ProductAggregate;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductConfig {

    private final EventStore eventStore;

    @Bean
    public AggregateFactory<ProductAggregate> productAggregateFactory(){
        return new GenericAggregateFactory<ProductAggregate>(ProductAggregate.class);
    }

    @Bean
    public Snapshotter productSnapshotter(AggregateFactory<ProductAggregate> aggregateFactory){
        return AggregateSnapshotter.builder()
                .aggregateFactories(aggregateFactory)
                .eventStore(eventStore).build();
    }

    @Bean
    public SnapshotTriggerDefinition productSnapshotTriggerDefinition(Snapshotter snapshotter){
        return new EventCountSnapshotTriggerDefinition(snapshotter,5);
    }

    @Bean
    public Repository<ProductAggregate> productAggregateRepository(SnapshotTriggerDefinition snapshotTriggerDefinition, AggregateFactory<ProductAggregate> aggregateFactory){
        return EventSourcingRepository.builder(ProductAggregate.class)
                .aggregateFactory(aggregateFactory)
                .eventStore(eventStore)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .build();

    }
}
