package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

public class ChainOfResponsibility {

    private RequestHandler rootHandler;

    public ChainOfResponsibility() {
        this.rootHandler = new RequestHandlerA();
        this.rootHandler.add(new RequestHandlerB());
        this.rootHandler.add(new UnhandledRequestHandler());
    }

    public void handle(Request request) {
        this.rootHandler.handle(request);
    }

}
