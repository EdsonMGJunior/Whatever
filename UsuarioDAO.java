/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Role;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Util.DBConexao;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public void CadastrarUsuario(Usuario Usuario) throws ClassNotFoundException, SQLException {
        
        String sql = "Insert into Usuarios (Username, Email, Hash_Senha, Perfil) values (?,?,?, ?)";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, Usuario.getUsername());
            Declaracao_SQL.setString(2, Usuario.getEmail());
            Declaracao_SQL.setString(3, Usuario.getSenhaHash());
            Declaracao_SQL.setString(4, Usuario.getRole().name());
            
            Declaracao_SQL.executeUpdate();
            
            Declaracao_SQL.close();
            con.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
    }
    
    public Usuario BuscarUsuario (String Username) throws SQLException {
        
        String sql = "Select Id, Username, Email, Hash_Senha, Perfil from Usuarios where Username = ?";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, Username);
            
            ResultSet rs = Declaracao_SQL.executeQuery();
            
            if (rs.next()) {
                
                return new Usuario.Builder()
                        .setId(rs.getInt("Id"))
                        .setUsername(rs.getString("Username"))
                        .setEmail(rs.getString("Email"))
                        .setSenhaHash(rs.getString("Hash_Senha"))
                        .setRole(Role.valueOf(rs.getString("Perfil")))
                        .Build();
            }
            
            rs.close();
            Declaracao_SQL.close();
            con.close();
            
        }catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
        
        return null;
    }
    
    public Usuario DeletarUsuario (String Username, String Email) throws SQLException {
        
        String sql = "Delete from Usuarios where Username = ? and Email = ?";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, Username);
            Declaracao_SQL.setString(2, Email);
            
            Declaracao_SQL.executeUpdate();
            
            Declaracao_SQL.close();
            con.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
        
        return null;
    }
    
    public Usuario AtualizarUsuario (Usuario usuarioAtual, Usuario usuarioAtualizado) throws SQLException {
        
        String sql = "Update Usuarios set Username = ?, Email = ?, Hash_Senha = ? where Username =  ? and Email = ?";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, usuarioAtualizado.getUsername());
            Declaracao_SQL.setString(2, usuarioAtualizado.getEmail());
            Declaracao_SQL.setString(3, usuarioAtualizado.getSenhaHash());
            Declaracao_SQL.setString(4, usuarioAtual.getUsername());
            Declaracao_SQL.setString(5, usuarioAtual.getEmail());
            
            System.out.println("Sua senha atual é");
            
            Declaracao_SQL.executeUpdate();
            
            Declaracao_SQL.close();
            con.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
        
        return null;
    }
}