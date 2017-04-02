package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

public class RequestHandlerB extends RequestHandler {

    @Override
    public void handle(Request request) {
        if (this.canHandle(request)) {
            request.setHandledBy(this.getClass().getSimpleName());
        } else {
            this.next().handle(request);
        }
    }

    private boolean canHandle(Request request) {
        return request.getType().equals("B");
    }

}
