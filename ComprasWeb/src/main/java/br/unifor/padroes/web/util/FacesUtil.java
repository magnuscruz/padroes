package br.unifor.padroes.web.util;

import static javax.faces.application.FacesMessage.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(SEVERITY_ERROR, message, null));
	}

	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(SEVERITY_INFO, message, null));
	}

}
