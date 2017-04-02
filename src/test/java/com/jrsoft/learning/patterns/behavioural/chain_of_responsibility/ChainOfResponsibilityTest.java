package com.jrsoft.learning.patterns.behavioural.chain_of_responsibility;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChainOfResponsibilityTest {
    private ChainOfResponsibility chainOfResponsibility = new ChainOfResponsibility();

    @Test
    public void shouldHandleRequestsOfTypeAWithRequestHandlerA() {
        Request requestA = new Request("A");

        this.chainOfResponsibility.handle(requestA);

        assertThat(requestA.getHandledBy(), is("RequestHandlerA"));
    }

    @Test
    public void shouldHandleRequestsOfTypeBWithRequestHandlerB() {
        Request requestA = new Request("B");

        this.chainOfResponsibility.handle(requestA);

        assertThat(requestA.getHandledBy(), is("RequestHandlerB"));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotHandleRequestsOtherThanAandB() {
        Request requestA = new Request("C");

        this.chainOfResponsibility.handle(requestA);
    }

}
