/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author 311101245
 */
public class DAOFactory {

    private static ClienteDAO cDAO = new ClienteDAO();

    public static ClienteDAO getClienteDAO() {
        return cDAO;
    }

    private static EditoraDAO eDao = new EditoraDAO();

    public static EditoraDAO getEditoraDAO() {
        return eDao;
    }
    private static LivroDAO livroDAO = new LivroDAO();

    public static LivroDAO getLivroDAO() {
        return livroDAO;
    }
    
    private static VendaLivroDAO vlDAO = new VendaLivroDAO();
    
    public static VendaLivroDAO getVendaLivroDAO(){
        return vlDAO;
    }
}
