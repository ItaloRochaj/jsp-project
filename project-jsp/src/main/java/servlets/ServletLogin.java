package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


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
		
		
		String login = request.getParameter("login");  
		String senha = request.getParameter("senha"); 
				
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(modelLogin.getLogin().equalsIgnoreCase("admin") /*Simulando login*/
				&& modelLogin.getSenha().equalsIgnoreCase("admin")){
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
					redirecionar.forward(request, response);
					
					
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente para acessar!");
					redirecionar.forward(request, response);
				}
			
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente para acessar!");
			redirecionar.forward(request, response);
		}
		
		
	}
	

}
