package com.mycompany.projectlogin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("user");
        String userPassword = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/servletlogin";
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Establecer conexión a la base de datos
            conexion = DriverManager.getConnection(url, "root", "");
            statement = conexion.createStatement();
            // Ejecutar consulta
            rs = statement.executeQuery("SELECT * FROM `users` WHERE `user` LIKE '" +userName+"' AND `passwod` LIKE '"+userPassword+"'");
            // Procesar resultados
            if(rs.next()) {
                request.getSession().setAttribute("user", userName );
                response.sendRedirect("panel.jsp");
            }else{
                response.sendRedirect("index.html");                
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
