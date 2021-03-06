package PetBeauty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Cliente extends Utilizador{

    public Cliente(String username, String password, String nome, String apelido, String NIF, String nTel, String tipo) {
        super(username, password, nome, apelido, NIF, nTel, "0");
    }

    /*
        MENU DO CLIENTE
     */
    public void menuCliente() throws IOException, ParseException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";

        Ficheiro f = new Ficheiro();
        f.importaU();

        Animais a = new Animais();
        a.importaAnimais();

        Empresas e = new Empresas();
        e.importaEmpresas();
        e.importaFuncionarios();

        Marcacoes m = new Marcacoes();
        m.importaMarcacoes();
        m.importaExtra();

        int op = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(System.lineSeparator().repeat(3));
            System.out.println(textoAmarelo + "\t-------------- MENU CLIENTE --------------");
            System.out.println("\t\t1 - Ver Perfil");
            System.out.println("\t\t2 - Editar Perfil");
            System.out.println("\t\t3 - Visualizar todos os animais");
            System.out.println("\t\t4 - Criar animais");
            System.out.println("\t\t5 - Marcar consultas");
            System.out.println("\t\t6 - Ver marcações");
            System.out.println("\t\t7 - Pagar consultas \n");
            System.out.println("\t\t0 - SAIR" + textoNormal);

            do{
                System.out.print("Escolha uma opção: ");
                op = sc.nextInt();
            }while(op < 0 || op > 7);


            switch(op){
                /*
                    Ver informação completa do cliente
                 */
                case 1:{
                    perfilCliente();
                    break;
                }

                /*
                    Editar dados co cliente
                 */
                case 2:{
                    Ficheiro file = new Ficheiro();
                    file.alterarDados(getUsername());
                    break;
                }

                /*
                    VER TODOS OS ANIMAIS COM O MESMO NIF DE DONO ASSOCIADO
                 */
                case 3:{
                    a.listarAnimais(getNIF());
                    break;
                }

                /*
                    ADICIONA NOVOS ANIMAIS
                 */
                case 4:{
                    a.RegistarAnimal(getNIF());
                    break;
                }

                /*
                    MARCAR NOVAS CONSULTAS
                 */
                case 5:{
                    m.registarConsulta(getNIF());
                    break;
                }

                /*
                    VER MARCAÇÕES
                 */
                case 6:{
                    m.mostrarMarcacoesCliente(getNIF());
                    break;
                }

                /*
                    PAGAR MARCAÇÕES
                 */
                case 7:{
                    m.pagarConsulta(getNIF());
                    break;
                }

                default:{
                    System.out.println(textoVerde + "A SAIR ..." + textoNormal);
                    System.exit(0);
                }
            }
        }while(op != 0);
    }

    /*
        Visualizar dados compeltos do cliente, excluindo a palavra-pass
     */
    public void perfilCliente(){
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";

        System.out.println(System.lineSeparator().repeat(2));
        System.out.println(textoRoxo + "\t----- PERFIL CLIENTE -----" + textoNormal);
        System.out.println("\t\tNOME - " + getPrimeiroNome());
        System.out.println("\t\tAPELIDO - " + getApelido());
        System.out.println("\t\tUSERNAME - " + getUsername());
        System.out.println("\t\tNIF - " + getNIF());
        System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + getnTel());
    }
}
