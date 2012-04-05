package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commonweb.util.MessageUtil;

@RunWith(JUnit4ButskriClassRunner.class)
public class ConverterExceptionFactoryTest {

	private static final String LABEL = "myLabel";
	private static final String DETAIL_MESSAGE = "myDetailMessage";
	private static final String SUMMARY_MESSAGE = "mySummaryMessage";

	private static final String MESSAGE_ID = "messageId";
	private static final Object[] PARAMS = new Object[] { new Object(), new Object() };

	@Mock
	private MessageUtil messageUtilMock;

	private ConverterExceptionFactory factory;

	@Before
	public void setUp() {
		factory = new ConverterExceptionFactory();
		factory.setMessageUtil(messageUtilMock);
	}

	@Test
	public void createMaaktConverterExceptionMetJuisteFacesMessageAan() {
		ButskriRuntimeException exception = new ButskriRuntimeException(MESSAGE_ID, PARAMS);

		stub(messageUtilMock.getDetailMessage(MESSAGE_ID, LABEL, PARAMS)).toReturn(DETAIL_MESSAGE);
		stub(messageUtilMock.getSummaryMessage(MESSAGE_ID, LABEL, PARAMS)).toReturn(SUMMARY_MESSAGE);

		ConverterException converterException = factory.create(LABEL, exception);
		FacesMessage facesMessage = converterException.getFacesMessage();
		assertEquals(FacesMessage.SEVERITY_ERROR, facesMessage.getSeverity());
		assertEquals(DETAIL_MESSAGE, facesMessage.getDetail());
		assertEquals(SUMMARY_MESSAGE, facesMessage.getSummary());
		assertEquals(exception, converterException.getCause());
	}

}
