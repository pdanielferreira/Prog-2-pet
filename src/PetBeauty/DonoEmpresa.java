package PetBeauty;

import java.io.IOException;
import java.util.Scanner;

public class DonoEmpresa extends Utilizador{

    public DonoEmpresa(String username, String password, String nome, String apelido, String NIF, String nTel, String tipo) {
        super(username, password, nome, apelido, NIF, nTel, "0");
    }

    /*
        MENU DO DONO DE EMPRESA
     */
    public void menuDonoEmpresa() throws IOException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";

        Animais a = new Animais();
        a.importaAnimais();

        Empresas e = new Empresas();
        e.importaEmpresas();

        int op = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(System.lineSeparator().repeat(3));
            System.out.println(textoAmarelo + "\t-------------- MENU DONO DE EMPRESA --------------");
            System.out.println("\t\t1 - VER PERFIL");
            System.out.println("\t\t2 - EDITAR PERFIL");
            System.out.println("\t\t3 - VER EMPRESAS");
            System.out.println("\t\t4 - CRIAR EMPRESAS");
            System.out.println("\t\t5 - VER FUNCIONÁRIOS");
            System.out.println("\t\t6 - CRIAR FUNCIONÁRIOS");
            System.out.println("\t\t7 - VER CONSULTAS AGENDADAS \n");
            System.out.println("\t\t0 - SAIR" + textoNormal);

            do{
                System.out.print("Escolha uma opção: ");
                op = sc.nextInt();
            }while(op < 0 || op > 7);


            switch(op){
                /*
                    VER INFORMAÇÃO COMPELETA DO SEU PERFIL
                 */
                case 1:{
                    perfilDono();
                    break;
                }

                /*
                    EDITAR PERFIL
                 */
                case 2:{
                    Ficheiro file = new Ficheiro();
                    file.alterarDados(getUsername());
                    break;
                }

                /*
                    VER TODOS AS EMPRESAS
                 */
                case 3:{
                    e.listarEmpresas(getNIF());
                    break;
                }

                /*
                    ADICIONA NOVAS EMPRESAS
                 */
                case 4:{
                    e.RegistarNovaEmpresa(getNIF());
                    break;
                }
                /*
                    VER TODOS OS FUNCIONÁRIOS DA EMPRESA
                 */
                case 5:{

                    break;
                }
                /*
                    ADICIONAR NOVOS FUNCIONÁRIOS
                 */
                case 6:{

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
        VISUALIZAR TODOS OS SEUS DADOS EXEPTO PALAVRA PASS
     */
    public void perfilDono(){
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";

        System.out.println(System.lineSeparator().repeat(2));
        System.out.println(textoRoxo + "\t----- PERFIL DONO DA EMPRESA -----" + textoNormal);
        System.out.println("\t\tNOME - " + getPrimeiroNome());
        System.out.println("\t\tAPELIDO - " + getApelido());
        System.out.println("\t\tUSERNAME - " + getUsername());
        System.out.println("\t\tNIF - " + getNIF());
        System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + getnTel());
    }
}
