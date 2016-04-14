package br.unifor.padroes.web.mbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

public class SistemaBean implements Serializable {

	private Integer pageSize = 5; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1504076767958111631L;
	
	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect( "./pages/index.xhtml");
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
