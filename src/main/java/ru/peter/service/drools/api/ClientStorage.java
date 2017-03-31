package ru.peter.service.drools.api;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

public interface ClientStorage {

    boolean hasNextClient();

    Client loadNextClient() throws LoadClientException;

    void storeClientResult(ClientResult clientResult) throws StoreClientException;
}
