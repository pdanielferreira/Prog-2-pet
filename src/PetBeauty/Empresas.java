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
    ArrayList empresas = new ArrayList();
    ArrayList funcionarios = new ArrayList();
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
            formatter.format("0:0:0:0:0:0:0:1\n");
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
                    existeE.add(new Empresa(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim(), divLinha[7].trim()));

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
                existeE.add(new Empresa(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim(), divLinha[7].trim()));

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
        ANÁLISA SE O FUNCIONÁRIO ESTÁ AUTORIZADO A ENTRAR NO SISTEMA
     */
    public int analisaEntrada(String nif){
        for (int i=0; i<existeF.size(); i++){
            if (existeF.get(i).getNifFuncionario().equals(nif) && existeF.get(i).getAtivo().equals("0")){
                //RETORNA QUE ESTA ATIVO
                return 0;
            }
        }
        //RETORNA QUE ESTA BLOQUEADO
        return 1;
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
        String nif, nome, morada, localidade, especialidade, nTel, escolha;
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
            System.out.print("ESPECIALIDADE(S): ");
            especialidade = sc.nextLine();

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
            formatter.format(especialidade + ":");
            formatter.format(nTel + ":");
            formatter.format(nifD + ":");
            formatter.format("0" + "\n");
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
                System.out.println("\t\tESPECILIDADE - " + especialidade);
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

                String txt;
                if (existeE.get(i).getAtivo().equals("0")){
                    txt="EMPRESA ATIVA";
                }else{
                    txt="EMPRESA BLOQUEADA POR OREDEM DE ADMINISTRADOR";
                }

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- EMPRESA: " + existeE.get(i).getNifEmpresa() + " -----" + textoNormal);
                System.out.println(textoRoxo + "\t----- CÓDIGO DE EMPRESA: " + i + " -----" + textoNormal);
                System.out.println("\t\tNOME - " + existeE.get(i).getNome());
                System.out.println("\t\tMORADA - " + existeE.get(i).getMorada());
                System.out.println("\t\tLOCALIDADE - " + existeE.get(i).getLocalidade());
                System.out.println("\t\tESPECIALIDADE - " + existeE.get(i).getEspecialidade());
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + existeE.get(i).getnTel());
                System.out.println("\t\tNIF DO DONO - " + existeE.get(i).getNifDono());
                System.out.println("\t\tESTADO - " + txt);
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
            formatter.format("0:0:1\n");
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
            formatter.format(0 + "\n");
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
                break;
            }
        }

        for (int i=0; i<existeF.size(); i++){
            if (existeF.get(i).getNifEmpresa().equals(nifEmpresa)){
                nifF=existeF.get(i).getNifFuncionario();
                String despedido = existeF.get(i).getAtivo();

                if (despedido.equals("0")){
                    txt = " A TRABALHAR ";
                }else{
                    txt = " DESPEDIDO ";
                }

                Ficheiro f = new Ficheiro();
                f.lerNIF(nifF);
                System.out.println("\t\tESTADO - " +  txt);
            }
        }
    }

    /*
        LER TODOS OS FUNCIONÁRIOS
     */
    public void lerTodosFuncEmp() throws IOException{
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        String nifEmpresa = null;
        String txt = null;
        String nifF = null;
        String nifE=null;

        for (int i=0; i<existeF.size(); i++){
            nifF=existeF.get(i).getNifFuncionario();
            String despedido = existeF.get(i).getAtivo();


            if (despedido.equals("0")){
                txt = " A TRABALHAR ";
            }else{
                txt = " DESPEDIDO ";
            }

            Ficheiro f = new Ficheiro();
            f.lerNIF(nifF);
        }
    }

    /*
        LISTA TODAS AS EMPRESAS
     */
    public void listarTodasEmpresas(){
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        int count=0;

        System.out.println(textoRoxo + "\t\t TODAS AS EMPRESAS:" + textoNormal);

        for(int i=0; i<existeE.size(); i++) {
            String txt;
            if (existeE.get(i).getAtivo().equals("0")){
                txt="EMPRESA ATIVA";
            }else{
                txt="EMPRESA BLOQUEADA POR ORDEM DE ADMINISTRADOR";
            }

            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoRoxo + "\t----- EMPRESA: " + existeE.get(i).getNifEmpresa() + " -----" + textoNormal);
            System.out.println(textoRoxo + "\t----- CÓDIGO DE EMPRESA: " + i + " -----" + textoNormal);
            System.out.println("\t\tNOME - " + existeE.get(i).getNome());
            System.out.println("\t\tMORADA - " + existeE.get(i).getMorada());
            System.out.println("\t\tLOCALIDADE - " + existeE.get(i).getLocalidade());
            System.out.println("\t\tESPECIALIDADE - " + existeE.get(i).getEspecialidade());
            System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + existeE.get(i).getnTel());
            System.out.println("\t\tESTADO - " + txt);
            count++;
        }
        if(count==0){
            System.out.println(textoVermelho + "\t\t NÃO EXISTEM EMPRESAS REGISTADOS" + textoNormal);
        }

    }

    /*
        LISTA TODAS AS EMPRESAS NÃO BLOQUEADAS
     */
    public int listarTodasEmpresasNBloqueadas(){
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        int count=0;

        System.out.println(textoRoxo + "\t\t TODAS AS EMPRESAS:" + textoNormal);

        for(int i=0; i<existeE.size(); i++) {
            if (existeE.get(i).getAtivo().equals("0")) {
                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- EMPRESA: " + existeE.get(i).getNifEmpresa() + " -----" + textoNormal);
                System.out.println(textoRoxo + "\t----- CÓDIGO DE EMPRESA: " + i + " -----" + textoNormal);
                System.out.println("\t\tNOME - " + existeE.get(i).getNome());
                System.out.println("\t\tMORADA - " + existeE.get(i).getMorada());
                System.out.println("\t\tLOCALIDADE - " + existeE.get(i).getLocalidade());
                System.out.println("\t\tESPECIALIDADE - " + existeE.get(i).getEspecialidade());
                System.out.println("\t\tNÚMERO DE TELEMÓVEL - " + existeE.get(i).getnTel());
                count++;
            }
        }
        if(count==0){
            return 1;
        }else
            return 0;

    }


    /*
        permite bloquear empresa
     */
    public void bloquearEmpresas(int op) throws IOException {
        Scanner sc = new Scanner(System.in);
        int codER, count=0;
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        String txt = null;
        String escolha;

        Scanner s = new Scanner(new File("empresas.dat"));
        while (s.hasNext()){
            empresas.add(s.next());
        }
        s.close();

        System.out.print(textoRoxo + "\n\tDESEJA ALTERAR O ESTADO DE UMA EMPRESA [S/N]: " + textoNormal);
        String resp = sc.nextLine();

        if (resp.toUpperCase().equals("S")) {
            listarTodasEmpresas();

            do {
                System.out.print("INSIRA O CÓDIGO DA EMPRESA QUE DESEJA ALTERAR: ");
                codER = sc.nextInt();
            }while(empresas.size()>codER && empresas.size()<codER);

            for(int i=0; i<empresas.size(); i++) {
                if(i==codER){
                    if(existeE.get(i).getAtivo().equals(op)){
                        if (op==0){
                            System.out.println(textoVermelho + "\t\tA EMPRESA QUE DESEJA BLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }else{
                            System.out.println(textoVermelho + "\t\tA EMPRESA QUE DESEJA DESBLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }
                    }else{

                        if (op==0){
                            escolha="1";
                        }else{
                            escolha="0";
                        }
                        empresas.set(i, existeE.get(i).getNifEmpresa() + ":" + existeE.get(i).getNome() + ":" + existeE.get(i).getMorada() + ":" + existeE.get(i).getLocalidade() + ":" + existeE.get(i).getEspecialidade() + ":" + existeE.get(i).getnTel() + ":" + existeE.get(i).getNifDono() + ":" + escolha);

                        FileWriter file = new FileWriter("empresas.dat", false);
                        Formatter formatter = new Formatter(file);
                        for(int w = 0; w < empresas.size(); w ++) {
                            formatter.format((String) empresas.get(w) + "\n");
                            formatter.flush();
                        }
                        formatter.close();

                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    }
                    count++;
                }
            }
            if(count==0){
                System.out.println(textoVermelho + "\t\t NÃO EXISTEM EMPRESAS REGISTADOS COM O CÓDIGO QUE INDICOU!" + textoNormal);
            }
        }
    }

    /*
        PERMITE BLOQUEAR/DESBLOQUEAR FUNCIONARIOS
     */
    public void bloquearFunc(int op) throws IOException {
        Scanner sc = new Scanner(System.in);
        int codER, count=0;
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        String txt = null;
        String escolha;
        String nifF;

        Scanner s = new Scanner(new File("funcionarios.dat"));
        while (s.hasNext()){
            funcionarios.add(s.next());
        }
        s.close();

        System.out.print(textoRoxo + "\n\tDESEJA ALTERAR O ESTADO DE UM FUNIONÁRIO [S/N]: " + textoNormal);
        String resp = sc.nextLine();

        if (resp.toUpperCase().equals("S")) {
            lerTodosFuncEmp();

            do {
                System.out.print("INSIRA O CÓDIGO DO FUNCIONÁRIO QUE DESEJA ALTERAR: ");
                codER = sc.nextInt();
            }while(funcionarios.size()>codER && funcionarios.size()<codER);

            Ficheiro f = new Ficheiro();
            nifF = f.devolveNIF(codER);

            for(int i=0; i<existeF.size(); i++) {
                if(existeF.get(i).getNifFuncionario().equals(nifF)){
                    if(existeF.get(i).getAtivo().equals(op)){
                        if (op==0){
                            System.out.println(textoVermelho + "\t\tO FUNCIONÁRIO QUE DESEJA BLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }else{
                            System.out.println(textoVermelho + "\t\tO FUNCIONÁRIO QUE DESEJA DESBLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }
                    }else{

                        if (op==0){
                            escolha="1";
                        }else{
                            escolha="0";
                        }
                        funcionarios.set(i, nifF + ":" + existeF.get(i).getNifEmpresa() + ":" + escolha);

                        FileWriter file = new FileWriter("funcionarios.dat", false);
                        Formatter formatter = new Formatter(file);
                        for(int w = 0; w < funcionarios.size(); w ++) {
                            formatter.format((String) funcionarios.get(w) + "\n");
                            formatter.flush();
                        }
                        formatter.close();

                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    }
                    count++;
                }
            }
            if(count==0){
                System.out.println(textoVermelho + "\t\t NÃO EXISTEM FUNCIONÁRIOS REGISTADOS COM O CÓDIGO QUE INDICOU!" + textoNormal);
            }
        }
    }

    /*
        PERMITE DONOS DE EMPRESA BLOQUEAR/DESBLOQUEAR FUNCIONARIOS
     */
    public void bloquearFuncDonoE(int op, String nifD) throws IOException {
        Scanner sc = new Scanner(System.in);
        int codER, count=0, codE;
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        String txt = null;
        String escolha;
        String nifF;

        Scanner s = new Scanner(new File("funcionarios.dat"));
        while (s.hasNext()){
            funcionarios.add(s.next());
        }
        s.close();

        System.out.print(textoRoxo + "\n\tDESEJA ALTERAR O ESTADO DE UM FUNIONÁRIO [S/N]: " + textoNormal);
        String resp = sc.nextLine();

        if (resp.toUpperCase().equals("S")) {
            listarEmpresas(nifD);
            do {
                System.out.print("INSIRA O CÓDIGO DA EMPRESA EM QUE O FUNCIONÁRIO ESTA ASSOCIADO: ");
                codE = sc.nextInt();
            }while(existeE.size()>codE && existeE.size()<codE);

            lerFuncEmp(codE);

            do {
                System.out.print("INSIRA O CÓDIGO DO FUNCIONÁRIO QUE DESEJA ALTERAR: ");
                codER = sc.nextInt();
            }while(funcionarios.size()>codER && funcionarios.size()<codER);

            Ficheiro f = new Ficheiro();
            nifF = f.devolveNIF(codER);

            for(int i=0; i<existeF.size(); i++) {
                if(existeF.get(i).getNifFuncionario().equals(nifF)){
                    if(existeF.get(i).getAtivo().equals(op)){
                        if (op==0){
                            System.out.println(textoVermelho + "\t\tO FUNCIONÁRIO QUE DESEJA BLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }else{
                            System.out.println(textoVermelho + "\t\tO FUNCIONÁRIO QUE DESEJA DESBLOQUEAR, JÁ SE ENCONTRA NESSE ESTADO. \n\t\tNENHUMA ALTERAÇÃO EFETUADA" + textoNormal);
                            break;
                        }
                    }else{

                        if (op==0){
                            escolha="1";
                        }else{
                            escolha="0";
                        }
                        funcionarios.set(i, nifF + ":" + existeF.get(i).getNifEmpresa() + ":" + escolha);

                        FileWriter file = new FileWriter("funcionarios.dat", false);
                        Formatter formatter = new Formatter(file);
                        for(int w = 0; w < funcionarios.size(); w ++) {
                            formatter.format((String) funcionarios.get(w) + "\n");
                            formatter.flush();
                        }
                        formatter.close();

                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    }
                    count++;
                }
            }
            if(count==0){
                System.out.println(textoVermelho + "\t\t NÃO EXISTEM FUNCIONÁRIOS REGISTADOS COM O CÓDIGO QUE INDICOU!" + textoNormal);
            }
        }
    }

    /*
        DEVOLVER NOME DA EMPRESA PELO NIF DA EMPRESA
     */
    public String devolverNomeEmp(String nif){
        for (int i=0; i<existeE.size(); i++){
            if (existeE.get(i).getNifEmpresa().equals(nif)){
                return existeE.get(i).getNome();
            }
        }
        return null;
    }

    /*
        PERMITE EDITAR UMA EMPRESA
     */
    public void editarEmpresa(String nifD) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        int codER, count=0, codE;
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        String nome;
        int esc=0;


        for (int x=0; x<existeE.size(); x++){
            empresas.add(existeE.get(x));
        }

        System.out.print(textoRoxo + "\n\tDESEJA ALTERAR EDITAR UMA DAS SUAS EMPRESAS [S/N]: " + textoNormal);
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("S")) {
            listarEmpresas(nifD);
            do {
                System.out.print("INSIRA O CÓDIGO DA EMPRESA A SER EDITADA: ");
                codE = sc.nextInt();
            }while(existeE.size()>codE && existeE.size()<codE);

            System.out.println("\n\tQUAL O CAMPO A ALTERAR: ");
            System.out.println("\t\t1 - NOME");
            System.out.println("\t\t2 - MORADA");
            System.out.println("\t\t3 - LOCALIDADE");
            System.out.println("\t\t4 - NÚMERO DE TELEMÓVEL");
            System.out.println("\t\t5 - ESPECIADLIDADE");

            do {
                System.out.print("\tSELECIONE O CAMPO: ");
                esc = sc.nextInt();
            } while (esc < 0 || esc > 5);

            switch (esc) {
                //NOME EMPRESA
                case 1:{
                    System.out.print(textoRoxo + codE +"\tNOVO NOME: " + textoNormal);
                    nome = scn.nextLine();

                    for(int i=0; i<existeE.size(); i++) {
                        if (i==codE){
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();

                            empresas.set(i, nifE + ":" + nome + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }else{
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();
                            String nomeEmpresa  = existeE.get(i).getNome();

                            empresas.set(i, nifE + ":" + nomeEmpresa + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }
                    }

                    FileWriter file = new FileWriter("empresas.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < empresas.size(); w ++) {
                        formatter.format((String) empresas.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    break;
                }

                //MORADA
                case 2:{
                    System.out.print(textoRoxo + "\tNOVA MORADA: " + textoNormal);
                    nome = scn.nextLine();

                    for(int i=0; i<existeE.size(); i++) {
                        if (i==codE){

                            String nifE = existeE.get(i).getNifEmpresa();
                            String nomeE = existeE.get(i).getNome();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();

                            empresas.set(i, nifE + ":" + nomeE + ":" + nome + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }else{
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();
                            String nomeEmpresa  = existeE.get(i).getNome();

                            empresas.set(i, nifE + ":" + nomeEmpresa + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }
                    }

                    FileWriter file = new FileWriter("empresas.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < empresas.size(); w ++) {
                        formatter.format((String) empresas.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);


                    break;
                }


                //LOCALIDADE
                case 3:{
                    System.out.print(textoRoxo + "\tNOVA LOCALIDADE: " + textoNormal);
                    nome = scn.nextLine();

                    for(int i=0; i<existeE.size(); i++) {
                        if (i==codE){
                            String nifE = existeE.get(i).getNifEmpresa();
                            String nomeE = existeE.get(i).getNome();
                            String morada  = existeE.get(i).getMorada();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();

                            empresas.set(i, nifE + ":" + nomeE + ":" + morada + ":" + nome + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }else{
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();
                            String nomeEmpresa  = existeE.get(i).getNome();

                            empresas.set(i, nifE + ":" + nomeEmpresa + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }
                    }

                    FileWriter file = new FileWriter("empresas.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < empresas.size(); w ++) {
                        formatter.format((String) empresas.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);


                    break;
                }


                //NÚMERO DE TELEMÓVEL
                case 4:{
                    int erro2;
                    do {
                        System.out.print("NÚMERO DE TELEMÓVEL: ");
                        nome = scn.nextLine();

                        if (nome.length()>9 || nome.length()<9){
                            System.out.println(textoVermelho + "\t\tNÚMERO DE TELEMÓVEL INVÁLIDO!" + textoNormal);
                            erro2=1;
                        }else{
                            erro2=0;
                        }

                    }while(erro2==1);
                    System.out.println(textoVerde + "\t\tNÚMERO DE TELEMÓVEL VÁLIDO!" + textoNormal);

                    for(int i=0; i<existeE.size(); i++) {
                        if (i==codE){
                            String nifE = existeE.get(i).getNifEmpresa();
                            String nomeE = existeE.get(i).getNome();
                            String morada  = existeE.get(i).getMorada();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String localidade  = existeE.get(i).getLocalidade();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();

                            empresas.set(i, nifE + ":" + nomeE + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nome + ":" + nifDono + ":" + ativo);

                        }else{
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();
                            String nomeEmpresa  = existeE.get(i).getNome();

                            empresas.set(i, nifE + ":" + nomeEmpresa + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }
                    }

                    FileWriter file = new FileWriter("empresas.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < empresas.size(); w ++) {
                        formatter.format((String) empresas.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    break;
                }


                //ESPECIALIDADE
                case 5:{
                    System.out.print(textoRoxo + "\tNOVA ESPECIALIDADE: " + textoNormal);
                    nome = scn.nextLine();

                    for(int i=0; i<existeE.size(); i++) {
                        if (i==codE){
                            String nifE = existeE.get(i).getNifEmpresa();
                            String nomeE = existeE.get(i).getNome();
                            String morada  = existeE.get(i).getMorada();
                            String nTele  = existeE.get(i).getnTel();
                            String localidade  = existeE.get(i).getLocalidade();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();

                            empresas.set(i, nifE + ":" + nomeE + ":" + morada + ":" + localidade + ":" + nome + ":" + nTele + ":" + nifDono + ":" + ativo);


                        }else{
                            String nifE = existeE.get(i).getNifEmpresa();
                            String morada  = existeE.get(i).getMorada();
                            String localidade  = existeE.get(i).getLocalidade();
                            String especialidade  = existeE.get(i).getEspecialidade();
                            String nTel  = existeE.get(i).getnTel();
                            String nifDono  = existeE.get(i).getNifDono();
                            String ativo  = existeE.get(i).getAtivo();
                            String nomeEmpresa  = existeE.get(i).getNome();

                            empresas.set(i, nifE + ":" + nomeEmpresa + ":" + morada + ":" + localidade + ":" + especialidade + ":" + nTel + ":" + nifDono + ":" + ativo);

                        }
                    }

                    FileWriter file = new FileWriter("empresas.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < empresas.size(); w ++) {
                        formatter.format((String) empresas.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    break;
                }
            }

        }
    }
}
