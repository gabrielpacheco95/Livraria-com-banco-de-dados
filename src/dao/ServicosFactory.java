/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import services.ClienteServicos;

/**
 *
 * @author 311101245
 */
public class ServicosFactory {

    private static ClienteServicos cServicos = new ClienteServicos();

    public static ClienteServicos getClienteServicos() {
        return cServicos;
    }
}
