package ru.peter.service.drools;

import org.junit.Before;
import org.junit.Test;
import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;
import ru.peter.service.drools.model.Subscriber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientProcessorTest {

    private Client testSumClient;

    private Client testBigClient;

    private ClientProcessor clientProcessor;

    @Before
    public void init() {
        clientProcessor = new DroolsClientProcessor();


        testSumClient = new Client();
        testSumClient.setClientId(1L);

        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        for (int i = 1; i < 6; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(i);
            subscriber.setSpent(5L);
            subscribers.add(subscriber);
        }
        testSumClient.setSubscribers(subscribers);

        testBigClient = new Client();
        testBigClient.setClientId(2L);

        subscribers = new ArrayList<Subscriber>();
        for (int i = 1; i < 102; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(i);
            subscriber.setSpent(100L);
            subscribers.add(subscriber);
        }
        testBigClient.setSubscribers(subscribers);
    }

    @Test
    public void testSum() {
        ClientResult result = clientProcessor.process(testSumClient);
        assertEquals(25L, result.getSpentTotal());
        assertFalse(result.isBig());
    }

    @Test
    public void testIsBig() {
        ClientResult result = clientProcessor.process(testBigClient);
        assertTrue(result.isBig());
    }
}
