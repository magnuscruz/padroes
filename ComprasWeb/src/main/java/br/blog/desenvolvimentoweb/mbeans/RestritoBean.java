package br.blog.desenvolvimentoweb.mbeans;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.blog.desenvolvimentoweb.util.Login;

@Login
@Named
@ViewScoped
public class RestritoBean implements Serializable {

	/**
	 * serialVersionUID - {@link long}
	 */
	private static final long serialVersionUID = 2160406798630612080L;

	public String index() {
		return "sucesso";
	}

}
