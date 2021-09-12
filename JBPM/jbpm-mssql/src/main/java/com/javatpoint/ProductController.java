/*
 * package com.javatpoint; import java.util.List;
 * 
 * import org.kie.api.runtime.manager.Context; import
 * org.kie.internal.runtime.manager.context.EmptyContext; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.minerva.jbpm.WorkflowProcessMain; import
 * com.minerva.jbpm.engine.WorkflowEngine; import
 * com.minerva.jbpm.engine.WorkflowEngineImpl;
 * 
 * @RestController public class ProductController {
 * 
 * @Autowired private IProductService productService; //mapping the getProduct()
 * method to /product
 * 
 * @GetMapping(value = "/product") public List<Product> getProduct() {
 * 
 * WorkflowProcessMain wpm=new WorkflowProcessMain(); wpm.main(null); //finds
 * all the products
 * 
 * WorkflowEngine workflowEngine = new WorkflowEngineImpl(); //String processId
 * = "com.baeldung.bpmn.helloworld"; String processId =
 * "com.minerva.bpmn.helloworld"; String kbaseId = "kbase"; String
 * persistenceUnit = "org.jbpm.persistence.jpa"; Context<String> initialContext
 * = EmptyContext.get(); workflowEngine.runjBPMEngineForProcess(processId,
 * initialContext, kbaseId, persistenceUnit);
 * 
 * 
 * List<Product> products = productService.findAll(); //returns the product list
 * return products; } }
 */