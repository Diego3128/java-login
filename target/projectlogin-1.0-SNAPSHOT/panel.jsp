<%-- 
    Document   : panel
    Created on : Aug 17, 2024, 7:42:50 PM
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1{
                text-align: center;
                font-size: 3rem;
            }
        </style>
    </head>
    <body>
        <%
            if(session.getAttribute("user") == null){
                response.sendRedirect("index.html");
                return;
            }
        %>
        <h1>Welcome to the control panel!</h1>
    </body>
</html>
