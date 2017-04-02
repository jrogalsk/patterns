package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

public class Request {
    private String type;
    private String handledBy;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setHandledBy(String handledBy) {
        this.handledBy = handledBy;
    }

    public String getHandledBy() {
        return this.handledBy;
    }
}
