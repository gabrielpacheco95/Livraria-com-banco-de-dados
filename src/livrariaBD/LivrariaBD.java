/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrariaBD;

import controller.CCliente;
import controller.CEditora;
import controller.CLivro;
import controller.CVendaLivro;
import services.ServicosFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;
import model.Editora;
import model.Livro;
import model.VendaLivro;
import services.ClienteServicos;
import services.EditoraServicos;
import services.LivroServicos;
import services.VendaLivroServicos;
import util.Validadores;

/**
 *
 * @author 311101245
 */
public class LivrariaBD {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static CLivro cadLivro = new CLivro();
    public static CVendaLivro cadVendaLivro = new CVendaLivro();
    public static Scanner leia = new Scanner(System.in);

    public static int leiaNumInt() {
        Scanner leiaNum = new Scanner(System.in);
        int num = 99;
        try {
            num = leiaNum.nextInt();
        } catch (Exception e) {
            System.out.println("Tente novamente");
            leiaNum.nextLine();
        }
        return num;
    }

    public static float leiaNumFloat() {
        Scanner leiaNumFloat = new Scanner(System.in);
        float num = 99;
        try {
            num = leia.nextFloat();
        } catch (Exception e) {
            System.out.println("Tente novamente");
            leiaNumFloat.nextLine();
        }
        return num;
    }

    public static void menuP() {
        System.out.println("Livraria");
        System.out.println("1- Gerenciar Clientes");
        System.out.println("2- Gerenciar Editoras");
        System.out.println("3- Gerenciar Livros");
        System.out.println("4- Venda Livro");
        System.out.println("0- Sair");
        System.out.print("Escolha uma opcção: ");
    }

    public static void subMenu(int op) {
        String tpCad = null;
        switch (op) {
            case 1:
                tpCad = "Cliente";
                break;
            case 2:
                tpCad = "Editora";
                break;
            case 3:
                tpCad = "Livro";
                break;
        }
        System.out.println("-- Ger." + tpCad + " --");
        System.out.println("1 - Cadastrar" + tpCad); //CADASTRAR CLIENTE
        System.out.println("2 - Editar" + tpCad);
        System.out.println("3 - Listar" + tpCad + "s");

        System.out.println("4 - Deletar" + tpCad);
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarCliente() {
        int idCliente;
        String nomeCliente;
        String cpf;
        String cnpj = null;
        String endereco;
        String telefone;
        ClienteServicos cClienteS = ServicosFactory.getClienteServicos();

        System.out.println("-- Cadastro de Cliente --");
        System.out.print("Informe o CPF: ");
        boolean cpfIs;
        int opCPF;
        do {
            cpf = leia.nextLine();
            cpfIs = Validadores.isCPF(cpf);
            if (!cpfIs) {
                System.out.println("CPF Inv1álido" + "\nDeseja tenta novamente? 1-Sim 2-Não");
                opCPF = leiaNumInt();
                if (opCPF == 1) {
                    System.out.print("Informe o CPF: ");
                } else if (opCPF == 2) {
                    System.out.println("Cadastro Cancelado pelo usuário");
                    return;
                }
            }
        } while (!Validadores.isCPF(cpf));
        if (cClienteS.buscarClientebyCPF(cpf).getCpf() != null) {
            System.out.println("Cliente Já Cadastrado");
        } else {
            System.out.print("Informe o nome: ");
            nomeCliente = leia.nextLine();
            System.out.print("Informe o telefone: ");
            telefone = leia.nextLine();
            System.out.print("Informe o Endereço: ");
            endereco = leia.nextLine();
            idCliente = cadCliente.geraID();
            Cliente cli = new Cliente(idCliente, nomeCliente, cpf, cnpj, endereco, telefone);
            //cadCliente.addCliente(cli);
            cClienteS.cadCliente(cli);
            System.out.println("Cliente Cadastrado com sucesso");

        }
    }// Fim Do cadastrarCliente    

    public static void deletarCliente() {
        System.out.println("Deletar Cliente");
        System.out.print("Informe o cpf: ");
        String cpf = leia.next();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        if (Validadores.isCPF(cpf)) {
            // Cliente cli = cadCliente.getClienteCPF(cpf);
            Cliente cli = clienteS.buscarClientebyCPF(cpf);
            if (cli != null) {
                //cadCliente.removeCliente(cli);
                clienteS.deletarCliente(cpf);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Cliente não consta na base de dados!");
            }
        } else {
            System.out.println("CPF INVÀLIDO!");

        }
    }// Fim do deletar Cliente

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cadCliente.mockClientes();
        cadEditora.mockEditoras();
        cadLivro.mockLivros();
        cadVendaLivro.mockVendaLivros();

        int opM;

        do {
            menuP();
            opM = leiaNumInt();
            switch (opM) {
                case 1:
                case 2:
                case 3:
                    int opSM;
                    do {
                        subMenu(opM);
                        opSM = leiaNumInt();
                        //switch
                        switch (opSM) {
                            case 1:
                                System.out.println(" -- Cadastrar -- ");
                                if (opM == 1) {
                                    cadastrarCliente();
                                } else if (opM == 2) {
                                    cadastrarEditora();
                                } else if (opM == 3) {
                                    cadastrarLivro();
                                }
                                break;
                            case 2:
                                System.out.println("-- Editar --");
                                if (opM == 1) {
                                    editarCliente();
                                } else if (opM == 2) {
                                    editarEditora();
                                    break;
                                }
                            case 3:
                                System.out.println("-- Listar --");
                                if (opM == 1) {
                                    listarClientes();
                                } else if (opM == 2) {
                                    listarEditora();
                                } else if (opM == 3) {
                                    listarLivro();
                                }
                                break;
                            case 4:
                                System.out.println("-- Aplicação Encerrada -- ");
                                break;
                            case 0:
                                System.out.println("-- Menu Principal--");
                                break;
                            default:
                                System.out.println("Aplicação encerrada");
                                break;
                        }
                    } while (opSM != 0); //fim subMenu
                    break;
                case 4:
                    vendaLivro();
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }

        } while (opM != 0); //Sistema

    }

