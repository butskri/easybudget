package be.butskri.commonweb.faces.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commonweb.util.MessageUtil;

@RunWith(JUnit4ButskriClassRunner.class)
public class FacesMessageFactoryTest {

	private static final String ID = "myId";
	private static final String LABEL = "myLabel";

	private static final String MESSAGE_KEY = "myErrorMessageKey";
	private static final String GENERATED_SUMMARY_MESSAGE = "myGeneratedSummaryMessage";
	private static final String GENERATED_DETAIL_MESSAGE = "myGeneratedDetailMessage";
	private static final Object MESSAGE_PARAM1 = new Object();
	private static final Object MESSAGE_PARAM2 = new Object();

	@Mock
	private MessageUtil messageUtilMock;
	@Mock
	private FacesUtil facesUtilMock;
	@Mock
	private UIComponent uiComponentMock;

	private FacesMessageFactory facesMessageFactory;

	@Before
	public void setUp() {
		facesMessageFactory = new FacesMessageFactory();
		facesMessageFactory.setFacesUtil(facesUtilMock);
		facesMessageFactory.setMessageUtil(messageUtilMock);

		stub(uiComponentMock.getId()).toReturn(ID);
		stub(facesUtilMock.getLabel(uiComponentMock)).toReturn(LABEL);
		stub(messageUtilMock.getDetailMessage(MESSAGE_KEY, LABEL, MESSAGE_PARAM1, MESSAGE_PARAM2)).toReturn(
				GENERATED_DETAIL_MESSAGE);
		stub(messageUtilMock.getSummaryMessage(MESSAGE_KEY, LABEL, MESSAGE_PARAM1, MESSAGE_PARAM2)).toReturn(
				GENERATED_SUMMARY_MESSAGE);
	}

	@Test
	public void createErrorMessageVoorComponentMaaktFacesMessageVanSeverityErrorAanMetJuisteMessage() {
		FacesMessage errorMessage = facesMessageFactory.createErrorMessage(uiComponentMock, MESSAGE_KEY,
				MESSAGE_PARAM1, MESSAGE_PARAM2);
		assertEquals(FacesMessage.SEVERITY_ERROR, errorMessage.getSeverity());
		assertEquals(GENERATED_DETAIL_MESSAGE, errorMessage.getDetail());
		assertEquals(GENERATED_SUMMARY_MESSAGE, errorMessage.getSummary());
	}

	@Test
	public void createErrorMessageVoorButskriRuntimeExceptionMaaktFacesMessageVanSeverityErrorAanMetJuisteMessage() {
		ButskriRuntimeException exception = new ButskriRuntimeException(MESSAGE_KEY, MESSAGE_PARAM1, MESSAGE_PARAM2);
		FacesMessage errorMessage = facesMessageFactory.createErrorMessage(uiComponentMock, exception);
		assertEquals(FacesMessage.SEVERITY_ERROR, errorMessage.getSeverity());
		assertEquals(GENERATED_DETAIL_MESSAGE, errorMessage.getDetail());
		assertEquals(GENERATED_SUMMARY_MESSAGE, errorMessage.getSummary());
	}

}
