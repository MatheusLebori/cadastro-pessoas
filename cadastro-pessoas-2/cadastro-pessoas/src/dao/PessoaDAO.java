package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaDAO {
    
    public void inserir(Pessoa p) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?,?)";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.executeUpdate();
        }
    }
    
    public List<Pessoa> listar() throws SQLException {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pessoa p = new Pessoa(
                    rs.getInt("id"), 
                    rs.getString("nome"),
                    rs.getString("email")
                );
                lista.add(p);
            }
        }
        return lista;
    }
    
    public void atualizar(Pessoa p) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
        }
    }
       
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
