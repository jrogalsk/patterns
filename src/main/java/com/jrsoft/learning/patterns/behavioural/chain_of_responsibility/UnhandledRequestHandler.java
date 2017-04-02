package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

public class UnhandledRequestHandler extends RequestHandler {
    @Override
    public void handle(Request request) {
      throw new IllegalStateException("This message has no appropriate handler!");
    }
}
