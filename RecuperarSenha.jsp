<%-- 
    Document   : RecuperarSenha
    Created on : 17 de mar. de 2026, 21:35:39
    Author     : edsonmigueljunior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar Senha</title>
    </head>
    <body>
        <h2>Recuperar Senha</h2>
        
        <form action="/PCP/RecuperarSenhaServlet" method="post">
            Email: <br>
            <input type="email" name="email" required><br><br>
            
            <input type="submit" value="Enviar Código">
            
        </form>
        
        <br>
        
        <a href="/PCP/LoginUsuario.jsp">Voltar ao Login</a>
    </body>
</html>
