/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;


import DAO.UsuarioDAO;
import Modelo.Usuario;
import Util.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author edsonmigueljunior
 */
@WebServlet(name = "AtualizarUsuarioServlet", urlPatterns = {"/AtualizarUsuarioServlet"})
public class AtualizarUsuarioServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            HttpSession sessao = request.getSession(false);
            Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
            
            String novoUsername = request.getParameter("username");
            String novoEmail = request.getParameter("email");
            String senhaAtual = request.getParameter("senhaAtual");
            String novaSenha = request.getParameter("novaSenha");
            
            if (novoUsername == null || novoUsername.trim().isEmpty() ||
                    novoEmail == null || novoEmail.trim().isEmpty() ||
                    senhaAtual == null || senhaAtual.trim().isEmpty() ||
                    novaSenha == null || novaSenha.trim().isEmpty()) {
                
                response.sendRedirect("/PCP/Usuario.jsp");
                return;
            }
            
            if (!PasswordUtil.checkSenha(senhaAtual, usuarioLogado.getSenhaHash())) {
                
                response.sendRedirect("/PCP/Usuario.jsp");
                return;
            }
            
            String novaSenhaHash = PasswordUtil.SenhaHash(novaSenha);
            
            Usuario usuarioAtualizado = new Usuario.Builder()
                    .setUsername(novoUsername)
                    .setEmail(novoEmail)
                    .setSenhaHash(novaSenhaHash)
                    .setRole(usuarioLogado.getRole())
                    .Build();
            
            UsuarioDAO dao = new UsuarioDAO();
            dao.AtualizarUsuario(usuarioLogado, usuarioAtualizado);
            
            sessao.setAttribute("usuarioLogado", usuarioAtualizado);
            
            response.sendRedirect("/PCP/Usuario.jsp");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            response.sendRedirect("/PCP/Usuario.");
        }
    }// </editor-fold>

}