    private static void editarCliente() {
        System.out.println("-- Editar Cliente --");
        System.out.print("Informe o CPF: ");
        String cpf = leia.nextLine();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                System.out.println("1 - Nome:\t" + cli.getNomeCliente());
                System.out.println("2 - Endereço:\t" + cli.getEndereco());
                System.out.println("3 - Fone:\t" + cli.getTelefone());
                System.out.println("4 - Todos os campos acima?");
                System.out.print("Qual campo deseja alterar?"
                        + "\nDigite aqui: ");
                int opEditar = leiaNumInt();
                //Sugestão Sérgio INF3M212
                if (opEditar == 1 || opEditar == 4) {// "||" pipe significa "ou"
                    System.out.print("Informe o nome: ");
                    cli.setNomeCliente(leia.nextLine());
                }
                if (opEditar == 2 || opEditar == 4) {
                    System.out.print("Informe o endereço: ");
                    cli.setEndereco(leia.nextLine());
                }
                if (opEditar == 3 || opEditar == 4) {
                    System.out.print("Informe o telefone: ");
                    cli.setTelefone(leia.nextLine());
                }
                if (opEditar < 1 || opEditar > 4) {
                    System.out.println("Opção inválida!");
                }
                ClienteServicos clienteS = ServicosFactory.getClienteServicos();
                /*
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.print("Informe o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        System.out.print("Informe o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                 */
                System.out.println("Cliente:\n" + cli.toString());
            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }

    public static void cadastrarEditora() {
        int idEditora;
        String nmEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;
        EditoraServicos cEditoras = ServicosFactory.getEditoraServicos();

        System.out.println("-- Cadastrar Editora --");
        System.out.print("Informe o CNPJ da Editora: ");
        boolean cnpjis;
        int opCNPJ;
        do {
            cnpj = leia.nextLine();
            cnpjis = Validadores.isCNPJ(cnpj);
            if (!cnpjis) {
                System.out.print("CNPJ Invalido!"
                        + "\nDeseja tentar novamente? 1 - Sim || 2 - Não: ");
                opCNPJ = leiaNumInt();
                if (opCNPJ == 1) {
                    System.out.print("Informe o CNPJ: ");
                } else if (opCNPJ == 2) {
                    System.out.println("Cadastro encerrado pelo usuario!");
                    break;
                }
            }

        } while (!cnpjis);

        if (cadEditora.getEditoraCNPJ(cnpj) != null) {
            System.out.println("Editora já cadastrada!");
        } else {
            System.out.print("Informe o nome da editora: ");
            nmEditora = leia.nextLine();
            System.out.print("Informe o telefone da editora: ");
            telefone = leia.nextLine();
            System.out.print("Informe o endereço da editora: ");
            endereco = leia.nextLine();
            System.out.print("Informe o nome do gerente: ");
            gerente = leia.nextLine();
            idEditora = cadEditora.geraID();

            Editora edi = new Editora(idEditora, nmEditora, cnpj, endereco, telefone, gerente);
            cadEditora.addEditora(edi);
            System.out.println("Editora cadastrada com sucesso!");
        }
    }//fim do cadastrarEditora

