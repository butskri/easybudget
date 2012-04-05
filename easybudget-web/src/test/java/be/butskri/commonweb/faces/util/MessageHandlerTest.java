package be.butskri.commonweb.faces.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.binding.message.DefaultMessageContext;
import org.springframework.binding.message.Message;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.Severity;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class MessageHandlerTest {

	private static final String MSG_KEY = "errorKey";
	private static final Object[] MSG_PARAMS = { "param1", "param2" };

	private MessageContext messageContext;
	private MessageHandler messageHandler;
	@Mock
	private RequestContext requestContextMock;

	@Before
	public void setUp() throws Exception {
		messageHandler = new MessageHandler();

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("TestMessage");
		messageContext = new DefaultMessageContext(messageSource);

		RequestContextHolder.setRequestContext(requestContextMock);
		stub(requestContextMock.getMessageContext()).toReturn(messageContext);
	}

	@Test
	public void addErrorMessageVoegtMessageMetErrorSeverityToeAanContext() {
		messageHandler.addError(MSG_KEY, MSG_PARAMS);

		Message[] messages = messageContext.getAllMessages();
		assertEquals("param1 test param2", messages[0].getText());
		assertEquals(Severity.ERROR, messages[0].getSeverity());
	}

	@Test
	public void addErrorMessageVoegtMessageMetErrorSeverityToeAanContextVoorBepaaldeComponent() {
		messageHandler.addErrorForComponent("compId", MSG_KEY, MSG_PARAMS);

		Message[] messages = messageContext.getAllMessages();
		assertEquals("param1 test param2", messages[0].getText());
		assertEquals(Severity.ERROR, messages[0].getSeverity());
		assertEquals(MessageHandler.JSF_FORM_PREFIX + "compId", messages[0].getSource());
	}

	@Test
	public void addWarningMessageVoegtMessageMetWarningSeverityToeAanContext() {
		messageHandler.addWarning(MSG_KEY, MSG_PARAMS);

		Message[] messages = messageContext.getAllMessages();
		assertEquals("param1 test param2", messages[0].getText());
		assertEquals(Severity.WARNING, messages[0].getSeverity());

	}

	@Test
	public void addInfoMessageVoegtMessageMetInfoSeverityToeAanContext() {
		messageHandler.addInfo(MSG_KEY, MSG_PARAMS);

		Message[] messages = messageContext.getAllMessages();
		assertEquals("param1 test param2", messages[0].getText());
		assertEquals(Severity.INFO, messages[0].getSeverity());
	}

}
