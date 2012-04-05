package be.butskri.commonweb.faces.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commonweb.faces.util.FacesUtil;

@RunWith(JUnit4ButskriClassRunner.class)
public class FacesUtilTest {

	private static final String LABEL_VALUE = "labelValue";

	@Mock
	private UIComponent uiComponentMock;

	private FacesUtil facesUtil;

	@Before
	public void setUp() {
		facesUtil = new FacesUtil();
	}

	@Test
	public void getLabelGeeftWaardeVanLabelAttributeTerug() {
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("label", LABEL_VALUE);
		stub(uiComponentMock.getAttributes()).toReturn(attributes);

		assertEquals(LABEL_VALUE, facesUtil.getLabel(uiComponentMock));
	}
}
