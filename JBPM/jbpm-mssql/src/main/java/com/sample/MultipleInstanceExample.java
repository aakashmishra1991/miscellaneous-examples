//package com.sample;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.kie.api.KieServices;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.manager.RuntimeEngine;
//import org.kie.api.runtime.manager.RuntimeEnvironment;
//import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
//import org.kie.api.runtime.manager.RuntimeManager;
//import org.kie.api.runtime.manager.RuntimeManagerFactory;
//import org.kie.api.task.TaskService;
//import org.kie.api.task.model.TaskSummary;
//import org.kie.test.util.db.PersistenceUtil;
//
//public class MultipleInstanceExample {
//
//	public static final void main(String[] args) {
//		try {
//            RuntimeManager manager = getRuntimeManager("multipleinstance/multipleinstance.bpmn");
//            RuntimeEngine runtime = manager.getRuntimeEngine(null);
//            KieSession ksession = runtime.getKieSession();
//
//			// start a new process instance
//			Map<String, Object> params = new HashMap<String, Object>();
//			List<String> list = new ArrayList<String>();
//			list.add("krisv");
//			list.add("john doe");
//			list.add("superman");
//			params.put("list", list);
//			ksession.startProcess("com.sample.multipleinstance", params);
//
//            TaskService taskService = runtime.getTaskService();
//    		List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner("sales-rep", "en-UK");
//    		for (TaskSummary task: tasks) {
//	            System.out.println("Sales-rep executing task " + task.getName() + "(" + task.getId() + ": " + task.getDescription() + ")");
//	            taskService.start(task.getId(), "sales-rep");
//	            taskService.complete(task.getId(), "sales-rep", null);
//    		}
//
//            manager.disposeRuntimeEngine(runtime);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        System.exit(0);
//	}
//
//    private static RuntimeManager getRuntimeManager(String process) {
//        // load up the knowledge base
//        PersistenceUtil.setupPoolingDataSource();
//        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder()
//            .addAsset(KieServices.Factory.get().getResources().newClassPathResource(process), ResourceType.BPMN2)
//            .get();
//        return RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
//    }
//
//	private static RuntimeManager createRuntimeManager(KieBase kbase) {
//		JBPMHelper.startH2Server();
//		JBPMHelper.setupDataSource();
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
//		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
//				.newDefaultBuilder().entityManagerFactory(emf)
//				.knowledgeBase(kbase);
//		return RuntimeManagerFactory.Factory.get()
//				.newSingletonRuntimeManager(builder.get(), "com.sample:example:1.0");
//	}
//
//
//}