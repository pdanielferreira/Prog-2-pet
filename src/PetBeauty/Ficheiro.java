package PetBeauty;

import java.io.*;
import java.util.*;

public class Ficheiro {
    public static ArrayList<Utilizador> existeU = new ArrayList<>();
    public static ArrayList<Animal> existeA = new ArrayList<>();
    ArrayList utilizadores = new ArrayList();

    public Ficheiro() { }

    /*
        SISTEMA DE LOGIN
        IMPORTA TODOS OS UTILIZADORES INDEPENDENTE DO TIPO, PARA O ARRAY EXISTEU
     */
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

                    importaAnimais();

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

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO ANIMAIS.DAT
     */
    public void importaAnimais() throws FileNotFoundException{
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("animais.dat");
        Formatter formatter;
        /*
            Caso não exista, o ficheiro
         */
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("0:0:0:0");
            formatter.flush();
            formatter.close();
        }
        else {
            Scanner scn = new Scanner(System.in);
            try {

                String linha;
                String[] divLinha;
                Scanner sc = new Scanner(new File("animais.dat"));
                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeA.add(new Animal(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim()));

                } while (sc.hasNextLine());


            } catch (Exception e) {
                System.out.println(textoVermelho + "FICHEIRO COM DADOS DE ANIMAIS NÃO ENCONTRADO!" + textoNormal);
                System.exit(0);
            }
        }
    }

    /*
        ACRESCENTA UM ANIMAL A LISTA
     */
    
    /*
        PERMITE EDITAR INOFRMAÇÕ DE UM UTILIZADOR
     */
    public void alterarDados(String utilizador) throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        String textoLaranja = "\033[33m";

        Scanner scn = new Scanner(System.in);
        String us = utilizador.toUpperCase();
        int esc = 0, count = 1;
        Scanner s = new Scanner(new File("registo.dat"));
        while (s.hasNext()){
            utilizadores.add(s.next());
        }
        s.close();
        try {
            int flag = 0;
            do {
                String linha;
                String[] divLinha;
                Scanner sc = new Scanner(new File("registo.dat"));

                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeU.add(new Utilizador(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim()));

                    if (us.equals(divLinha[0])) {
                        System.out.println(textoRoxo + "\t----- PERFIL CLIENTE -----" + textoNormal);
                        System.out.println("\t\tUSERNAME - " + divLinha[0]);
                        System.out.println("\t\tNOME - " + divLinha[2]);
                        System.out.println("\t\tAPELIDO - " + divLinha[3]);
                        System.out.println("\t\tNIF - " + divLinha[4]);
                        System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + divLinha[5]);
                        flag = 1;
                        break;
                    }else{
                        count ++;
                    }
                } while (sc.hasNextLine());

                if (flag == 1) {
                    System.out.print(textoRoxo + "\n\tDESEJA EDITAR O SEU PERFIL [S/N]: " + textoNormal);
                    String resp = scn.nextLine();

                    if (resp.toUpperCase().equals("S")) {
                        System.out.println("\n\tQUAL O CAMPO A ALTERAR: ");
                        System.out.println("\t\t1 - PASSWORD");
                        System.out.println("\t\t2 - NOME");
                        System.out.println("\t\t3 - APELIDO");
                        System.out.println("\t\t4 - NIF");
                        System.out.println("\t\t5 - NÚMERO DE TELEMÓVEL");

                        do {
                            System.out.print("\tSELECIONE O CAMPO: ");
                            esc = scn.nextInt();
                        } while (esc < 0 || esc > 5);

                        switch (esc) {
                            //EDIÇÃO DE PALAVRA PASS
                            case 1: {
                                Scanner sc1 = new Scanner(System.in);
                                if(us.equals(divLinha[0])){
                                    System.out.println(textoAmarelo + "\n\tPASSWORD ANTIGA: " + divLinha[1] + textoNormal);
                                }
                                System.out.print(textoRoxo + "\tNOVA PASSWORD: " + textoNormal);
                                String novaPass = sc1.nextLine();

                                for(int i = 0; i< utilizadores.size(); i++) {
                                    utilizadores.set(count-1, divLinha[0] + ":" + novaPass + ":" +  divLinha[2] + ":" +  divLinha[3] + ":" +  divLinha[4]+ ":" +  divLinha[5]+ ":" +  divLinha[6]);
                                }

                                FileWriter f = new FileWriter("registo.dat", false);
                                Formatter formatter = new Formatter(f);
                                for(int i = 0; i < utilizadores.size(); i ++) {
                                    formatter.format((String) utilizadores.get(i) + "\n");
                                    formatter.flush();
                                }
                                formatter.close();

                                System.out.println(textoVerde + "\tPALAVRA PASS ALTERADA COM SUCESSO" + textoNormal);
                                System.out.println(textoLaranja + "\t\t::ATENÇÃO::");
                                System.out.println("\tAS ALTERAÇÕES APENAS FICAM ATIVAS APÓS A APLICAÇÃO RENICIAR" + textoNormal);
                                break;
                            }
                            //EDIÇÃO DE NOME
                            case 2: {
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print(textoRoxo + "\tNOVO NOME: " + textoNormal);
                                String primeiroNome = sc1.nextLine();

                                for(int i = 0; i< utilizadores.size(); i++) {
                                    utilizadores.set(count-1, divLinha[0] + ":" + divLinha[1]+ ":" +  primeiroNome + ":" +  divLinha[3] + ":" +  divLinha[4]+ ":" +  divLinha[5]+ ":" +  divLinha[6]);
                                }

                                FileWriter f = new FileWriter("registo.dat", false);
                                Formatter formatter = new Formatter(f);
                                for(int i = 0; i < utilizadores.size(); i ++) {
                                    formatter.format((String) utilizadores.get(i) + "\n");
                                    formatter.flush();
                                }
                                formatter.close();

                                System.out.println(textoVerde + "\tNOME ALTERADO COM SUCESSO" + textoNormal);
                                System.out.println(textoLaranja + "\t\t::ATENÇÃO::");
                                System.out.println("\tAS ALTERAÇÕES APENAS FICAM ATIVAS APÓS A APLICAÇÃO RENICIAR" + textoNormal);
                                break;
                            }
                            //EDIÇÃO DE APELIDO
                            case 3:{
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print(textoRoxo + "\tNOVO APELIDO: " + textoNormal);
                                String apelido = sc1.nextLine();

                                for(int i = 0; i< utilizadores.size(); i++) {
                                    utilizadores.set(count-1, divLinha[0] + ":" + divLinha[1]+ ":" +  divLinha[2] + ":" +  apelido + ":" +  divLinha[4] + ":" +  divLinha[5] + ":" +  divLinha[6]);
                                }

                                FileWriter f = new FileWriter("registo.dat", false);
                                Formatter formatter = new Formatter(f);
                                for(int i = 0; i < utilizadores.size(); i ++) {
                                    formatter.format((String) utilizadores.get(i) + "\n");
                                    formatter.flush();
                                }
                                formatter.close();

                                System.out.println(textoVerde + "\tAPELIDO ALTERADO COM SUCESSO" + textoNormal);
                                System.out.println(textoLaranja + "\t\t::ATENÇÃO::");
                                System.out.println("\tAS ALTERAÇÕES APENAS FICAM ATIVAS APÓS A APLICAÇÃO RENICIAR" + textoNormal);
                                break;
                            }
                            //EDIÇÃO DE NIF
                            case 4:{
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print(textoRoxo + "\tNOVO NIF: " + textoNormal);
                                String NIF = sc1.nextLine();

                                for(int i = 0; i< utilizadores.size(); i++) {
                                    utilizadores.set(count-1, divLinha[0] + ":" + divLinha[1]+ ":" +  divLinha[2] + ":" +  divLinha[3] + ":" +  NIF + ":" +  divLinha[5] + ":" +  divLinha[6]);
                                }

                                FileWriter f = new FileWriter("registo.dat", false);
                                Formatter formatter = new Formatter(f);
                                for(int i = 0; i < utilizadores.size(); i ++) {
                                    formatter.format((String) utilizadores.get(i) + "\n");
                                    formatter.flush();
                                }
                                formatter.close();

                                System.out.println(textoVerde + "\tNIF ALTERADO COM SUCESSO" + textoNormal);
                                System.out.println(textoLaranja + "\t\t::ATENÇÃO::");
                                System.out.println("\tAS ALTERAÇÕES APENAS FICAM ATIVAS APÓS A APLICAÇÃO RENICIAR" + textoNormal);
                                break;
                            }
                            //EDIÇÃO DE NUMERO DE TELEMOVEL
                            case 5:{
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print(textoRoxo + "\tNOVO NÚMERO DE TELEMÓVEL: " + textoNormal);
                                String nTel = sc1.nextLine();

                                for(int i = 0; i< utilizadores.size(); i++) {
                                    utilizadores.set(count-1, divLinha[0] + ":" + divLinha[1]+ ":" +  divLinha[2] + ":" +  divLinha[3] + ":" +  divLinha[4] + ":" +  nTel + ":" +  divLinha[6]);
                                }

                                FileWriter f = new FileWriter("registo.dat", false);
                                Formatter formatter = new Formatter(f);
                                for(int i = 0; i < utilizadores.size(); i ++) {
                                    formatter.format((String) utilizadores.get(i) + "\n");
                                    formatter.flush();
                                }
                                formatter.close();

                                System.out.println(textoVerde + "\tNÚMERO DE TELEMÓVEL ALTERADO COM SUCESSO" + textoNormal);
                                System.out.println(textoLaranja + "\t\t::ATENÇÃO::");
                                System.out.println("\tAS ALTERAÇÕES APENAS FICAM ATIVAS APÓS A APLICAÇÃO RENICIAR" + textoNormal);
                                break;
                            }
                        }
                    }else {
                        return;
                    }
                }
            }while (flag == 0) ;
        }catch(Exception e){
            System.out.println(textoVermelho + "Ficheiro não encontrado!" + textoNormal);
            System.exit(0);
        }
    }
}
