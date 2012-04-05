package be.butskri.web.easybudget.flows.nieuweuitgave;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.web.easybudget.GeldstroomBean;
import be.butskri.web.easybudget.flows.FlowTestUtils;

@RunWith(JUnit4ButskriClassRunner.class)
public class NieuweUitgaveFlowTest extends AbstractXmlFlowExecutionTests {

	@Mock
	private UitgaveController uitgaveControllerMock;

	private MockExternalContext context;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		context = new MockExternalContext();
	}

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return FlowTestUtils.getResource(resourceFactory, "nieuweUitgave/nieuweUitgave.xml");
	}

	@Override
	protected FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
		return FlowTestUtils.getModelResources(resourceFactory);
	}

	@Override
	protected final void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		builderContext.registerBean("uitgaveController", uitgaveControllerMock);
	}

	@Test
	public void flowStartInStateDetail() {
		startFlow(context);

		assertCurrentStateEquals("details");
	}

	@Test
	public void bewarenDelegeertNaarController() {
		setCurrentState("details");
		GeldstroomBean uitgaveBean = new GeldstroomBean();
		getFlowScope().put("uitgaveBean", uitgaveBean);

		context.setEventId("bewaren");
		resumeFlow(context);

		assertCurrentStateEquals("details");
		verify(uitgaveControllerMock).bewaren(uitgaveBean);
	}

	@Test
	public void categorieGeselecteerdDelegeertNaarController() {
		setCurrentState("details");
		GeldstroomBean uitgaveBean = new GeldstroomBean();
		getFlowScope().put("uitgaveBean", uitgaveBean);

		context.setEventId("categorieGeselecteerd");
		resumeFlow(context);

		assertCurrentStateEquals("details");
		verify(uitgaveControllerMock).categorieGeselecteerd(uitgaveBean);
	}
}
