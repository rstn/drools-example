package ru.peter.service.drools;

import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

public class DroolsClientProcessor implements ClientProcessor {

    private final DroolsEngine droolsEngine = new DroolsEngine();

    public ClientResult process(Client client) {
        ClientResult clientResult = new ClientResult();
        clientResult.setClientId(client.getClientId());

        DroolsClient droolsClient = new DroolsClient(client, clientResult);
        droolsEngine.getSession().insert(droolsClient);
        droolsEngine.getSession().fireAllRules();
        return droolsClient.getClientResult();
    }
}
