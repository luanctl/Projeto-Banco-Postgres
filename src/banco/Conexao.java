package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonas
 */
public class Conexao {
    
    private final static String DRIVER = "org.postgresql.Driver";
    private final static String URL = "jdbc:postgresql://localhost:5432/educa4";
    private final static String USER = "postgres";
    private final static String PASS = "postgres";
    
    public static Connection con;
    public static Statement stmt;
    
    public static void abreConexao() {
        try {
            
            Connection con = obterConexao();
            stmt = con.createStatement();
            System.out.print("Conexão com Banco de Dados Criada!");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public static Connection obterConexao(){
     
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void fecharConexao(Connection con){
        try{
            
            if(con != null){
                con.close();
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt){
        
        //stmt é responsável por executar os comandos SQL - DML (Data Manipulation Langage)
        fecharConexao(con);
        
        try{
            
            if(stmt != null){
                stmt.close();
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs){
        
        fecharConexao(con, stmt);
        
        try{
            
            if(rs != null){
                rs.close();
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}