/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;

import DAO.CodigoRecuperacaoDAO;
import DAO.UsuarioDAO;
import Modelo.Usuario;
import Util.EmailUtil;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.MessagingException;

import java.sql.SQLException;
import java.security.SecureRandom;

@WebServlet(name = "RecuperarSenhaServlet", urlPatterns = {"/RecuperarSenhaServlet"})
public class RecuperarSenhaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            String email = request.getParameter("email");
            
            if (email == null || email.trim().isEmpty()) {
                
                response.sendRedirect("/PCP/RecuperarSenha.jsp");
                return;
            }
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.BuscarUsuarioPorEmail(email);
            
            if (usuario == null) {
                
                response.sendRedirect("/PCP/RecuperarSenha.jsp");
                return;
            }
            
            String codigo = GerarCodigo();
            
            CodigoRecuperacaoDAO CodigoDAO = new CodigoRecuperacaoDAO();
            CodigoDAO.SalvarCodigo(email, codigo);
            
            EmailUtil.EnviarCodigo(email, codigo);
            
            response.sendRedirect("/PCP/ConfirmarCodigo.jsp");
            
        } catch (SQLException | MessagingException e) {
            
            e.printStackTrace();
            response.sendRedirect("/PCP/RecuperarSenha.jsp");
        }
    }
    
    private String GerarCodigo() {
        
        SecureRandom random = new SecureRandom();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
}
