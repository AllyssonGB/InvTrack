/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Funcionarios;
import br.com.sistema.view.AreaTrabalho;
import br.com.sistema.view.FormularioLogin;
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
public class FuncionariosDAO {
    private Connection conn;
    
    public FuncionariosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    public void Salvar(Funcionarios obj) {
        try {
            //1° Criar o SQL
            String sql = "insert into tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o cliente "+ erro);
        }
    }
    public void Editar(Funcionarios obj) {
        try {
            //1° Criar o SQL
            String sql = "update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
            // 2° Preparar conexão SQL para se conectar com o banco de dados
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            stmt.setInt(17, obj.getId());
            //3° Executar sql
            stmt.execute();
            //4° fechar conexão 
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário Editado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o Funcionário "+ erro);
        }
    }
    public void Excluir(Funcionarios obj){
        try {
            String sql = "delete from tb_funcionarios where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário Excluido com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Funcionário: " + erro);
        }
    }
    
    public Funcionarios BuscarFuncionario(String nome) {
      try {
          String sql = "select * from tb_funcionarios where nome = ?";
          PreparedStatement stmt = conn.prepareCall(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          Funcionarios obj = new Funcionarios();
          if(rs.next()){
              obj.setId(rs.getInt("id"));
              obj.setNome(rs.getString("nome"));
              obj.setRg(rs.getString("rg"));
              obj.setCpf(rs.getString("cpf"));
              obj.setEmail(rs.getString("email"));
              obj.setSenha(rs.getString("senha"));
              obj.setCargo(rs.getString("cargo"));
              obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
          JOptionPane.showMessageDialog(null, "Erro ao buscar Funcionário"+ erro);
      }
      return null;
  }
    
    public List<Funcionarios>Listar(){
        List<Funcionarios> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                    Funcionarios obj = new Funcionarios();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setRg(rs.getString("rg"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                    obj.setCargo(rs.getString("cargo"));
                    obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    public List<Funcionarios>Filtrar(String nome){
        List<Funcionarios> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setRg(rs.getString("rg"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                    obj.setCargo(rs.getString("cargo"));
                    obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    public void efetuarLogin(String email, String senha){
               try {
        String sql = "select * from tb_funcionarios where email=? and senha=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            // Cria a área de trabalho mas não mostra ainda
            AreaTrabalho at = new AreaTrabalho();
            at.usuarioLogado = rs.getString("nome");
            
            if(rs.getString("nivel_acesso").equals("ADMINISTRADOR")) {
                // Removida a mensagem de boas-vindas
            }
            else if(rs.getString("nivel_acesso").equals("USUÁRIO")) {
                at.forne.setEnabled(false);
                at.menu_funcionario.setEnabled(false);
                at.menu_estoque.setEnabled(false);
                // Removida a mensagem de boas-vindas
            }
            
            // Mostra o SplashScreen primeiro
            br.com.sistema.view.SplashScreen splash = new br.com.sistema.view.SplashScreen(null, true);
            splash.setAlwaysOnTop(true);
            splash.toFront();
            splash.setLocationRelativeTo(null);
            splash.setVisible(true); // Espera o splash terminar
            
            // Depois mostra a área de trabalho
            at.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Dados Invalidos");
            FormularioLogin login = new FormularioLogin();
            login.setVisible(true);
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro: " + erro);
    }
}
}
