/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;

import DAO.CodigoRecuperacaoDAO;
import DAO.UsuarioDAO;
import Util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
/**
 *
 * @author edsonmigueljunior
 */
@WebServlet(name = "ConfirmarCodigoServlet", urlPatterns = {"/ConfirmarCodigoServlet"})
public class ConfirmarCodigoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            String email = request.getParameter("email");
            String codigo = request.getParameter("codigo");
            String novaSenha = request.getParameter("novaSenha");
            
            if (email    == null || email.trim().isEmpty() ||
                codigo   == null || codigo.trim().isEmpty() ||
                novaSenha == null || novaSenha.trim().isEmpty()) {

                response.sendRedirect("/PCP/ConfirmarCodigo.jsp");
                return;
            }
            
            CodigoRecuperacaoDAO CodigoDAO = new CodigoRecuperacaoDAO();
            boolean CodigoValido = CodigoDAO.ValidarCodigo(email, codigo);
            
            if (!CodigoValido) {
                
                response.sendRedirect("/PCP/ConfirmarCodigo.jsp");
                return;
            }
            
            String novaSenhaHash = PasswordUtil.SenhaHash(novaSenha);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.AtualizarSenhaEmail(email, novaSenhaHash);
            
            response.sendRedirect("/PCP/LoginUsuario.jsp");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            response.sendRedirect("/PCP/ConfirmarCodigo.jsp");
        }
    }
}
