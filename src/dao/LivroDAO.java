/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import services.ServicosFactory;
import conexão.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Livro;
import services.EditoraServicos;

/**
 *
 * @author 311101245
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro LivroVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into livros values (null,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, LivroVO.getTítulo());
            pst.setString(2, LivroVO.getAutor());
            pst.setString(3, LivroVO.getAssunto());
            pst.setString(4, LivroVO.getIsbn());
            pst.setInt(5, LivroVO.getEstoque());
            pst.setFloat(6, LivroVO.getPreco());
            pst.setInt(7, LivroVO.getIdEditora().getIdEditora());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro.\n" + e.getMessage());
        }
    }// fim do cadastrar

    public ArrayList<Livro> getLivrosDAO() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livros.*, e.cnpj from livros "
                    + "join editoras e using(ideditora)";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
            while (rs.next()) {
                Livro li = new Livro();
                li.setIdLivro(rs.getInt("idlivro"));
                li.setTítulo(rs.getString("titulo"));
                li.setAssunto(rs.getString("assunto"));
                li.setAutor(rs.getString("autor"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabycnpj(rs.getString("cnpj")));
                livros.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros.\n" + e.getMessage());
        }
        return livros;
    }// fim do getLivrosDAO

    public Livro getLivroByISBN(String isbn) {
        Livro li = new Livro();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livros.*, e.cnpj from livros "
                    + "join editoras e using(ideditora) where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                li.setIdLivro(rs.getInt("idlivro"));
                li.setTítulo(rs.getString("titulo"));
                li.setAssunto(rs.getString("assunto"));
                li.setAutor(rs.getString("autor"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabycnpj(rs.getString("cnpj")));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro\n" + e.getMessage());
        }
        return li;
    } // fim do busca livro

    public void atualizarLivroDAO(Livro LivroVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set estoque = ?, preco = ? where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, LivroVO.getEstoque());
            pst.setFloat(2, LivroVO.getPreco());
            pst.setString(3, LivroVO.getIsbn());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro.\n" + e.getMessage());
        }
    } // fim do atualizar

    public void deletarLivroDAO(String isbn) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from livros where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, isbn);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro.\n" + e.getMessage());
        }
    }

}// fim da classe

