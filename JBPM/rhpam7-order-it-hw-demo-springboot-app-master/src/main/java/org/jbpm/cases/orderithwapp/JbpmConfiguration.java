//package org.jbpm.cases.orderithwapp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.kie.server.api.model.KieContainerResource;
//import org.kie.server.api.model.KieContainerStatus;
//import org.kie.server.api.model.ReleaseId;
//import org.kie.server.services.api.KieServer;
//import org.kie.server.services.impl.KieServerImpl;
//import org.kie.server.services.impl.storage.KieServerState;
//import org.kie.server.services.impl.storage.file.KieServerStateFileRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.Set;
//
//@Slf4j
//@Configuration
//public class JbpmConfiguration {
//
//  private static String GROUP_ID = "itorders";
//  private static String ARTIFACT_ID = "itorders";
//  private static String VERSION = "7.59.0.Final";
//
//  @Autowired
//  KieServer kieServer;
//
//  @Primary
//  @Bean
//  public KieServerStateFileRepository getKieServerStateFileRepository() {
//
//    final KieServerStateFileRepository repository =
//        new KieServerStateFileRepository(new File("/Users/aakash/Documents/data"));
//    log.info("repository = {}", repository.toString());
//
//    KieServerState currentState = repository.load(ARTIFACT_ID);
//    log.info("KieServerState = {}", currentState.toString());
//
//    Set<KieContainerResource> containers = new HashSet<>();
//
//    ReleaseId releaseId = new ReleaseId(GROUP_ID, ARTIFACT_ID, VERSION);
//    log.info("ReleaseId = {}", releaseId.getVersion());
//
//    KieContainerResource container =
//        new KieContainerResource(
//            releaseId.getArtifactId() + "-" + releaseId.getVersion(),
//            releaseId,
//            KieContainerStatus.STARTED);
//    containers.add(container);
//    currentState.setContainers(containers);
//
//    repository.store(ARTIFACT_ID, currentState);
//    kieServer.getServerState().getResult().getContainers().add(container);
//    return repository;
//  }
//}
