package ru.peter.service.drools;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

public class DroolsClientProcessor implements ClientProcessor {

    private final DroolsEngine droolsEngine = new DroolsEngine();

    public ClientResult process(Client client) {
        DroolsClient droolsClient = new DroolsClient(client, new ClientResult());
        droolsEngine.getSession().insert(droolsClient);
        droolsEngine.getSession().fireAllRules();
        return droolsClient.getClientResult();
    }
}
