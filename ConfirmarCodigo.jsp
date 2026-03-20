<%-- 
    Document   : ConfirmarCódigo
    Created on : 17 de mar. de 2026, 21:43:45
    Author     : edsonmigueljunior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Código</title>
    </head>
    <body>
        <h2>Confirmar Código</h2>
        
        <form action="/PCP/ConfirmarCodigoServlet" method="post">
            
            Email: <br>
            <input type="email" name="email" required><br><br>
            
            Código:<br>
            <input type="text" name="codigo" required maxlength="6"><br><br>
            
            Nova Senha<br>
            <input type="password" name="novaSenha" required maxlength="8"><br><br>
            
            <input type="submit" value="Confirmar">
            
        </form>
        
        <br>
        
        <a href="/PCP/LoginUsuario.jsp">Voltar ao Login</a>
    </body>
</html>
