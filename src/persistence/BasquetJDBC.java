package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Equipo;
import model.Jugador;

public class BasquetJDBC {
    private Connection connection;
    
    public void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/basquet";
        String usr = "root";
        String pass = "";
        connection = DriverManager.getConnection(url, usr, pass);
    }
    
    public void desconectar() throws SQLException{
        if(connection != null){
            connection.close();
        }
    }
    
    
}
