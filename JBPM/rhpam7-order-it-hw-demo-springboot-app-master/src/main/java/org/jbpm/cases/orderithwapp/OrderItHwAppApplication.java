package org.jbpm.cases.orderithwapp;

import lombok.extern.slf4j.Slf4j;
import org.kie.server.api.KieServerConstants;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerStatus;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.services.impl.storage.KieServerState;
import org.kie.server.services.impl.storage.file.KieServerStateFileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class OrderItHwAppApplication {
  private static String GROUP_ID = "itorders";
  private static String ARTIFACT_ID = "itorders";
  private static String VERSION = "7.59.0.Final";

  public static void main(String[] args) {
    String controller = System.getProperty(KieServerConstants.KIE_SERVER_CONTROLLER);
    log.info("controller = {}", controller);
    if (controller != null && !controller.isEmpty()) {
      log.info("Controller is configured (" + controller + ") - no local kjars can be installed");
      return;
    }

    // proceed only when kie server id is given and there is no controller

    KieServerStateFileRepository repository =
        new KieServerStateFileRepository(new File("/Users/aakash/Documents/data"));
    log.info("repository = {}", repository.toString());

    KieServerState currentState = repository.load(ARTIFACT_ID);
    log.info("KieServerState = {}", currentState.toString());

    Set<KieContainerResource> containers = new HashSet<>();

    ReleaseId releaseId = new ReleaseId(GROUP_ID, ARTIFACT_ID, VERSION);
    log.info("ReleaseId = {}", releaseId.getVersion());

    KieContainerResource container =
        new KieContainerResource(
            releaseId.getArtifactId() + "-" + releaseId.getVersion(),
            releaseId,
            KieContainerStatus.STARTED);
    containers.add(container);
    currentState.setContainers(containers);

    repository.store(ARTIFACT_ID, currentState);
    SpringApplication.run(OrderItHwAppApplication.class, args);
  }
}