    private static void editarEditora() {
        System.out.println("-- Editar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.nextLine();
        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = cadEditora.getEditoraCNPJ(cnpj);
            if (edi != null) {
                System.out.println("1 - Nome:\t" + edi.getNmEditora());
                System.out.println("2 - Endereço:\t" + edi.getEndereco());
                System.out.println("3 - Fone:\t" + edi.getTelefone());
                System.out.println("4 - Todas as opções acima");
                System.out.print("Qual das opções deseja alterar? 1 || 2 || 3 || 4\n Digite aqui: ");
                int opEditar = leiaNumInt();
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine().toUpperCase());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine().toUpperCase());
                        break;
                    case 3:
                        System.out.println("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine());
                        System.out.print("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                System.out.println("Editora:\n" + edi.toString());
            } else {
                System.out.println("Editora não cadastrado!");
            }
        } else {
            System.out.println("CNPJ inválido!");
            EditoraServicos cEditoras = ServicosFactory.getEditoraServicos();
        }
    }//fim do editarEditora

    public static void listarEditora() {

        for (Editora edi : cadEditora.getEditoras()) {
            System.out.println("----");
            System.out.println("CNPJ:\t" + edi.getCnpj());
            System.out.println("Nome:\t" + edi.getNmEditora());
            System.out.println("Fone:\t" + edi.getTelefone());
        }

    }//fim listarEditora

    private static void listarClientes() {
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        for (Cliente cli : clienteS.getClientes()) {
            System.out.println("---");
            System.out.println("CPF:\t" + cli.getCpf());
            System.out.println("Nome:\t" + cli.getNomeCliente());
            System.out.println("Fone:\t" + cli.getTelefone());
        }
    }

