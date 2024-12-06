/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.jdbc;



import java.sql.Statement; // Certifique-se de importar somente o Statement correto
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author victo
 */
public class ConexaoBancoRelatorios {
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/invtrack";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
    public boolean conecta(){
        boolean resultado = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url ,usuario , senha);
            
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado! " + Driver);
            resultado = false;
        }catch(SQLException fonte){
            JOptionPane.showMessageDialog(null, "Erro na minha fonte de dados! " + fonte.getMessage());
            resultado = false;
        }
        return resultado;
    }
    public void desconecta() {
        boolean resultado = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Banco Fechado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Desconectar do Banco de Dados!");
            resultado = false;
        }
        
    }
    public void executeSQL(String sql){
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }
    }
}

