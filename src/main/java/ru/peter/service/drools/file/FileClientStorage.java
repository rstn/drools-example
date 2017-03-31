package ru.peter.service.drools.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import ru.peter.service.drools.api.ClientStorage;
import ru.peter.service.drools.api.LoadClientException;
import ru.peter.service.drools.api.StoreClientException;
import ru.peter.service.drools.model.Client;
import ru.peter.service.drools.model.ClientResult;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class FileClientStorage implements ClientStorage {

    private static final String OTPUT_FILE_TEMPLATE = "\\%d.json";

    private ObjectMapper mapper = new ObjectMapper();

    private File[] inboxFiles;
    private File outboxDir;
    private int currentInboxPos = -1;

    public FileClientStorage(File inboxDirectory, File outboxDir) {
        checkNotNull(inboxDirectory, "Директория inbox не задана");
        checkNotNull(outboxDir, "Директория outbox не задана");
        //TODO добавить другие проверки

        this.inboxFiles = inboxDirectory.listFiles((File f, String name) -> name.matches("^\\d*.json$"));
        this.outboxDir = outboxDir;
    }

    @Override
    public boolean hasNextClient() {
        return currentInboxPos + 1 < inboxFiles.length;
    }

    @Override
    public Client loadNextClient() throws LoadClientException {
        if (currentInboxPos+1 == inboxFiles.length) {
            throw new RuntimeException("Входных файлов с клиентами больше нет");
        }

        currentInboxPos++;
        try {
            return mapper.readValue(inboxFiles[currentInboxPos], Client.class);
        } catch (IOException ex) {
            throw new LoadClientException("Не возможно прочитать входной файл", ex);
        }
    }

    @Override
    public void storeClientResult(ClientResult clientResult) throws StoreClientException {
        File output = new File(outboxDir, String.format(OTPUT_FILE_TEMPLATE, clientResult.getClientId()));
        try {
            mapper.writeValue(output, clientResult);
        } catch (IOException ex) {
            throw new StoreClientException("Не удалось записать результаты в файл", ex);
        }
    }
}
