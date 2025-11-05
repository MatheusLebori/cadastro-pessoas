/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://sql10.freesqldatabase.com/sql10806255";
    private static final String USER = "sql10806255";
    private static final String PASSWORD = "xaYrGHi4Rt";
    
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
