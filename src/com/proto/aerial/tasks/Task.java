package com.proto.aerial.tasks;

public abstract class Task {

    public Task() {
        super();
    }

    public abstract boolean activate();

    public abstract void execute();


}
