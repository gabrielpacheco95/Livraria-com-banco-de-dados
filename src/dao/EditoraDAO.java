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
import model.Editora;

/**
 *
 * @author 311101245
 */
public class EditoraDAO {
    public void cadastrarEditoraDAO(Editora cVO) {
        try {
            //busca conexão com o BD
            Connection con = Conexao.getConexao();
            //cria espaço de trabalho SQl, é a área no Java onde
            //vamo executar os scripts SQL
            String sql;
            sql = "insert into editoras values (null, ?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNmEditora());
            pst.setString(2, cVO.getCnpj());
            pst.setString(3, cVO.getEndereco());
            pst.setString(4, cVO.getTelefone());
            pst.setString(5, cVO.getGerente());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar!\n"
                    + ex.getMessage());
        }
    }//fim cadastrar

    public ArrayList<Editora> getEditorasDAO() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "select * from editoras";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Editora c = new Editora();
                //lado do java |x| (lado do banco)
                c.setIdEditora(rs.getInt("ideditora"));
                c.setNmEditora("nomeEditora");
                c.setCnpj(rs.getString("c"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                editoras.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao Listar!\n"
                    + ex.getMessage());
        }
        return editoras;
    }//fim do listar

    public Editora getEditoraByDoc(String cnpj) {
        Editora c = new Editora();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from editoras where cnpj = ?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //lado do java |x| (lado do banco)
                c.setIdEditora(rs.getInt("ideditora"));
                c.setNmEditora(rs.getString("nomeEditora"));
                c.setCnpj(rs.getString("cnpj"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setGerente(rs.getString("gerente"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar CNPJ!\n"
                    + ex.getMessage());
        }
        return c;
    }

    public void deletarEditoraDAO(String cnpj) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar CNPJ!\n"
                    + ex.getMessage());
        }
    }//fim deletarEditora

    public void atualizaEditoraByDoc(Editora cVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update editoras set nome = ?, endereco = ?, telefone = ?, gerente =? "
                    + "where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNmEditora());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getCnpj());
            pst.setString(5, cVO.getGerente());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar CNPJ!\n"
                    + ex.getMessage());
        }
    }

    
    
}
