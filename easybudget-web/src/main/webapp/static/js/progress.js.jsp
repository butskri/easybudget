<%@ page contentType="text/javascript" %> 
<%--
Progress indicator JavaScript support.

Images can be preloaded by adding the following JavaScript call to your page:

	afterPageLoadedHandler.addEventListener(preloadImgs);

The (submit) button onclick handler should call showProgress().
The progress indicator image should have id 'progressIndicator'.

Only one progress indicator is supported per page.
However, more than one button is supported.
--%>

var progressCount=11;
INDICATOR_TIMEOUT_MILLIS=3000;

function onShowProgressTimeout() {
	var img = document.getElementById("progressIndicator");
	img.style.display="inline";
	img.src="<%=request.getContextPath()%>/static/images/progress/progress" + progressCount + ".gif";
	
	progressCount=(progressCount == 0 ? 11 : progressCount-1);
	
	setTimeout(onShowProgressTimeout, 100);
}

function disableButton(button) {
	button.disabled="true";
}

function showProgress(button, buttonLabel) {
	button.value=buttonLabel;
	setTimeout(function() { disableButton(button); }, 1);
	setTimeout(onShowProgressTimeout, INDICATOR_TIMEOUT_MILLIS);
}

function preloadImgs() {	   			
	for(var i=0; i<12; i++) {
		var img = new Image();
		img.src="<%=request.getContextPath()%>/static/images/progress/progress" + i + ".gif";
	}
}
