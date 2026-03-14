/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Role;
import Util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author edsonmigueljunior
 */
@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/CadastrarUsuario"})
public class CadastrarUsuarioServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/PCP/CadastrarUsuario.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String perfil = request.getParameter("role");
            
            if(username == null || username.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    senha == null || senha.trim().isEmpty() ||
                    perfil == null || perfil.trim().isEmpty()) {
                
                response.sendRedirect("/PCP/CadastrarUsuario");
                return;
            }
            
            String senhaHash = PasswordUtil.SenhaHash(senha);
            String perfilFormatado = perfil.substring(0,1).toUpperCase() + perfil.substring(1).toLowerCase();
            Role role = Role.valueOf(perfilFormatado);
            
            Usuario usuario = new Usuario.Builder()
                    .setUsername(username)
                    .setEmail(email)
                    .setSenhaHash(senhaHash)
                    .setRole(role)
                    .Build();
            
            UsuarioDAO DAO = new UsuarioDAO();
            DAO.CadastrarUsuario(usuario);
            
            response.sendRedirect("/PCP/CadastrarUsuario");
            
        }catch(Exception e){
            
            e.printStackTrace();
            response.sendRedirect("/PCP/CadastrarUsuario");
        }
    }
}