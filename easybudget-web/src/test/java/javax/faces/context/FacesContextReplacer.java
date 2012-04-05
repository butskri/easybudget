package javax.faces.context;

public class FacesContextReplacer {

	public static FacesContext replaceFacesContext(FacesContext newFacesContext) {
		FacesContext originalFacesContext = FacesContext.getCurrentInstance();
		FacesContext.setCurrentInstance(newFacesContext);
		return originalFacesContext;
	}

}
