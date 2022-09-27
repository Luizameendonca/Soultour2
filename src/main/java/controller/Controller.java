package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans cliente = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			clientes(request, response);

		} else if (action.equals("/insert")) {
			novoCliente(request, response);
		}else {
			response.sendRedirect("index.html");
		}

	}

	

	protected void clientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ArrayList<JavaBeans> lista = dao.listarClientes();
		request.setAttribute("clientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
		rd.forward(request, response);


		}
		
	
	
	protected void novoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	System.out.println(request.getParameter("nome"));
	System.out.println(request.getParameter("email"));
	System.out.println(request.getParameter("senha"));
	
	cliente.setNome(request.getParameter("nome"));
	cliente.setEmail(request.getParameter("email"));
	cliente.setSenha(request.getParameter("senha"));
	
	// inserir contato
	
	dao.inserirCliente(cliente);
	
	response.sendRedirect("main"); 
	
	

} 
	protected void listarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	String id = request.getParameter("id");
	cliente.setId(id);
	dao.selecionarCliente(cliente);
	}
	protected void alterarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		cliente.setId(request.getParameter("id"));
		cliente.setNome(request.getParameter("nome"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setSenha(request.getParameter("senha"));
		
		dao.alterarCliente(cliente);
	}
	
	protected void removerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String id = request.getParameter("id");
		cliente.setId(id);
		dao.deletarCliente(cliente);
		
	}
	
}
