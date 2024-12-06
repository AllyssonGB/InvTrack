/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Funcionarios;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author jhona
 */
public class FornecedoresDAO {
    private Connection conn;
    
    public FornecedoresDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    public void Salvar(Fornecedores obj) {
        try {
            //1° Criar o SQL
            String sql = "insert into tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Fornecedor "+ erro);
        }
    }
    public void Editar(Fornecedores obj) {
        try {
            //1° Criar o SQL
            String sql = "update tb_fornecedores set nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?,"
                    + " complemento=?, bairro=?, cidade=?, estado=? where id=?";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o fornecedor "+ erro);
        }
    }
    public void Excluir(Fornecedores obj){
        try {
            String sql = "delete from tb_fornecedores where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor Excluido com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Fornecedor: " + erro);
        }
    }
    
    public Fornecedores BuscarFornecedor(String nome) {
      try {
          String sql = "select * from tb_fornecedores where nome = ?";
          PreparedStatement stmt = conn.prepareCall(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          Fornecedores obj = new Fornecedores();
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setCnpj(rs.getString("cnpj"));
              obj.setEmail(rs.getString("email"));
              obj.setTelefone(rs.getString("telefone"));
              obj.setCelular(rs.getString("celular"));
              obj.setCep(rs.getString("cep"));
              obj.setEndereco(rs.getString("endereco"));
              obj.setNumero(rs.getInt("numero"));
              obj.setCelular(rs.getString("celular"));
              obj.setCelular(rs.getString("celular"));
              obj.setComplemento(rs.getString("complemento"));
              obj.setBairro(rs.getString("bairro"));
              obj.setCidade(rs.getString("cidade"));
              obj.setEstado(rs.getString("estado"));
          }
          return obj;
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Erro ao buscar Fornecedor"+ erro);
      }
      return null;
  }
    
    public List<Fornecedores>Listar(){
        List<Fornecedores> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                    Fornecedores obj = new Fornecedores();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setCnpj(rs.getString("cnpj"));
                    obj.setEmail(rs.getString("email"));
                    obj.setTelefone(rs.getString("telefone"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setCep(rs.getString("cep"));
                    obj.setEndereco(rs.getString("endereco"));
                    obj.setNumero(rs.getInt("numero"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setComplemento(rs.getString("complemento"));
                    obj.setBairro(rs.getString("bairro"));
                    obj.setCidade(rs.getString("cidade"));
                    obj.setEstado(rs.getString("estado"));
                    lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista"+ erro);
        }
        return null;
    }
    
    
    public List<Fornecedores>Filtrar(String nome){
        List<Fornecedores> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                    Fornecedores obj = new Fornecedores();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setCnpj(rs.getString("cnpj"));
                    obj.setEmail(rs.getString("email"));
                    obj.setTelefone(rs.getString("telefone"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setCep(rs.getString("cep"));
                    obj.setEndereco(rs.getString("endereco"));
                    obj.setNumero(rs.getInt("numero"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setCelular(rs.getString("celular"));
                    obj.setComplemento(rs.getString("complemento"));
                    obj.setBairro(rs.getString("bairro"));
                    obj.setCidade(rs.getString("cidade"));
                    obj.setEstado(rs.getString("estado"));
                    lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista"+ erro);
        }
        return null;
    }    
    
}
