import dao.Conexao;
import view.TelaCadastro;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class App {
    
    public static void main(String[] args){
        
        if (!testarConexao()) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.\nVerifique se o serviço MySQL está em execução.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        if (!verificarECriarTabela()) {
            System.exit(1);
        }
        
        new TelaCadastro().setVisible(true);
    }
    
    private static boolean testarConexao() {
        try (Connection conn = Conexao.conectar()) {
            return conn != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean verificarECriarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoa ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY,"
                   + "nome VARCHAR(100) NOT NULL,"
                   + "email VARCHAR(100)"
                   + ")";
                   
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar ou verificar a tabela 'pessoa': " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
}