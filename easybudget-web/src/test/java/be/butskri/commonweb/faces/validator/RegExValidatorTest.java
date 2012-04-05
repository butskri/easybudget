package be.butskri.commonweb.faces.validator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.context.LocatorConfigurator;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commonweb.faces.util.FacesMessageFactory;
import be.butskri.commonweb.faces.validator.RegExPattern;
import be.butskri.commonweb.faces.validator.RegExValidator;

@RunWith(JUnit4ButskriClassRunner.class)
public class RegExValidatorTest {

	private static final RegExPattern PATTERN = RegExPattern.ALFANUMERIEK;
	private static final String VALID_INPUT = "DitIsGeldigeInput";
	private static final String INVALID_INPUT = "OngeldigeInput;";

	@Mock
	private FacesMessageFactory facesMessageFactoryMock;
	@Mock
	private UIComponent uiComponentMock;

	private FacesMessage facesMessage;
	private FacesContext facesContextFake;

	private LocatorConfigurator locatorConfigurator;
	private RegExValidator regExValidator;

	@Before
	public void setUp() {
		facesMessage = new FacesMessage();
		stub(facesMessageFactoryMock.createErrorMessage(uiComponentMock, PATTERN.getMessage(), INVALID_INPUT))
				.toReturn(facesMessage);

		locatorConfigurator = new LocatorConfigurator();
		locatorConfigurator.configure(FacesMessageFactory.class, facesMessageFactoryMock);

		regExValidator = new RegExValidator();
		regExValidator.setPattern(PATTERN.name());
	}

	@After
	public void tearDown() {
		locatorConfigurator.resetContext();
	}

	@Test
	public void validateGooitValidatorExceptionIndienInputOngeldig() {
		try {
			regExValidator.validate(facesContextFake, uiComponentMock, INVALID_INPUT);
			fail();
		} catch (ValidatorException expected) {
			assertSame(facesMessage, expected.getFacesMessage());
		}
	}

	@Test
	public void validateGooitGeenExceptionIndienInputGeldig() {
		regExValidator.validate(facesContextFake, uiComponentMock, VALID_INPUT);

		verifyZeroInteractions(facesMessageFactoryMock);
	}
}
