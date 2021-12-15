package PetBeauty;

import java.io.*;
import java.util.*;

public class Registo {
    public Registo() { }

    public void login() throws FileNotFoundException, IOException {
        Ficheiro ler = new Ficheiro();
        ler.LerLogin();
    }

    /*
        REGISTAR UM NOVO UTILIZADOR
     */
    public void registarUtilizador() throws IOException{
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        FileWriter registar = new FileWriter("registo.dat", true);
        Scanner sc = new Scanner(System.in);
        Formatter formatter;
        String nif, nome, apelido, utilizador, pass, nTel, escolha;
        int copia=0, op, erro1=0, erro2=0, copia2=0;

        try{
            do {
                System.out.print("NIF: ");
                nif = sc.nextLine();

                Ficheiro f = new Ficheiro();
                copia = f.LerRegistoIguais(nif);

                if (nif.length()>9 || nif.length()<9){
                    System.out.println(textoVermelho + "\t\tNIF INVÁLIDO!" + textoNormal);
                    erro1=1;
                }else{
                    erro1=0;
                }

            }while(copia==1 || erro1==1);

            System.out.println(textoVerde + "\t\tNIF VÁLIDO!" + textoNormal);

            System.out.print("PRIMEIRO NOME: ");
            nome = sc.nextLine();
            System.out.print("APELIDO: ");
            apelido = sc.nextLine();

            do {
                System.out.print("NÚMERO DE TELEMÓVEL: ");
                nTel = sc.nextLine();

                if (nTel.length()>9 || nTel.length()<9){
                    System.out.println(textoVermelho + "\t\tNÚMERO DE TELEMÓVEL INVÁLIDO!" + textoNormal);
                    erro2=1;
                }else{
                    erro2=0;
                }

            }while(erro2==1);
            System.out.println(textoVerde + "\t\tNÚMERO DE TELEMÓVEL VÁLIDO!" + textoNormal);

            do {
                System.out.print("UTILIZADOR: ");
                utilizador = sc.nextLine();

                Ficheiro f = new Ficheiro();
                copia2 = f.LerUtilizadoresIguais(utilizador);

            }while(copia2==1);

            System.out.print("PALAVRA PASS: ");
            pass = sc.nextLine();

            do{
                System.out.println("\t\t--- Escolha como pretende se registar --- ");
                System.out.println("\t\t\t 1 - Cliente ");
                System.out.println("\t\t\t 2 - Dono de Empresa ");
                op = sc.nextInt();
            }while(op!=1 && op!=2);

            if (op==1){
                op=0;
            }else{
                op=2;
            }

            formatter = new Formatter(registar);
            formatter.format(utilizador + ":");
            formatter.format(pass + ":");
            formatter.format(nome + ":");
            formatter.format(apelido + ":");
            formatter.format(nif + ":");
            formatter.format(nTel + ":");
            formatter.format(op + "\n");
            formatter.flush();
            formatter.close();

            if(formatter != null){

                if(op==0){
                    escolha="CLIENTE";
                }else{
                    escolha="DONO DE EMPRESA";
                }

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoVerde + "\n\tFOI ADICIONADO UM UTILIZADOR COMO: " + escolha + textoNormal);
                System.out.println(System.lineSeparator().repeat(1));
                System.out.println(textoRoxo + "\t----- DADOS DO REGISTO -----" + textoNormal);
                System.out.println("\t\tNOME - " + nome);
                System.out.println("\t\tAPELIDO - " + apelido);
                System.out.println("\t\tUSERNAME - " + utilizador);
                System.out.println("\t\tNIF - " + nif);
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + nTel);


            }else{
                System.out.println(textoVermelho + "REGISTO NÃO EFETUADO" + textoNormal);
            }

        }catch (Exception e) {
            System.out.println(textoVermelho + "FICHEIRO NÃO ENCONTRADO!" + textoNormal);
            System.exit(0);
        }
    }
}
