/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.EditoraDAO;
import dao.DAOFactory;
import java.util.ArrayList;
import model.Editora;

/**
 *
 * @author 311101245
 */
public class EditoraServicos {

     public void cadEditora(Editora cVO) {
        EditoraDAO cDAO = DAOFactory.getEditoraDAO();
        cDAO.cadastrarEditoraDAO(cVO);
    }
    
    public void atualizarEditora(Editora cVO){
        EditoraDAO cDAO = DAOFactory.getEditoraDAO();
        cDAO.atualizaEditoraByDoc(cVO);
    }
    
    public void deletarEditora(String cnpj){
        EditoraDAO cDAO = DAOFactory.getEditoraDAO();
        cDAO.deletarEditoraDAO(cnpj);
    }
    
    public Editora buscarEditorabycnpj(String cnpj){
        EditoraDAO cDAO = DAOFactory.getEditoraDAO();
        return cDAO.getEditoraByDoc(cnpj);
    }
    
    public ArrayList<Editora> getEditoras() {
        EditoraDAO cDAO = DAOFactory.getEditoraDAO();
        return cDAO.getEditorasDAO();
    }
}
