/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.DBConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class CodigoRecuperacaoDAO {
    
    public void SalvarCodigo (String Email, String Codigo) throws SQLException {
        
        String sql = "Insert into Codigo_Seguranca (Email, Codigo, Expiracao, Usado) values (?, ?, ?, false)";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, Email);
            Declaracao_SQL.setString(2, Codigo);
            Declaracao_SQL.setObject(3, LocalDateTime.now().plusMinutes(15));
            
            Declaracao_SQL.executeUpdate();
            
            Declaracao_SQL.close();
            con.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
    }
    
    public boolean ValidarCodigo (String Email, String Codigo) throws SQLException {
        
        String SQL = "Select Id from Codigo_Seguranca where Email = ? and Codigo = ? and Expiracao > now() and Usado = false";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(SQL);
            
            Declaracao_SQL.setString(1, Email);
            Declaracao_SQL.setString(2, Codigo);
            
            ResultSet rs = Declaracao_SQL.executeQuery();
            
            if (rs.next()) {
                
                MarcarCodigo(Email, Codigo);
                
                rs.close();
                Declaracao_SQL.close();
                con.close();
                
                return true;
                
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
        return false;   
    }
    
    private void MarcarCodigo (String email, String codigo) throws SQLException {
        
        String sql = "Update Codigo_Seguranca set Usado = true; where Email = ?, and Codigo = ?";
        
        try {
            
            Connection con = DBConexao.FabricaConexao();
            PreparedStatement Declaracao_SQL = con.prepareStatement(sql);
            
            Declaracao_SQL.setString(1, email);
            Declaracao_SQL.setString(2, codigo);
            
            Declaracao_SQL.executeUpdate();
            
            Declaracao_SQL.close();
            con.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        }
    }
}