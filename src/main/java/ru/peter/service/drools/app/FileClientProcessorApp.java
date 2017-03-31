package ru.peter.service.drools.app;

import ru.peter.service.drools.api.ClientStorage;
import ru.peter.service.drools.api.ClientProcessor;
import ru.peter.service.drools.api.LoadClientException;
import ru.peter.service.drools.api.StoreClientException;
import ru.peter.service.drools.file.FileClientStorage;
import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;
import ru.peter.service.drools.processor.ClientProcessorImpl;
import ru.peter.service.drools.processor.DroolsClientProcessor;

import java.io.File;

public class FileClientProcessorApp {

    private ClientStorage clientStorage;

    private ClientProcessor clientProcessor;

    public static void main(String[] args) {
        FileClientProcessorApp app = new FileClientProcessorApp();
        app.startProcessing();
    }

    public void startProcessing() {
        clientStorage = new FileClientStorage(new File("inbox"), new File("outbox"));
        clientProcessor = new DroolsClientProcessor();

        while (clientStorage.hasNextClient()){
            try {
                Client client = clientStorage.loadNextClient();
                ClientResult result = clientProcessor.process(client);
                clientStorage.storeClientResult(result);
            } catch (LoadClientException e) {
                e.printStackTrace();
            } catch (StoreClientException e) {
                e.printStackTrace();
            }
        }
    }
}
