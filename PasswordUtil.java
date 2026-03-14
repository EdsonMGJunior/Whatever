/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import at.favre.lib.crypto.bcrypt.BCrypt;

//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author edsonmigueljunior
 */
public class PasswordUtil {
    
    private static final int Log_Grounds = 12;
    
    public static String SenhaHash(String Senha) {
        
        if (Senha == null || Senha.isEmpty()) {
            
            throw new IllegalArgumentException("Senha não pode permanecer vazia.");
        }
        
        return BCrypt.withDefaults().hashToString(Log_Grounds, Senha.toCharArray());
    }
    
    public static boolean checkSenha (String SenhaDigitada, String hash) {
        
        if(SenhaDigitada == null || hash == null){
            
            return false;
        }
        
        BCrypt.Result result = BCrypt.verifyer()
                .verify(SenhaDigitada.toCharArray(), hash);
        
        return result.verified;
    }
}