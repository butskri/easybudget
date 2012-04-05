package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.context.LocatorConfigurator;
import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commonweb.faces.util.FacesMessageFactory;

@RunWith(JUnit4ButskriClassRunner.class)
public class AbstractConverterTest {

	private static final String STRING_WAARDE = "STRING_WAARDE";
	private static final String NULL_STRING_WAARDE = "NULL_STRING_WAARDE";
	private static final Object OBJECT_WAARDE = new Object();

	private LocatorConfigurator locatorConfigurator;

	@TestedObject
	private AbstractConverter converter;

	@Mock
	@InjectIntoByType
	private SimpleConverter simpleConverterMock;
	@Mock
	private FacesContext facesContextMock;
	@Mock
	private ExternalContext externalContextMock;
	@Mock
	private UIComponent uiComponentMock;
	@Mock
	private FacesMessageFactory facesMessageFactoryMock;

	@Before
	public void setUp() {
		stub(facesContextMock.getExternalContext()).toReturn(externalContextMock);

		locatorConfigurator = new LocatorConfigurator();
		locatorConfigurator.configure(FacesMessageFactory.class, facesMessageFactoryMock);
	}

	@After
	public void tearDown() {
		locatorConfigurator.resetContext();
	}

	@Test
	public void getAsObjectGeeftNullTerugVoorLegeString() {
		assertNull(converter.getAsObject(facesContextMock, uiComponentMock, ""));

		verifyZeroInteractions(simpleConverterMock);
	}

	@Test
	public void getAsObjectGeeftNullTerugVoorStringMetEnkelSpaties() {
		assertNull(converter.getAsObject(facesContextMock, uiComponentMock, "  "));

		verifyZeroInteractions(simpleConverterMock);
	}

	@Test
	public void getAsObjectGeeftNullTerugVoorNullString() {
		assertNull(converter.getAsObject(facesContextMock, uiComponentMock, null));

		verifyZeroInteractions(simpleConverterMock);
	}

	@Test
	public void getAsObjectGeeftObjectWaardeTerugVoorStringWaarde() {
		stub(simpleConverterMock.stringToObject(STRING_WAARDE)).toReturn(OBJECT_WAARDE);

		assertEquals(OBJECT_WAARDE, converter.getAsObject(facesContextMock, uiComponentMock, STRING_WAARDE));
	}

	@Test
	public void getAsObjectGooitConverterExceptionIndienButskriRuntimeException() {
		FacesMessage facesMessage = new FacesMessage();
		ButskriRuntimeException runtimeException = new ButskriRuntimeException("");
		stub(simpleConverterMock.stringToObject(STRING_WAARDE)).toThrow(runtimeException);
		stub(facesMessageFactoryMock.createErrorMessage(uiComponentMock, runtimeException)).toReturn(facesMessage);

		try {
			converter.getAsObject(facesContextMock, uiComponentMock, STRING_WAARDE);
			fail();
		} catch (ConverterException expected) {
			assertSame(facesMessage, expected.getFacesMessage());
			assertSame(runtimeException, expected.getCause());
		}
	}

	@Test
	public void getAsStringGeeftStringWaardeIndienObjectNietNull() {
		stub(simpleConverterMock.objectToString(OBJECT_WAARDE)).toReturn(STRING_WAARDE);

		assertEquals(STRING_WAARDE, converter.getAsString(facesContextMock, uiComponentMock, OBJECT_WAARDE));
	}

	@Test
	public void getAsStringGeeftNullStringWaardeIndienObjectNull() {
		stub(simpleConverterMock.getNullString()).toReturn(NULL_STRING_WAARDE);

		assertEquals(NULL_STRING_WAARDE, converter.getAsString(facesContextMock, uiComponentMock, null));
	}

	@Test
	public void getNullStringGeeftLegeStringTerug() {
		assertEquals("", converter.getNullString());
	}
}
