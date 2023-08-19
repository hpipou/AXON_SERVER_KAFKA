package com.kafka.axon.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

    @TargetAggregateIdentifier
    private  T id;

    public BaseCommand(T id) {
        this.id = id;
    }
    public BaseCommand() {}

    public T getId() {
        return id;
    }
}
