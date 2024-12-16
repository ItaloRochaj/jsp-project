package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = {"/principal/*"}) /* Realiza o mapeamento das requisições. */
public class FilterAutenticacao extends HttpFilter implements Filter {
    
    private static Connection connection;
    
    public FilterAutenticacao() {
        super();
    }

    /* Encerra os processos quando o servidor é parado. */
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar de forma adequada ou registrar o erro.
        }
    }

    /* Intercepta as requisições e as respostas do sistema. */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        
        String usuarioLogado = (String) session.getAttribute("usuario");
        String urlParaAutenticar = req.getServletPath(); /* Url que está sendo acessada. */
        
        // Verifica se o usuário está logado, senão redireciona para a tela de login
        if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { /* Não logado */
            RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
            request.setAttribute("msg", "Por favor realize o login!");
            redireciona.forward(request, response);
            
            return; /* Retorna para efetuar o login */
            
        } else {
            try {
                // Executa a cadeia de filtros se o usuário estiver logado
                chain.doFilter(request, response);
                
                ((FilterChain) connection).doFilter(request, response); /*Seguindo tudo bem a conecxão é estabelecida.*/
               
            } catch (Exception e) {
                e.printStackTrace();
                // Caso haja erro, fazer rollback
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /* Inicia os processos ou recursos quando o servidor inicia o projeto. */
    /* Inicia a conexão com o banco de dados. */
    public void init(FilterConfig fConfig) throws ServletException {
        connection = SingleConnectionBanco.getConnection();
    }
}
