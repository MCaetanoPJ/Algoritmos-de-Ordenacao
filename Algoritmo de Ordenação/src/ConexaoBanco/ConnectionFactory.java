
package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String host = "localhost";
    private static final String banco = "Ordenacao";
    private static final String url = "jdbc:mysql://" + host + ":3306/" + banco;
    private static final String user = "root";
    private static final String pass = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: "+ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro com o STMT: "+ex);
            }
        }
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro com o ResultSet: "+ex);
            }
        }
        closeConnection(con, stmt);
    }
    private static Connection con = null;
    public static PreparedStatement getPreparedStatement(String sql){
        
        // testo se a conexão já foi criada
        if (con == null){
            // cria a conexão
            con = getConnection();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+e);
        }
        return null;
    }    

}
