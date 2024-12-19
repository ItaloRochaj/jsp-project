package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    public ServletLogin() {
        super();
    }

    /* Recebe os dados pela URL em parâmetros */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        doPost(request, response);
    }

    /* Recebe os dados enviados por um formulário */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        try {
            // Verifica se os parâmetros login e senha foram preenchidos
            if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
                ModelLogin modelLogin = new ModelLogin();
                modelLogin.setLogin(login);
                modelLogin.setSenha(senha);

                // Verifica se a autenticação foi bem-sucedida
                if (daoLoginRepository.validarAutenticacao(modelLogin)) {
                    request.getSession().setAttribute("usuario", modelLogin.getLogin());

                    // Se for um login de admin, simula o acesso e redireciona
                    if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
                        if (url == null || url.equals("null")) {
                            url = "principal/principal.jsp";
                        }

                        RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                        redirecionar.forward(request, response);
                    } else {
                        // Caso o login não seja de admin, redireciona para a página principal
                        RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("msg", "Login e senha inválidos. Tente novamente.");
                        redirecionar.forward(request, response);
                    }
                } else {
                    // Se a autenticação falhar, redireciona para a página de login com mensagem
                    RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("msg", "Login e senha inválidos. Tente novamente.");
                    redirecionar.forward(request, response);
                }
            } else {
                // Caso login ou senha não sejam preenchidos, redireciona com mensagem
                RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msg", "Por favor, informe o login e a senha.");
                redirecionar.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro, redireciona para a página de login
            RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg", "Ocorreu um erro ao processar a solicitação. Tente novamente.");
            redirecionar.forward(request, response);
        }
    }
}
