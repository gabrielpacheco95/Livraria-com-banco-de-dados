/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.LivroDAO;
import java.util.ArrayList;
import model.Livro;

/**
 *
 * @author 311101245
 */
public class LivroServicos {
    public void cadastrarLivro(Livro LivroVO){
        LivroDAO livroDAO = DAOFactory.getLivroDAO();
        livroDAO.cadastrarLivroDAO(LivroVO);
    }
    
    public ArrayList <Livro> buscarLivros(){
           LivroDAO livroDAO = DAOFactory.getLivroDAO();
           return livroDAO.getLivrosDAO();
    }
    
    public Livro buscaLivroISBN (String isbn){
        LivroDAO livroDAO = DAOFactory.getLivroDAO();
        return  livroDAO.getLivroByISBN(isbn);
    }
    public void deletarLivro (String isbn){
         LivroDAO livroDAO = DAOFactory.getLivroDAO();
         livroDAO.deletarLivroDAO(isbn);
    }
    public void atualizarLivro(Livro livroVO){
         LivroDAO livroDAO = DAOFactory.getLivroDAO();
         livroDAO.atualizarLivroDAO(livroVO);
    }
}
