/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexão.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author 311101245
 */
public class ClienteDAO {

    public void cadastrarCLienteDAO(Cliente cVO) {
        //busca conexão com o BD  
        try {
             Connection con = Conexao.getConexao();
            //cria espaço de trabalho SQL, é a área no java onde vamos executar
            //os scripts  SQL
            Statement stat = con.createStatement();
            String sql;
            sql = "insert into clientes values (null, ?,?,null,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getCpf());
            pst.setString(3, cVO.getEndereco());
            pst.setString(4, cVO.getTelefone());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar! \n" + ex.getMessage());
        }
    }// fim do cadastrar

    public ArrayList<Cliente> getClienteByDoc() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
             Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "select* from clientes";
            ResultSet rs = stat.executeQuery(sql);
           
            while (rs.next()) {
                Cliente c = new Cliente();
                //lado do java |X| Lado do Banco
                c.setIdCliente(rs.getInt("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone("telefone");
                clientes.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao listar! \n" + ex.getMessage());
        }
        return clientes;
    }// fim do listar

    public Cliente getClientesDAO(String cpf) {
        Cliente c = null;
        try {
            Connection con = Conexao.getConexao();
            String sql = "select*from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                //lado do java |X| Lado do Banco
                c.setIdCliente(rs.getInt("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone("telefone");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conultar o CPF!\n" + ex.getMessage());
        }
        return c;
    }

    public void deletarClienteDAO(String cpf) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "deletr from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao Deletar o CPF!\n" + ex.getMessage());
        }
    }// fim do deletarCliente

    public void atualizarClienteByDoc(Cliente cVO) {       
        try {
            Connection con = Conexao.getConexao();
            String sql = "update cliente set nome = ?, endereco = ?, telefone =? "
                    + "wherecpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getCpf());
        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar CPF!\n" + ex.getMessage());
        }

    }
}
