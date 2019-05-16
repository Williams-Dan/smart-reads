package uk.co.scottishpower.smartreads.utils;

import java.util.function.Consumer;

public class Builder<T> {
    private T object;

    public Builder(Class<T> clazz){
        try {
            this.object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Builder<T> and(Consumer<T> expression){
        expression.accept(object);
        return this;
    }

    public T build(){
        return object;
    }

}
