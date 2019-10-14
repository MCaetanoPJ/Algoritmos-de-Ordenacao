
package ConexaoSql;

import ConexaoBanco.ConnectionFactory;
import GetSetters.ConexaoOrdenacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SqlOrdenacao {
    
    private final Connection con;

    public SqlOrdenacao() {        
        con = ConnectionFactory.getConnection(); 
    }
    
    public boolean Salvar(ConexaoOrdenacao categoria){
        Boolean retorno = false;
        String sql = "INSERT INTO TabelaOrdenacao (IdOrdenado,Imagem) values (?,?)";
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try
        {
            pst.setInt(1, categoria.getIdOrdenado());
            pst.setBytes(2, categoria.getImagem());
            pst.executeUpdate();
            retorno = true;
            
        }
        catch(SQLException ex)
        {
        }
        finally{
            ConnectionFactory.closeConnection(con);
        }
        return retorno;
        }
    
    public List<ConexaoOrdenacao> PesquisarTudo(){
        String sql = "select Id,IdOrdenado FROM TabelaOrdenacao";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ConexaoOrdenacao> GetSet = new ArrayList<>();//cria a lista de Ordenacao
        try {                                   
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                ConexaoOrdenacao c = new ConexaoOrdenacao();
                c.setId(rs.getInt("Id"));
                c.setIdOrdenado(rs.getInt("IdOrdenado"));
                GetSet.add(c);      
            }
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return GetSet;        
    }
    
    public List<ConexaoOrdenacao> Ordenacao(String IdOrdenado) {
        String sql = "select Imagem FROM TabelaOrdenacao where IdOrdenado = "+IdOrdenado;
        PreparedStatement stmt = null;
        List<ConexaoOrdenacao> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                ConexaoOrdenacao Listagem = new ConexaoOrdenacao();
                Listagem.setImagem(res.getBytes("Imagem"));
                lista.add(Listagem);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro de Consulta "+ex);
        }
        return lista;
    }
    
    public void FecharConexao() {
	try 
        {
            //Encerra a conexão com o banco de dados
            con.close();
	} 
        catch (SQLException ex) 
        {
	}
    }
}
