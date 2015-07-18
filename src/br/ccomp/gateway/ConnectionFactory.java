package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {  
        try {  
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());  
   
            String url = "jdbc:mysql://localhost:3306/bandejao";  
            String username = "root";   
            String password = "";  
              
            return DriverManager.getConnection(url, username, password);    
        } catch (SQLException e) {  
            System.out.println("Nao foi possivel conectar ao banco de dados.");  
            throw new RuntimeException(e);  
        }  
	}
}
