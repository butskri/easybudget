package be.butskri.commonweb.faces.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextReplacer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.util.Locales;

@RunWith(JUnit4ButskriClassRunner.class)
public class FacesLocaleResolverTest {

	private static final Locale LOCALE = Locales.FR;

	@Mock
	private FacesContext facesContextMock;
	@Mock
	private UIViewRoot uiViewRootMock;

	private FacesContext originalFacesContext;

	private FacesLocaleResolver facesLanguageBean;

	@Before
	public void setUp() {
		facesLanguageBean = new FacesLocaleResolver();

		stub(facesContextMock.getViewRoot()).toReturn(uiViewRootMock);
		originalFacesContext = FacesContextReplacer.replaceFacesContext(facesContextMock);
	}

	@After
	public void tearDown() {
		FacesContextReplacer.replaceFacesContext(originalFacesContext);
	}

	@Test
	public void getLocaleGeeftLocaleTerugVanDeViewRoot() {
		stub(uiViewRootMock.getLocale()).toReturn(LOCALE);

		assertEquals(LOCALE, facesLanguageBean.getLocale());
	}

	@Test
	public void setLocaleSetLocaleOpDeViewRoot() {
		facesLanguageBean.setLocale(LOCALE);

		verify(uiViewRootMock).setLocale(LOCALE);
		verifyNoMoreInteractions(uiViewRootMock);
	}
}
