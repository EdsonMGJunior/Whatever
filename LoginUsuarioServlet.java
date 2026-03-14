/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Role;
import Util.PasswordUtil;
import Util.DBConexao;

import java.sql.SQLException;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginUsuarioServlet", urlPatterns = {"/LoginUsuarioServlet"})
public class LoginUsuarioServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("/PCP/LoginUsuario.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try{
            
            String username = request.getParameter("username");
            String senha = request.getParameter("senha");
            
            if (username == null || username.trim().isEmpty() || senha == null || senha.trim().isEmpty()){
                
                response.sendRedirect("/PCP/LoginUsuario.jsp");
                return;
            }
            
            UsuarioDAO DAO = new UsuarioDAO();
            Usuario usuario = DAO.BuscarUsuario(username);
            
            if (usuario == null || !PasswordUtil.checkSenha(senha, usuario.getSenhaHash())){
                
                response.sendRedirect("/PCP/LoginUsuario.jsp");
                return;
            }
            
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", usuario);
            sessao.setAttribute("perfil", usuario.getRole().name());
            
            if(usuario.getRole() == Role.Administrador) {
                
                response.sendRedirect("/PCP/CadastrarUsuario.jsp");
            } else {
                response.sendRedirect("/PCP/Usuario.jsp");
            }
            return;
            
        }catch (SQLException e){
            
            e.printStackTrace();
            response.sendRedirect("/PCP/CadastrarUsuario");
        }
    }
}