package org.jbpm.cases.orderithwapp.addon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.server.api.model.KieContainerStatus;
import org.kie.server.services.api.ContainerLocator;
import org.kie.server.services.api.KieContainerInstance;


public class SelectiveContainerLocator implements ContainerLocator {

    @Override
    public String locateContainer(String alias, List<? extends KieContainerInstance> containerInstances) {
        if (containerInstances.isEmpty()) {
            return alias;
        }
        
        String selectedVersion = System.getProperty("itorders.version", "1.0.0-SNAPSHOT");
        Map<String, String> versionToIdentifier = new HashMap<String, String>();
        containerInstances.forEach(c ->  {
                    if (c.getStatus().equals(KieContainerStatus.STARTED)) {                        
                        versionToIdentifier.put(c.getKieContainer().getReleaseId().getVersion(), c.getContainerId());
                    }
                }
        );
        return versionToIdentifier.get(selectedVersion);
    }

}
