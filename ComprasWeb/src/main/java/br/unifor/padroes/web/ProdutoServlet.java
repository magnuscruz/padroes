package br.unifor.padroes.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unifor.padroes.entidades.Produto;
import br.unifor.padroes.servicelocator.ServiceLocator;
import br.unifor.padroes.servicelocator.impl.FactoryServiceLocator;
import br.unifor.padroes.services.ProdutoService;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoService service;
	private static ServiceLocator instance= FactoryServiceLocator.getInstance("compras");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoServlet() {
		super();
		service = (ProdutoService) instance.getService(ProdutoService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		produto.setNome("CARRO");
		produto.setValor(100.0);
		service.inserir(produto);
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		pw.println("PRODUTO INSERIDO COM SUCESSO!!");
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
