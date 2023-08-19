package com.kafka.axon.common.event;

public abstract class BaseEvent<T> {

    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public BaseEvent(){}

    public T getId(){
        return id;
    }
}
