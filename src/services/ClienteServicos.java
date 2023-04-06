/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ClienteDAO;
import dao.DAOFactory;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author 311101245
 */
public class ClienteServicos {

    public void cadCliente(Cliente CVO) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.cadastrarCLienteDAO(CVO);
    }

    public void atualizarCliente(Cliente CVO) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.atualizarClienteByDoc(CVO);
    }

    public void deletarCliente(String cpf) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.deletarClienteDAO(cpf);
    }

    public Cliente buscarClientebyCPF(String cpf) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.getClientesDAO(cpf);
    }
   public ArrayList<Cliente> getClientes(){
       ClienteDAO cDAO = DAOFactory.getClienteDAO();
       return cDAO.getClienteByDoc();
   }
   
}
