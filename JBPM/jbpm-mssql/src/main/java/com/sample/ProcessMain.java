package com.sample;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.model.Task;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProcessMain {

  public static void main(String[] args) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.getKieClasspathContainer();
    KieBase kbase = kContainer.getKieBase("kbase");

    RuntimeManager manager = createRuntimeManager(kbase);
    RuntimeEngine engine = manager.getRuntimeEngine(null);
    KieSession ksession = engine.getKieSession();
    ksession.startProcess("com.minerva.bpmn.helloworld");

    System.out.println("processId=" + engine.getKieSession().getProcessInstances().size());
    // ksession.getId()
    int processInstanceId = engine.getKieSession().getId();
    System.out.println("processInstanceId=" + processInstanceId);
    // engine.getTaskService().claimNextAvailable("john","java");
    Task taskId = engine.getTaskService().getTaskByWorkItemId(processInstanceId);
    //	System.out.println("taskId="+taskId.getId());

    //	manager.disposeRuntimeEngine(engine);
    // System.exit(0);
  }

  private static RuntimeManager createRuntimeManager(KieBase kbase) {
    // JBPMHelper.startH2Server();
    PoolingDataSource ds = new PoolingDataSource();
    ds.setUniqueName("jdbc/jbpm-ds");
    ds.setClassName("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
    ds.setMinPoolSize(0);
    ds.setMaxPoolSize(5);
    ds.setAllowLocalTransactions(true);
    // ds.setDriverProperties(new Properties());
    // ds.getDriverProperties().put("user", "sa");
    // ds.getDriverProperties().put("password", "msSQL123");
    // ds.getDriverProperties().put("URL", "jdbc:jtds:sqlserver://master:1433/master");
    //ds.getDriverProperties().put("driver_class","com.microsoft.sqlserver.jdbc.SQLServerDriver");
    ds.getDriverProperties()
        .put(
            "URL",
            "jdbc:sqlserver://localhost:51433;databaseName=jbpm;user=sa;password={msSQL123};");

    ds.init();
    Map map = new HashMap<>();
    map.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
    map.put("hibernate.show_sql", "true");

    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa", map);
    RuntimeEnvironmentBuilder builder =
        RuntimeEnvironmentBuilder.Factory.get()
            .newDefaultBuilder()
            .entityManagerFactory(emf)
            .knowledgeBase(kbase);
    return RuntimeManagerFactory.Factory.get()
        .newSingletonRuntimeManager(builder.get(), "com.sample:example:1.0");
  }
}
