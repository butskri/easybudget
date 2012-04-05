package be.butskri.commonweb.faces.functions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextReplacer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class FunctionsTest {

	private FacesContext originalFacesContext;
	private Map<String, String> requestParameterMap = new HashMap<String, String>();

	@Mock
	private FacesContext facesContextMock;
	@Mock
	private ExternalContext externalContextMock;

	@Before
	public void setUp() {
		stub(facesContextMock.getExternalContext()).toReturn(externalContextMock);
		stub(externalContextMock.getRequestParameterMap()).toReturn(requestParameterMap);

		originalFacesContext = FacesContextReplacer.replaceFacesContext(facesContextMock);
	}

	@After
	public void tearDown() {
		FacesContextReplacer.replaceFacesContext(originalFacesContext);
	}

	@Test
	public void fieldRequiredGeeftTrueIndienIgnoreRequirednessFalseEnRequiredTrue() {
		requestParameterMap.put(Functions.IGNORE_REQUIREDNESS, "false");

		assertTrue(Functions.fieldRequired("true"));
	}

	@Test
	public void fieldRequiredGeeftTrueIndienIgnoreRequirednessFalseEnRequiredTrueBoolean() {
		requestParameterMap.put(Functions.IGNORE_REQUIREDNESS, "false");

		assertTrue(Functions.fieldRequired(true));
	}

	@Test
	public void fieldRequiredGeeftTrueIndienIgnoreRequirednessNietIngevuldEnRequiredTrueBoolean() {
		assertTrue(Functions.fieldRequired("true"));
	}

	@Test
	public void fieldRequiredGeeftFalseIndienIgnoreRequirednessTrue() {
		requestParameterMap.put(Functions.IGNORE_REQUIREDNESS, "true");

		assertFalse(Functions.fieldRequired("true"));
		assertFalse(Functions.fieldRequired(true));
	}

	@Test
	public void fieldRequiredGeeftFalseIndienRequiredFalse() {
		assertFalse(Functions.fieldRequired("false"));
		assertFalse(Functions.fieldRequired(false));
	}

	@Test
	public void fieldRequiredGeeftFalseIndienRequiredNull() {
		assertFalse(Functions.fieldRequired(null));
	}

}
