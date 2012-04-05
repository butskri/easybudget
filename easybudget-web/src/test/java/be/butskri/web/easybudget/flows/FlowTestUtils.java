package be.butskri.web.easybudget.flows;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;

public class FlowTestUtils {

	private static final String FLOW_LOCATION = "src/main/webapp/WEB-INF/flows/";

	public static FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory, String flowLocation) {
		return resourceFactory.createFileResource(FLOW_LOCATION + flowLocation);
	}

	public static FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
		FlowDefinitionResource[] resources = new FlowDefinitionResource[1];
		resources[0] = getResource(resourceFactory, "parentFlow.xml");
		return resources;
	}

}
