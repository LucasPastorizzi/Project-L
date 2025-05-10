/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st ;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
      public ProdutosDAO() {
        conectaDAO conexao = new conectaDAO();
        this.conn = conexao.connectDB();

        if (this.conn == null) {
            System.out.println("Erro: conexão falhou no DAO!");
        }
    }
    
    public  int cadastrarProduto (ProdutosDTO produto){
        
   
       int status;   
        
        try {
           
        st = conn.prepareStatement("INSERT INTO produtos (nome ,valor,status) VALUES (?,?,'A venda') ");
        st.setString(1,produto.getNome());
        st.setString(2,produto.getValor().toString());
        status = st.executeUpdate();
        
        return status;
        
        
        
        
        }catch (SQLException sqle ) {
            System.out.println("ERRO AO CADASTRAR" + sqle.getMessage() );   
           return sqle.getErrorCode();
        } 
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

