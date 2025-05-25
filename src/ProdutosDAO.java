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
    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    try {
        st = conn.prepareStatement(sql);
        resultset = st.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listagem.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }

    return listagem;
}
    
    public boolean venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        st = conn.prepareStatement(sql);
        st.setInt(1, id);
        int rowsUpdated = st.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        return false;
    }
}

    public ArrayList<ProdutosDTO> listarVendidos() {
    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();

    try {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        st = conn.prepareStatement(sql);
        resultset = st.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            vendidos.add(produto);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }

    return vendidos;
}
    
    
        
}

