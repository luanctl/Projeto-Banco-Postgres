package controles;

import banco.Conexao;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelos.Bairro;
/**
 *
 * @author Jonas Dhein
 */
public class BairroControle {
    
    Bairro objBairro;
    JTable jtbBairros = null;
    
    public BairroControle(Bairro objBairro, JTable jtbBairros) {
        this.objBairro = objBairro;
        this.jtbBairros = jtbBairros;
    }
    
    public boolean incluir(){
        
        Conexao.abreConexao();
        Connection con = Conexao.obterConexao();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO bairros(nome) VALUES(?)");
            stmt.setString(1, objBairro.getNome());
            
            stmt.executeUpdate();
            
            return true;
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public boolean alterar(){
        
        Conexao.abreConexao();
        Connection con = Conexao.obterConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE bairros SET nome=? WHERE id=?");
            stmt.setString(1, objBairro.getNome());
            stmt.setInt(2, objBairro.getId());
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            Conexao.fecharConexao(con, stmt);
        }
        
    }
    
    /*
    public Bairro buscar(String id)
    {
        try {
            ConnectionFactory.abreConexao();
            ResultSet rs = null;

            String SQL = "";
            SQL = " SELECT id, nome ";
            SQL += " FROM area ";
            SQL += " WHERE id = '" + id + "'";
            SQL += " AND COALESCE(dataExclusao,'') = '' ";
            //stm.executeQuery(SQL);

            try{
                System.out.println("Vai Executar Conexão em buscar area");
                rs = ConnectionFactory.stmt.executeQuery(SQL);
                System.out.println("Executou Conexão em buscar area");

                objArea = new Area();
                
                if(rs.next() == true)
                {
                    objArea.setId(rs.getInt(1));
                    objArea.setNome(rs.getString(2));
                }
            }

            catch (SQLException ex )
            {
                System.out.println("ERRO de SQL: " + ex.getMessage().toString());
                return null;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage().toString());
            return null;
        }
        
        System.out.println ("Executou buscar area com sucesso");
        return objArea;
    }
    
    
    
    public boolean excluir(){
        
        ConnectionFactory.abreConexao();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE area SET dataExclusao=? WHERE id=?");
            stmt.setString(1, objArea.getDataExclusao());//1º?
            stmt.setInt(2, objArea.getId());//5º?
                        
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public ArrayList<Area> listar() {

        ConnectionFactory.abreConexao();
        
        ArrayList<Area> listagem_areas = new ArrayList();
        Area area_item = null;
        
        ResultSet result = null;
        
        try {

            String SQL = "";
            SQL = " SELECT id, nome AS nomeArea ";
            SQL += " FROM area ";
            SQL += " ORDER BY nome ";
            
            result = ConnectionFactory.stmt.executeQuery(SQL);

            while (result.next()) {
                area_item = new Area();
                area_item.setId(result.getInt("id"));
                area_item.setNome(result.getString("nomeArea"));
                listagem_areas.add(area_item);
            }
            
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
            return null;
        }
        
        return listagem_areas;
    }
    
    public void Preencher() {

        ConnectionFactory.abreConexao();
        
        Vector<String> cabecalhos = new Vector<String>();
        Vector dadosTabela = new Vector();
        cabecalhos.add("Código");
        cabecalhos.add("Nome");
        
        ResultSet result = null;
        
        try {

            String SQL = "";
            SQL = " SELECT id, nome ";
            SQL += " FROM area ";
            SQL += " WHERE COALESCE(dataExclusao,'') = '' ";
            SQL += " ORDER BY nome DESC ";
            
            result = ConnectionFactory.stmt.executeQuery(SQL);

            while (result.next()) {
                Vector<Object> linha = new Vector<Object>();
                linha.add(result.getInt(1));
                linha.add(result.getString(2));
                dadosTabela.add(linha);
            }
            
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        jtbAreas.setModel(new DefaultTableModel(dadosTabela, cabecalhos) {

            @Override
            public boolean isCellEditable(int row, int column) {
              return false;
            }
            // permite seleção de apenas uma linha da tabela
        });


        // permite seleção de apenas uma linha da tabela
        jtbAreas.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = jtbAreas.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(80);
                    break;
                case 1:
                    column.setPreferredWidth(200);
                    break;
            }
        }
        jtbAreas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.LIGHT_GRAY);
                } else {
                    setBackground(Color.WHITE);
                }
                return this;
            }
        });
        //return (true);
    }
    */
}
