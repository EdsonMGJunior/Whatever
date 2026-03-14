/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author edsonmigueljunior
 */
public class Usuario {
    
    private int Id;
    private String Username;
    private String Email;
    private String SenhaHash;
    private Role role;
    
    private Usuario(Builder build) {
        this.Id = build.Id;
        this.Username = build.Username;
        this.Email = build.Email;
        this.SenhaHash = build.SenhaHash;
        this.role = build.role;
    }
    
    public int getId() {
        
        return Id;
    }
    
    public String getUsername() {
        
        return Username;
    }
    
    public String getEmail() {
        
        return Email;
    }
    
    public String getSenhaHash() {
        
        return SenhaHash;
    }
    
    public Role getRole() {
        
        return role;
    }
    
    public static class Builder {
        
        private int Id;
        private String Username;
        private String Email;
        private String SenhaHash;
        private Role role;
        
        public Builder setId(int Id) {
            
            this.Id = Id;
            return this;
            
        }
        
        public Builder setUsername(String Username) {
            
            this.Username = Username;
            return this;
            
        }
        
        public Builder setEmail(String Email) {
            
            this.Email = Email;
            return this;
            
        }
        
        public Builder setSenhaHash(String SenhaHash) {
            
            this.SenhaHash = SenhaHash;
            return this;
            
        }
        
        public Builder setRole (Role role) {
            
            this.role = role;
            return this;
        }
        
        public Usuario Build() {
            
            return new Usuario(this);
        }
    }
}