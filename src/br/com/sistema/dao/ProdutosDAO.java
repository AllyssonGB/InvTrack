/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Produtos;
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
public class ProdutosDAO {
    private Connection conn;
    
    public ProdutosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    public void Salvar(Produtos obj) {
        try {
            //1° Criar o SQL
            String sql = "insert into tb_produtos (descricao, preco, qtd_estoque, for_id)"
                    + "values(?,?,?,?)";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Produto " + erro);
        }
    }
    public void Editar(Produtos obj) {
        try {
            //1° Criar o SQL
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            stmt.setInt(5, obj.getId());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto Editado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o Produto "+ erro);
        }
    }
    public void Excluir(Produtos obj){
        try {
            String sql = "delete from tb_produtos where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto Excluido com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Produto: " + erro);
        }
    }
    
    public Produtos BuscarProdutos(String nome) {
      try {
          String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p  inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao = ?";
          PreparedStatement stmt = conn.prepareCall(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          Produtos obj = new Produtos();
          Fornecedores f = new Fornecedores();
          if(rs.next()){
              obj.setId(rs.getInt("p.id"));
              obj.setDescricao(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString("f.nome"));
              obj.setFornecedores(f);
          }
          return obj;
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Erro ao buscar Produto"+ erro);
      }
      return null;
  }
    public Produtos BuscarProdutosCodigo(int id) {
      try {
          String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p  inner join tb_fornecedores as f on(p.for_id=f.id) where p.id = ?";
          PreparedStatement stmt = conn.prepareCall(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
          Produtos obj = new Produtos();
          Fornecedores f = new Fornecedores();
          if(rs.next()){
              obj.setId(rs.getInt("p.id"));
              obj.setDescricao(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString("f.nome"));
              obj.setFornecedores(f);
          }
          return obj;
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null, "Erro ao buscar Produto"+ erro);
      }
      return null;
  }
    
    public List<Produtos>Listar(){
        List<Produtos> lista = new ArrayList<>();
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                    obj.setId(rs.getInt("id"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setPreco(rs.getDouble("preco"));
                    obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                    f.setNome(rs.getString("f.nome"));
                    obj.setFornecedores(f);
                    lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista "+ erro);
        }
        return null;
    }
    
    
    public List<Produtos>Filtrar(String nome){
        List<Produtos> lista = new ArrayList<>();
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    
                    f.setNome(rs.getString("f.nome"));
                    obj.setFornecedores(f);
                    lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista"+ erro);
        }
        return null;
    }    
    public void adicionarEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso ao Estoque!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Adicionar ao Estoque! "+ e);
        }
    }
    public void baixaEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso do Estoque!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Remover do Estoque! "+ e);
        }
        
    }
    
    public int retornaQtdAtualEstoque(int id){
        try {
            int qtd_atual_estoque = 0;
            String sql = "select qtd_estoque from tb_produtos where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                qtd_atual_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_atual_estoque;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao retornar a quantidade atual do estoque! " + e);
        }
        
    }
    
}
