package ru.peter.service.drools;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

public interface ClientProcessor {

    ClientResult process(Client client);
}
