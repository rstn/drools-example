package ru.peter.service.drools;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;
import ru.peter.service.drools.model.Subscriber;

public class ClientProcessorImpl implements ClientProcessor {
    public ClientResult process(Client client) {
        ClientResult result = new ClientResult();
        long sum = 0;
        for (Subscriber subscriber : client.getSubscribers()) {
            sum += subscriber.getSpent();
        }
        result.setSpentTotal(sum);
        result.setBig(client.getSubscribers().size() > 100);
        return result;
    }
}
