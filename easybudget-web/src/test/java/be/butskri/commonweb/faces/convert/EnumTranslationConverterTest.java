package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.context.LocatorConfigurator;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.util.EnumTranslator;
import be.butskri.commonweb.faces.convert.EnumTranslationConverter;

@RunWith(JUnit4ButskriClassRunner.class)
public class EnumTranslationConverterTest {

	private static final String ENUM_VERTALING = "enumVertaling";
	private static final Enum<?> ENUM = null;

	@Mock
	private EnumTranslator enumTranslator;
	@Mock
	private FacesContext facesContextMock;
	@Mock
	private UIComponent componentMock;

	private LocatorConfigurator locatorConfigurator;
	private EnumTranslationConverter converter;

	@Before
	public void setUp() {
		converter = new EnumTranslationConverter();

		stub(enumTranslator.translate(ENUM)).toReturn(ENUM_VERTALING);
		locatorConfigurator = new LocatorConfigurator();
		locatorConfigurator.configure(EnumTranslator.class, enumTranslator);
	}

	@After
	public void tearDown() {
		locatorConfigurator.resetContext();
	}

	@Test
	public void getAsStringVertaaltEnum() {
		assertEquals(ENUM_VERTALING, converter.getAsString(facesContextMock, componentMock, ENUM));
	}

}
