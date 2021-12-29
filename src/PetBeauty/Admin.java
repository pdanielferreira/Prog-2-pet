package PetBeauty;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Admin extends Utilizador {
    public Admin(String username, String password, String nome, String apelido, String NIF, String nTel, String tipo) {
        super(username, password, nome, apelido, NIF, nTel, "3");
    }

    /*
        MENU DO ADMINISTRADOR PETBEATY
     */
    public void menuAdmin() throws IOException, ParseException {
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


        int op = 0, aux;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(System.lineSeparator().repeat(3));
            System.out.println(textoAmarelo + "\t-------------- MENU ADMINISTRADOR --------------");
            System.out.println("\t\t1 - VER PERFIL");
            System.out.println("\t\t2 - EDITAR PERFIL");
            System.out.println("\t\t3 - LISTAR EMPRESAS");
            System.out.println("\t\t4 - BLOQUEAR/DESBLOQUEAR EMPRESAS");
            System.out.println("\t\t5 - VER FUNCIONÁRIOS");
            System.out.println("\t\t6 - DESATIVAR/ATIVAR FUNCIONÁRIOS");
            System.out.println("\t\t7 - VER CONSULTAS AGENDADAS");
            System.out.println("\t\t8 - VER ADMIN");
            System.out.println("\t\t9 - REGISTAR ADMIN \n");
            System.out.println("\t\t0 - SAIR" + textoNormal);

            do{
                System.out.print("Escolha uma opção: ");
                op = sc.nextInt();
            }while(op < 0 || op > 8);


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
                    e.listarTodasEmpresas();
                    break;
                }

                /*
                    BLOQUEAR/DESBLOQUEAR EMPRESAS
                 */
                case 4:{
                    int opB=0;
                    do{
                        System.out.println(System.lineSeparator().repeat(3));
                        System.out.println(textoAmarelo + "\t-------------- OPCÇÕES --------------");
                        System.out.println("\t\t1 - BLOQUEAR EMPRESA");
                        System.out.println("\t\t2 - DESBLOQUEAR EMPRESA");

                        System.out.print("Escolha uma opção: ");
                        opB = sc.nextInt()-1;
                    }while(opB<0 || opB>1);

                    e.bloquearEmpresas(opB);

                    break;
                }
                /*
                    VER TODOS OS FUNCIONÁRIOS DA EMPRESA
                 */
                case 5:{
                    e.lerTodosFuncEmp();
                    break;
                }

                /*
                    ADICIONAR NOVOS FUNCIONÁRIOS
                 */
                case 6:{
                    int opB=0;
                    do{
                        System.out.println(System.lineSeparator().repeat(3));
                        System.out.println(textoAmarelo + "\t-------------- OPCÇÕES --------------");
                        System.out.println("\t\t1 - BLOQUEAR FUNCIONÁRIO");
                        System.out.println("\t\t2 - DESBLOQUEAR FUNCIOPNÁRIO");

                        System.out.print("Escolha uma opção: ");
                        opB = sc.nextInt()-1;
                    }while(opB<0 || opB>1);

                    e.bloquearFunc(opB);
                    break;
                }
                /*
                    MOTRA TODAS AS CONSULTAS AGENDADAS
                 */
                case 7:{
                    m.mostrarTodasMarcacoes();
                    break;
                }
                /*
                    MOTRA TODOS OS ADMINS REGISTADOS
                 */
                case 8:{
                    f.listarA();
                    break;
                }
                /*
                    REGISTA NOVOS ADMINS
                 */
                case 9:{
                    f.registarAdmin();
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
