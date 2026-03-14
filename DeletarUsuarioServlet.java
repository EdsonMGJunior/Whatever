/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controle;

import DAO.UsuarioDAO;
import Modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

@WebServlet(name = "DeletarUsuarioServlet", urlPatterns = {"/DeletarUsuarioServlet"})
public class DeletarUsuarioServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            HttpSession sessao = request.getSession(false);
            Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
            
            UsuarioDAO dao = new UsuarioDAO();
            dao.DeletarUsuario(usuarioLogado.getUsername(), usuarioLogado.getEmail());
            
            sessao.invalidate();
            
            response.sendRedirect("/PCP/LoginUsuario.jsp");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            response.sendRedirect("/PCP/Usuario.jsp");
        }
    }
}