package PetBeauty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Empresas {
    public static ArrayList<Empresa> existeE = new ArrayList<>();
    public static ArrayList<AssociacaoFuncEmpresa> existeF = new ArrayList<>();

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO EMPRESAS.DAT
     */
    public void importaEmpresas() throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("empresas.dat");
        Formatter formatter;
        /*
            Caso não exista, o ficheiro
         */
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("0:0:0:0:0:0\n");
            formatter.flush();
            formatter.close();
        }
        else {
            Scanner scn = new Scanner(System.in);
            try {

                String linha;
                String[] divLinha;
                Scanner sc = new Scanner(new File("empresas.dat"));
                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeE.add(new Empresa(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim()));

                } while (sc.hasNextLine());


            } catch (Exception e) {
                System.out.println(textoVermelho + "FICHEIRO COM DADOS DE EMPRESAS NÃO ENCONTRADO!" + textoNormal);
                System.exit(0);
            }
        }
    }

    /*
        ANÁLISA SE O NIF JÁ EXISTE
     */
    public int LerRegistoIguais (String nif) {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        int copia=0;

        try{
            String linha;
            String[] divLinha;
            Scanner sc = new Scanner(new File("empresas.dat"));
            do{
                linha = sc.nextLine();
                divLinha = linha.split(":");
                existeE.add(new Empresa(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim()));

                if(nif.equals(divLinha[4])){
                    copia = 1;
                    break;
                }
            }while(sc.hasNextLine());

            if(copia == 1){
                System.out.println(textoVermelho + "\tNIF JÁ EXISTENTE!" + textoNormal);
            }

        }catch (Exception e) {
            System.out.println(textoVermelho + "\tFICHEIRO NÃO ENCONTRADO!" + textoNormal);
            System.exit(0);
        }
        return copia;
    }

    /*
        REGISTAR UM NOVA EMPRESA
     */
    public void RegistarNovaEmpresa(String nifD) throws IOException{
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        FileWriter registar = new FileWriter("empresas.dat", true);
        Scanner sc = new Scanner(System.in);
        Formatter formatter;
        String nif, nome, morada, localidade, nTel, escolha;
        int copia=0, op, erro1=0, erro2=0;

        try{
            do {
                System.out.print("NIF: ");
                nif = sc.nextLine();

                copia = LerRegistoIguais(nif);

                if (nif.length()>9 || nif.length()<9){
                    System.out.println(textoVermelho + "\t\tNIF INVÁLIDO!" + textoNormal);
                    erro1=1;
                }else{
                    erro1=0;
                }

            }while(copia==1 || erro1==1);

            System.out.println(textoVerde + "\t\tNIF VÁLIDO!" + textoNormal);

            System.out.print("NOME DA EMPRESA: ");
            nome = sc.nextLine();
            System.out.print("MORADA DA EMPRESA: ");
            morada = sc.nextLine();
            System.out.print("LOCALIDADE: ");
            localidade = sc.nextLine();

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

            formatter = new Formatter(registar);
            formatter.format(nif + ":");
            formatter.format(nome + ":");
            formatter.format(morada + ":");
            formatter.format(localidade + ":");
            formatter.format(nTel + ":");
            formatter.format(nifD + "\n");
            formatter.flush();
            formatter.close();

            if(formatter != null){

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoVerde + "\n\t--- FOI ADICIONADO UMA EMPRESA ---" + textoNormal);
                System.out.println(System.lineSeparator().repeat(1));
                System.out.println(textoRoxo + "\t----- DADOS DO REGISTO -----" + textoNormal);
                System.out.println("\t\tNOME - " + nome);
                System.out.println("\t\tNIF - " + nif);
                System.out.println("\t\tMORADA - " + morada);
                System.out.println("\t\tLOCALIDADE - " + localidade);
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + nTel);
                System.out.println("\t\tNIF DO PROPRIETÁRIO - " + nifD);


            }else{
                System.out.println(textoVermelho + "REGISTO NÃO EFETUADO" + textoNormal);
            }

        }catch (Exception e) {
            System.out.println(textoVermelho + "FICHEIRO NÃO ENCONTRADO!" + textoNormal);
            System.exit(0);
        }
    }

    /*
        LISTA TODAS AS EMPRESAS DO MESMO DONO
     */
    public void listarEmpresas(String nifD){
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        int count=0;

        System.out.println(textoRoxo + "\t\t TODAS AS EMPRESAS ASSOCIADOS A SI:" + textoNormal);

        for(int i=0; i<existeE.size(); i++) {
            if(nifD.equals(existeE.get(i).getNifDono())){

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- EMPRESA: " + existeE.get(i).getNifEmpresa() + " -----" + textoNormal);
                System.out.println(textoRoxo + "\t----- CÓDIGO DE EMPRESA: " + i + " -----" + textoNormal);
                System.out.println("\t\tNOME - " + existeE.get(i).getNome());
                System.out.println("\t\tMORADA - " + existeE.get(i).getMorada());
                System.out.println("\t\tLOCALIDADE - " + existeE.get(i).getLocalidade());
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + existeE.get(i).getnTel());
                System.out.println("\t\tNIF DO DONO - " + existeE.get(i).getNifDono());
                count++;
            }
        }
        if(count==0){
            System.out.println(textoVermelho + "\t\t NÃO EXISTEM EMPRESAS REGISTADOS COM O SEU NIF" + textoNormal);
        }

    }

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO FUNCIONARIOS.DAT
     */
    public void importaFuncionarios() throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("funcionarios.dat");
        Formatter formatter;
        /*
            Caso não exista, o ficheiro
         */
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("0:0:0\n");
            formatter.flush();
            formatter.close();
        }
        else {
            Scanner scn = new Scanner(System.in);
            try {

                String linha;
                String[] divLinha;
                Scanner sc = new Scanner(new File("funcionarios.dat"));
                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeF.add(new AssociacaoFuncEmpresa(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim()));

                } while (sc.hasNextLine());


            } catch (Exception e) {
                System.out.println(textoVermelho + "FICHEIRO COM DADOS DE FUNCIONÁRIOS NÃO ENCONTRADO!" + textoNormal);
                System.exit(0);
            }
        }
    }

    /*
        ASSOCIAR FUNCIONARIO A UMA EMPRESA
     */
    public void associarFuncEmpresa(String nifF, int valor) throws IOException{
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        String nifEmpresa = null;

        for (int i=0; i<existeE.size(); i++){
            if (i==valor){
                nifEmpresa = existeE.get(i).getNifEmpresa();
            }
        }
        FileWriter associar = new FileWriter("funcionarios.dat", true);
        Scanner sc = new Scanner(System.in);
        Formatter formatter;

        try{
            formatter = new Formatter(associar);
            formatter.format(nifF + ":");
            formatter.format(nifEmpresa + ":");
            formatter.format(1 + "\n");
            formatter.flush();
            formatter.close();

            if(formatter != null){

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoVerde + "\n\tUM FUNCIONÁRIO FOI ASSOCIADO A EMPRESA: . " + nifEmpresa + textoNormal);
                System.out.println(System.lineSeparator().repeat(1));

                Ficheiro f = new Ficheiro();
                f.lerNIF(nifF);

                existeF.add(new AssociacaoFuncEmpresa(nifF, nifEmpresa, "1"));

            }else{
                System.out.println(textoVermelho + "ASSOCIAÇÃO NÃO EFETUADA" + textoNormal);
            }

        }catch (Exception e) {
            System.out.println(textoVermelho + "FICHEIRO NÃO ENCONTRADO!" + textoNormal);
            System.exit(0);
        }
    }

    /*
        LER FUNCIONÁRIOS DE UMA EMPRESA
     */
    public void lerFuncEmp(int valor) throws IOException{
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        String nifEmpresa = null;
        String txt = null;
        String nifF = null;

        for (int i=0; i<existeE.size(); i++){
            if (i==valor){
                nifEmpresa = existeE.get(i).getNifEmpresa();
            }
        }

        for (int i=0; i<existeF.size(); i++){
            if (existeF.get(i).getNifEmpresa().equals(nifEmpresa)){
                nifF=existeF.get(i).getNifFuncionario();
                String despedido = existeF.get(i).getAtivo();

                if (despedido.equals("1")){
                    txt = " A TRABALHAR ";
                }else{
                    txt = " DESPEDIDO ";
                }
            }
        }

        for(int j=0; j<Ficheiro.existeU.size(); j++){
            System.out.println("entra");
            if(Ficheiro.existeU.get(j).getNIF().equals(nifF)){

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DO FUNCIONÁRIO -----" + textoNormal);
                System.out.println("\t\tUSERNAME - " + Ficheiro.existeU.get(j).getUsername());
                System.out.println("\t\tNOME - " +  Ficheiro.existeU.get(j).getPrimeiroNome());
                System.out.println("\t\tAPELIDO - " +  Ficheiro.existeU.get(j).getApelido());
                System.out.println("\t\tNIF - " +  Ficheiro.existeU.get(j).getNIF());
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " +  Ficheiro.existeU.get(j).getnTel());
                System.out.println("\t\tATIVO - " + txt);
            }
        }
    }
}
