package ru.peter.service.drools.processor;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

public class DroolsClient {

    private final Client client;

    private final ClientResult clientResult;

    public DroolsClient(Client client, ClientResult clientResult) {
        this.client = client;
        this.clientResult = clientResult;
    }

    public Client getClient() {
        return client;
    }

    public ClientResult getClientResult() {
        return clientResult;
    }
}
