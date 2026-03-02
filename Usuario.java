/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author alunocmc
 */
public class Usuario {
    
    private int Id;
    private String Username;
    private String Senha;
    
    private Usuario(Builder build){
        this.Id = build.Id;
        this.Username = build.Username;
        this.Senha = build.Senha;
    }
    
    public int getId(){
        return Id;
    }
    
    public String getUsername(){
        return Username;
    }
    
    public String getSenha(){
        return Senha;
    }
    
    public static class Builder {
        
        private int Id;
        private String Username;
        private String Senha;
        
        public Builder setId() {
            
            this.Id = Id;
            return this;
        }
        
        public Builder setUsername() {
            
            this.Username = Username;
            return this;
        }
        
        public Builder setSenha() {
            
            this.Senha = Senha;
            return this;
        }
        
        public Usuario Build () {
            
            return new Usuario(this);
        }
    }
}