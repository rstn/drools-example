package ru.peter.service.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsEngine {

    private final KieSession session;

    public DroolsEngine() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        session = kContainer.newKieSession("ksession-rules");
    }

    public KieSession getSession() {
        return session;
    }
}
