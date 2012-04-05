package be.butskri.commonweb.util;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.util.LanguageBean;
import be.butskri.commons.util.Locales;

@RunWith(JUnit4ButskriClassRunner.class)
public class MessageUtilTest {

	private static final String MESSAGE_KEY = "messageKey";
	private static final String LABEL = "theLabel";
	private static final Object OBJECT_PARAM1 = new Object();
	private static final Object OBJECT_PARAM2 = new Object();
	private static final Locale LOCALE = Locales.FR;

	private static final String VALUE = "theValue";

	@Mock
	private LanguageBean languageBeanMock;
	@Mock
	private MessageSource messageSourceMock;

	private MessageUtil messageUtil;

	@Before
	public void setUp() {
		messageUtil = new MessageUtil();
		messageUtil.setLanguageBean(languageBeanMock);
		messageUtil.setMessageSource(messageSourceMock);

		stub(languageBeanMock.getLocale()).toReturn(LOCALE);
	}

	@Test
	public void getDetailMessageGeeftDetailMessageTerugAlsDezeBestaat() {
		stub(
				messageSourceMock.getMessage(MESSAGE_KEY + "_detail", new Object[] { LABEL, OBJECT_PARAM1,
						OBJECT_PARAM2 }, LOCALE)).toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getDetailMessage(MESSAGE_KEY, LABEL, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getDetailMessageGeeftMessageTerugAlsDetailMessageNietBestaat() {
		stub(messageSourceMock.getMessage(eq(MESSAGE_KEY + "_detail"), (Object[]) anyObject(), eq(LOCALE))).toThrow(
				new NoSuchMessageException(""));
		stub(messageSourceMock.getMessage(MESSAGE_KEY, new Object[] { LABEL, OBJECT_PARAM1, OBJECT_PARAM2 }, LOCALE))
				.toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getDetailMessage(MESSAGE_KEY, LABEL, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getSummaryMessageGeeftSummaryMessageTerugAlsDezeBestaat() {
		stub(
				messageSourceMock.getMessage(MESSAGE_KEY + "_summary", new Object[] { LABEL, OBJECT_PARAM1,
						OBJECT_PARAM2 }, LOCALE)).toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getSummaryMessage(MESSAGE_KEY, LABEL, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getSummaryMessageGeeftMessageTerugAlsSummaryMessageNietBestaat() {
		stub(messageSourceMock.getMessage(eq(MESSAGE_KEY + "_summary"), (Object[]) anyObject(), eq(LOCALE))).toThrow(
				new NoSuchMessageException(""));
		stub(messageSourceMock.getMessage(MESSAGE_KEY, new Object[] { LABEL, OBJECT_PARAM1, OBJECT_PARAM2 }, LOCALE))
				.toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getSummaryMessage(MESSAGE_KEY, LABEL, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getMessageGeeftGeformatteerdeMessageTerug() {
		stub(messageSourceMock.getMessage(MESSAGE_KEY, new Object[] { OBJECT_PARAM1, OBJECT_PARAM2 }, LOCALE))
				.toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getMessage(MESSAGE_KEY, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getMessageMetLabelGeeftGeformatteerdeMessageTerug() {
		stub(messageSourceMock.getMessage(MESSAGE_KEY, new Object[] { LABEL, OBJECT_PARAM1, OBJECT_PARAM2 }, LOCALE))
				.toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getMessageMetLabel(MESSAGE_KEY, LABEL, OBJECT_PARAM1, OBJECT_PARAM2));
	}

	@Test
	public void getMessageMetLabelMetNullArrayGeeftGeformatteerdeMessageTerug() {
		stub(messageSourceMock.getMessage(MESSAGE_KEY, new Object[] { LABEL }, LOCALE)).toReturn(VALUE);

		assertEquals(VALUE, messageUtil.getMessageMetLabel(MESSAGE_KEY, LABEL, (Object[]) null));
	}
}
