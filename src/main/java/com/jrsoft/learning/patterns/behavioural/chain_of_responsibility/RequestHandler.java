package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

import static java.util.Objects.isNull;

public abstract class RequestHandler {
    private RequestHandler next;

    public void add(RequestHandler next) {
        if (isNull(this.next))
            this.next = next;
        else
            this.next.add(next);
    }

    protected RequestHandler next() {
        return this.next;
    }

    public abstract void handle(Request request);

}