    public static void deletarEditora() {
        System.out.println("-- Deletar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.nextLine();
        EditoraServicos cEditoras = ServicosFactory.getEditoraServicos();

        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = cadEditora.getEditoraCNPJ(cnpj);
            if (edi != null) {
                cadEditora.removeEditora(edi);
                System.out.println("Editora deletada com sucesso!");
            } else {
                System.out.println("Editora não consta na base de dados!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }//fim deletarEditora

    private static void cadastrarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        System.out.println("-- Cadastro de Livro --");
        System.out.print("Informe o ISBN: ");
        String isbn = leia.nextLine();
        if (livroS.buscaLivroISBN(isbn).getIsbn() != null) {
            System.out.println("Livro ja cadastrado");
        } else {
            // não precisa alterar para o serviço, pois 
            //o id será gerido pelo banco de dados
            int idLivro = cadLivro.geraID();
            System.out.print("Informe o Título do Livro: ");
            String titulo = leia.nextLine();
            System.out.print("Informe o Autor: ");
            String autor = leia.nextLine();
            System.out.print("Infome o assunto:");
            String assunto = leia.nextLine();
            System.out.print("Informe o estoque: ");
            int estoque = leiaNumInt();
            System.out.print("Informe o preço: ");
            float preco = leiaNumFloat();
            boolean isCNPJ = false;
            Editora idEditora = null;
            do {
                System.out.print("Informe o CNPJ da Editora: ");
                String cnpj = leia.nextLine();
                isCNPJ = Validadores.isCNPJ(cnpj);
                if (isCNPJ) {
                    idEditora = editoraS.buscarEditorabycnpj(cnpj);
                    if (idEditora.getCnpj() == null) {
                        System.out.println("Editora não cadastrada!");
                        isCNPJ = false;
                    }
                } else {
                    System.out.println("CNPJ inválido!");
                }

            } while (!isCNPJ);
            Livro l = new Livro(idLivro, titulo, autor, assunto, isbn, estoque, preco, idEditora);
            //cadLivro.addLivro(l);
            livroS.cadastrarLivro(l);
            System.out.println("Livro Cadastrado com sucesso");
        }
    }

    private static void editarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        System.out.println("-- Editar Livro --");
        System.out.print("Informe o ISBN: ");
        String isbn = leia.nextLine();
        Livro li = cadLivro.getLivroISBN(isbn);
        if (li != null) {
            System.out.println("1 - Estoque");
            System.out.println("2 - Preço");
            System.out.println("3 - Todos acima");
            System.out.println("0 - Cancelar");
            System.out.print("Digite aqui: ");
            int op = leiaNumInt();
            if (op == 1 || op == 3) {
                System.out.println("Estoque atual:\t" + li.getEstoque());
                System.out.print("Informe novo estoque: ");
                li.setEstoque(leiaNumInt());
            }
            if (op == 2 || op == 3) {
                System.out.println("Preço atual:\t" + li.getPreco());
                System.out.print("Informe novo preço: ");
                li.setPreco(leiaNumFloat());
            }
            if (op == 0) {
                System.out.println("Operação cancelada pelo usuário!");
                return;
            }
            livroS.atualizarLivro(li);
            System.out.println("Livro Editado:");
            System.out.println(li.toString());
        } else {
            System.out.println("ISBN inválido!");
        }
    }

    private static void listarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        if (!livroS.buscarLivros().isEmpty()) {

            System.out.println("-- Lista de livros --");
            for (Livro livro : livroS.buscarLivros()) {
                /*System.out.println("ISBN: \t" + livro.getIsbn());
            System.out.println("Título:\t" + livro.getTítulo());
            System.out.println("Atutor: \t" + livro.getAutor());
            System.out.println("Estoque: \t" + livro.getEstoque());
            System.out.println("Assunto: \t" + livro.getAssunto());
                 */
                System.out.println(livro.toString());
            }
        } else {
            System.out.println("Livros não cadastrados.");
        }
    }

    private static void deletarLivro() {
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        System.out.println("-- Deletar Livro -- ");
        System.out.print("Informe o ISBN: ");
        String isbn = leia.nextLine();

        //Livro li = cadLivro.getLivroISBN(isbn);
        Livro li = livroS.buscaLivroISBN(isbn);
        if (li != null) {
            System.out.println("Livro" + li.getTítulo() + "Será deletado!");
            //cadLivro.removeLivro(li);
            livroS.deletarLivro(isbn);
        } else {
            System.out.println("ISBN não encontrado!");
        }
    }

    private static void vendaLivro() {
        VendaLivroServicos vlS = ServicosFactory.getVendaLivros();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        LivroServicos livroS = ServicosFactory.getLivroServicos();

        int idVendaLivro;
        Cliente idCliente = null;
        ArrayList<Livro> livros = new ArrayList<>();
        float subTotal = 0;
        LocalDate dataVenda = LocalDate.now();

        do {
            System.out.print("Informe o CPF do Cliente: ");
            String CPF = leia.nextLine();
            if (Validadores.isCPF(CPF)) {
                //idCliente = cadCliente.getClienteCPF(CPF);
                idCliente = clienteS.buscarClientebyCPF(CPF);
                if (idCliente.getCpf() == null) {
                    System.out.println("Cliente não cadastrado!");
                }
            } else {
                System.out.println("CPF INVÀLIDO");
            }
        } while (idCliente.getCpf() == null);

        boolean venda = true;

        do {
            Livro li = null;
            String Isbn;

            do {
                System.out.print("Informe o ISBN:");
                Isbn = leia.nextLine();
                //li = cadLivro.getLivroISBN(Isbn); ANTES
                li = livroS.buscaLivroISBN(Isbn); //AGORA
                if (li.getIsbn() == null) {
                    System.out.println("Livro não encontrado, tente novamente");
                }
            } while (li.getIsbn() == null);
            if (li.getEstoque()>0) {
                livros.add(li);
                //cadLivro.atualizaEstoqueLivro(li.getIsbn()); ANTES
                int estoque = li.getEstoque()-1; //AGORA
                li.setEstoque(estoque);
                livroS.atualizarLivro(li);
                subTotal += li.getPreco();
                
            }else{
                 System.out.println(li.getTítulo() + "não tem estoque");
            }
            System.out.println("Deseja comprar mais livros?"
                        + "\n1 - Sim | 2- Não"
                        + "\n Digite: ");
                if (leiaNumInt() == 2) {
                    venda = false;
                }
            idVendaLivro = cadVendaLivro.geraID();
            VendaLivro vl = new VendaLivro(idVendaLivro, idCliente, livros, subTotal, dataVenda);
            //cadVendaLivro.addVendaLivro(vl); ANTES
            vlS.VendaLivros(vl); // AGORA114
            
            System.out.println(" -- VENDA -- " + vl.toString());
        } while (venda);
    }

}
/*
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o Nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o Endereco: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.println("Informe o telefone:");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo: ");
                        System.out.print("Informe o Nome: ");
                        cli.setNomeCliente(leia.nextLine());

                        System.out.print("Informe o Endereco: ");
                        cli.setEndereco(leia.nextLine());

                        System.out.println("Informe o telefone:");
                        cli.setTelefone(leia.nextLine());
                        break;

                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }
                /*
                System.out.println("Cliente: \n" + cli.toString());

            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF inválido");
        }
    }

    private static void cadastrarEditora() {
        

    }

 */
