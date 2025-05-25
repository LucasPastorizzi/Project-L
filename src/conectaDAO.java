
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

public class conectaDAO {
    Connection conn;  // variável de instância
    PreparedStatement st;

    public Connection connectDB() {
        try {
conn = DriverManager.getConnection("jdbc:mysql://localhost/att1?useSSL=false", "root", "@usuario");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }

    public boolean Desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println("ERRO AO SE DESCONECTAR: " + sqle.getMessage());
            return false;
        }
    }
}

    
   
    
    

