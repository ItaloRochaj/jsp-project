package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletLogin() {
        super();
    }
    /*Recebe os dados pela url em parametros!*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/*Recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request, response);
		
		
		System.out.println(request.getParameter("nome"));  // Imprime o valor do parâmetro 'nome' no console do servidor
		System.out.println(request.getParameter("idade")); // Imprime o valor do parâmetro 'idade' no console do servidor
		
	}
	

}
