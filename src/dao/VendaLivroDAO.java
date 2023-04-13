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
import model.VendaLivro;

/**
 *
 * @author 311101245
 */
public class VendaLivroDAO {

    public void cadastrarVendaLivroDAO(VendaLivro vlVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into pedidos values (null, ?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            // converter LocalDate no formato sql
            java.sql.Date dataAtual = java.sql.Date.valueOf(vlVO.getDataVenda());
            pst.setDate(1, dataAtual);
            pst.setInt(2, vlVO.getIdCliente().getIdCliente());
            pst.setFloat(3, vlVO.getSubTotal());
            pst.executeUpdate();

            //Pegar do Banco o ultimo id inserido na tabela pedidos
            String sqlIdPedido = "select max (idPedido) as idpedido pedidos";
            PreparedStatement pst2 = con.prepareStatement(sqlIdPedido);
            ResultSet rsIdPed = pst2.executeQuery();
            int idPedido = 0;
            while (rsIdPed.next()) {
                idPedido = rsIdPed.getInt("idPedido");
            }
            
            
        } catch (SQLException e) {
            System.out.println("Erro ao realizar venta\n" + e.getMessage());
        }

    }

}
