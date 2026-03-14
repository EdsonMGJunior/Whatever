/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author edsonmigueljunior
 */
public class DBConexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/fake_news";
    private static final String Usuario = "root";
    private static final String Senha = "Hear_the$h4dows";
    
    public static Connection FabricaConexao() throws SQLException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException e) {
            
            throw new SQLException("Driver do MySQL não encontrado.", e);
            
        }
        
        return DriverManager.getConnection(URL, Usuario, Senha);
    }
}