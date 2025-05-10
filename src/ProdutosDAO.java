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
import java.util.List;


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
         List<ProdutosDTO> funcc = new ArrayList<>();
        
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.connectDB();
            
            String sql = "SELECT id, nome, valor,status FROM produtos";
            
            PreparedStatement ps = conexao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ProdutosDTO fc = new ProdutosDTO();
                fc.setId(rs.getInt("id"));
                fc.setNome(rs.getString("nome"));
              
                fc.setValor(rs.getInt("valor"));
                fc.setStatus(rs.getString("status"));
                        
                funcc.add(fc);
            }
            
            conexao.Desconectar();
            
        } catch (SQLException sqle) { 
            System.out.println("Erro no Listar: " + sqle.getMessage());   
        }
        
    return new ArrayList<>(funcc);    
    
    }
    
    
    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try {
            Connection conn = new conectaDAO().connectDB(); 
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "Vendido");
            pstm.setInt(2, id);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto com ID " + id + " não encontrado.");
            }

            pstm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        }
    }
    
    
    
        
}

