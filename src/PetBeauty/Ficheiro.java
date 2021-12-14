package PetBeauty;

import java.io.*;
import java.util.*;

public class Ficheiro {
    public static ArrayList<Utilizador> existeU = new ArrayList<>();
    //ArrayList useers = new ArrayList();

    public Ficheiro() { }

    public void LerLogin() throws FileNotFoundException {

        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("registo.dat");
        Formatter formatter;
        /*
            Caso não exista, o ficheiro
         */
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("ADMIN:ADMIN:Admin:Admin:123456789:123456789:3");
            formatter.flush();
            formatter.close();
        }
        /*
            Caso á exista, o ficheiro
         */
        else {
            Scanner scn = new Scanner(System.in);
            try {
                int flag = 0;
                int tipo = 0;
                do {
                    System.out.print("Utilizador: ");
                    String user = scn.nextLine();

                    System.out.print("Palavra Pass: ");
                    String pass = scn.nextLine();

                    String us = user.toUpperCase();
                    String pss = pass.toUpperCase();

                    String linha;
                    String[] divLinha;
                    Scanner sc = new Scanner(new File("registo.dat"));
                    do {
                        linha = sc.nextLine();
                        divLinha = linha.split(":");
                        existeU.add(new Utilizador(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim()));

                        if (us.equals(divLinha[0]) && pss.equals(divLinha[1])) {
                            flag = 1;
                            if (divLinha[6].equals("0")) {
                                tipo = 0;
                            } else if (divLinha[6].equals("1")) {
                                tipo = 1;
                            } else if (divLinha[6].equals("2")) {
                                tipo = 2;
                            } else if (divLinha[6].equals("3")) {
                                tipo = 3;
                            }
                            break;
                        }
                    } while (sc.hasNextLine());

                    //Direcionamento de Cliente
                    if (flag == 1 && tipo == 0) {
                        System.out.println(textoVerde + "\n\tCLIENTE LOGADO COM SUCESSO!" + textoNormal);
                        Thread.sleep(1000);
                        Cliente cl = new Cliente(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim());
                        cl.menuCliente();
                        break;
                    }
                    //Direcionamento de Funcionário
                    else if (flag == 1 && tipo == 1) {
                        System.out.println(textoVerde + "\n\tFUNCIONÁRIO LOGADO COM SUCESSO!" + textoNormal);
                        Thread.sleep(1000);
                        System.out.println("Entra menu funcionário");
                        break;
                    }
                    //Direcionamento de Dono de Empresa
                    else if (flag == 1 && tipo == 2) {
                        System.out.println(textoVerde + "\n\tDONO DE EMPRESA LOGADO COM SUCESSO!" + textoNormal);
                        Thread.sleep(1000);
                        System.out.println("Entra menu dono de empresa");
                        break;
                    }
                    //Direcionamento de Admin
                    else if (flag == 1 && tipo == 3) {
                        System.out.println(textoVerde + "\n\tADMIN LOGADO COM SUCESSO!" + textoNormal);
                        Thread.sleep(1000);
                        System.out.println("Entra menu admin");
                        break;
                    }
                    /*
                        ERRO: CASO O NÚMERO SEJA ALTERADO MANUALMENTE NO FICHEIRO
                     */
                    if (tipo != 0 && tipo != 1 && tipo != 2 && tipo != 3 && flag != 0) {
                        System.out.println(textoVermelho + "\tERRO! CONTACTE O DESENVOLVEDOR." + textoNormal);
                    }
                    /*
                        ERRO: CASO O UTILIZADOR/PASSWORD SEJAM ERRADOS
                     */
                    if (flag == 0) {
                        System.out.println( textoVermelho + "\t USERNAME/PASSWORD INVÁLIDOS! " + textoNormal);
                    }
                } while (flag == 0);

            } catch (Exception e) {
                System.out.println( textoVermelho +"Ficheiro não encontrado!"  + textoNormal);
                System.exit(0);
            }
        }
    }
}
