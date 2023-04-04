/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexão;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author 311101245
 */
public class Conexao {
    //cria uma constante com o endereço do Banco de Dados/Schema

    private static String url = "jdbc:mysql://localhost:3306/livraria";
//cria uma cconstante (um user)  do BD
    private static String user = "root";
//cria uma constante com a senha do BD
    private static String pass = "";

    public static Connection getConexao() {
        //Inicia conexão nula, ainda não estabelecida
        Connection c = null;
        // tenta estabelecer conexão
        try {
            c = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            //caso ha erro na conexção do banco
            System.out.println("Erro ao conectar banco\n" +e.getMessage());
        }
        return c;
    }
}
