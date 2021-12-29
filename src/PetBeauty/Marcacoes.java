package PetBeauty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Marcacoes {
    public static ArrayList<Consulta> existeC = new ArrayList<>();
    public static ArrayList<ExtrasConsulta> existeExtra = new ArrayList<>();
    ArrayList marcacoes = new ArrayList();

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO MARCACOES.DAT
     */
    public void importaMarcacoes() throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("marcacoes.dat");
        Formatter formatter;
        /*
            Caso não exista, o ficheiro
         */
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("0:0:0:0:0:0:0:0:0:0:0\n");
            formatter.flush();
            formatter.close();
        }
        else {
            Scanner scn = new Scanner(System.in);
            try {

                String linha;
                String[] divLinha;
                Scanner sc = new Scanner(new File("marcacoes.dat"));
                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeC.add(new Consulta(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim(), divLinha[5].trim(), divLinha[6].trim(), divLinha[7].trim(), divLinha[8].trim(), divLinha[9].trim(), divLinha[10].trim()));

                } while (sc.hasNextLine());


            } catch (Exception e) {
                System.out.println(textoVermelho + "FICHEIRO COM DADOS DE CONSULTAS NÃO ENCONTRADO!" + textoNormal);
                System.exit(0);
            }
        }
    }

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO EXTRAS.DAT
     */
    public void importaExtra() throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";

        File f = new File("extras.dat");
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
                Scanner sc = new Scanner(new File("extras.dat"));
                do {
                    linha = sc.nextLine();
                    divLinha = linha.split(":");
                    existeExtra.add(new ExtrasConsulta(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim()));

                } while (sc.hasNextLine());


            } catch (Exception e) {
                System.out.println(textoVermelho + "FICHEIRO COM DADOS DE EXTRAS NÃO ENCONTRADO!" + textoNormal);
                System.exit(0);
            }
        }
    }

    /*
        MOSTRA PEDIDOS DE CONSULTAS AINDA SEM RESPOSTA
     */
    public void mostrarPedidos(String nifF) throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0;
        String nomeD = null;
        String especie = null;
        String nomeA = null;

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFFuncionario().equals(nifF) && existeC.get(i).getConfirmada().equals("0")){
                Ficheiro f = new Ficheiro();
                nomeD = f.devolveNome(existeC.get(i).getNIFDono());

                Animais a = new Animais();
                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DO PEDIDO DE CONSULTA -----" + textoNormal);
                System.out.println(textoRoxo + "\t----- CÓDIGO RÁPIDO: " + i + " -----" + textoNormal);
                System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tNIF DONO- " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());

                valor++;
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS PEDIDOS MARCAÇÕES ASSOCIADAS A SI!" + textoNormal);
        }
    }

    /*
        MOSTRA PEDIDOS DE CONSULTAS
     */
    public void mostrarMarcacoesCliente(String nifC) throws FileNotFoundException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, vExtras=0;
        String nomeF = null;
        String especie = null;
        String nomeA = null;
        Ficheiro f = new Ficheiro();
        Animais a = new Animais();
        Empresas e = new Empresas();

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFDono().equals(nifC)){
                if (existeC.get(i).getConfirmada().equals("0")&& existeC.get(i).getRealizada().equals("0") && existeC.get(i).getPaga().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DO PEDIDO DE CONSULTA -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                    System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                    System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                    System.out.println("\t\tESTADO - PENDENTE / A AGUARDAR CONFIRMAÇÃO");

                    valor++;
                }
                if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("0") && existeC.get(i).getPaga().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (APROVADA) -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                    System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                    System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                    System.out.println("\t\tESTADO - APROVADA / AGUARDAR CONSULTA");

                    valor++;
                }
                if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("1") && existeC.get(i).getPaga().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (EM PAGAMENTO) -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                    System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                    System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                    System.out.println("\t\tVALOR A PAGAR - " +  existeC.get(i).getValor() + " €");
                    System.out.println("\t\tESTADO - CONSULTA REALIZADA / A AGUARDAR PAGAMENTO");

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DOS EXTRAS ADICIONADOS -----" + textoNormal);
                    System.out.println("\t       NOME             VALOR     " + textoNormal);

                    for(int x=0; x<existeExtra.size(); x++){
                        if (existeExtra.get(x).getCodConsulta().equals(existeC.get(i).getCodigo())){
                            System.out.println("\t  " + existeExtra.get(x).getDesc() + "            " + existeExtra.get(x).getValor() + "€   ");
                            vExtras++;
                        }
                    }

                    if (vExtras==0){
                        System.out.println(textoRoxo + "\t|      NÃO EXISTEM EXTRAS NESTA CONSULTA      |" + textoNormal);
                    }

                    valor++;

                }
                if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("1") && existeC.get(i).getPaga().equals("1")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (REALIZADA) -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                    System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                    System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                    System.out.println("\t\tVALOR A PAGO - " +  existeC.get(i).getValor() + " €");
                    System.out.println("\t\tESTADO - CONSULTA REALIZADA / PAGA");

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DOS EXTRAS ADICIONADOS -----");
                    System.out.println("\t       NOME             VALOR     " + textoNormal);

                    for(int x=0; x<existeExtra.size(); x++){
                        if (existeExtra.get(x).getCodConsulta().equals(existeC.get(i).getCodigo())){
                        System.out.println("\t  " + existeExtra.get(x).getDesc() + "            " + existeExtra.get(x).getValor() + "€   ");
                            vExtras++;
                        }
                    }

                    if (vExtras==0){
                        System.out.println(textoRoxo + "\t|      NÃO EXISTEM EXTRAS NESTA CONSULTA      |" + textoNormal);
                    }

                    valor++;
                }
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS PEDIDOS MARCAÇÕES ASSOCIADAS A SI!" + valor + textoNormal);
        }
    }

    /*
       PAGAR CONSULTAS
    */
    public void pagarConsulta(String nifC) throws IOException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, valorP=0;
        String nomeF = null;
        String especie = null;
        String nomeA = null;
        Ficheiro f = new Ficheiro();
        Animais a = new Animais();
        Empresas e = new Empresas();
        Scanner sc = new Scanner(System.in);
        String[] divLinha;
        String linha;

        Scanner s = new Scanner(new File("marcacoes.dat"));
        while (s.hasNext()){
            marcacoes.add(s.next());
        }
        s.close();

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFDono().equals(nifC)){
                if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("1") && existeC.get(i).getPaga().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (EM PAGAMENTO) -----" + textoNormal);
                    System.out.println(textoRoxo + "\t----- CÓDIGO RÁPIDO " + i + " -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                    System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                    System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                    System.out.println("\t\tVALOR A PAGAR - " +  existeC.get(i).getValor() + " €");
                    System.out.println("\t\tESTADO - CONSULTA REALIZADA / A AGUARDAR PAGAMENTO");

                    valor++;
                }
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS MARCAÇÕES DE PAGAMENTOS PENDENTES!" + textoNormal);
        }else{

            do {
                System.out.println(System.lineSeparator().repeat(1));
                System.out.print( textoRoxo + "INDIQUE O CÓDIGO RÁPIDO DA CONSULTA QUE DESEJA PAGAR: ");
                valorP = sc.nextInt();
                System.out.print(textoNormal);
            }while(valorP<existeC.size() && valorP>existeC.size());

            for (int i=0; i<existeC.size(); i++){
                if (i==valorP){
                    System.out.println(System.lineSeparator().repeat(1));
                    System.out.println( textoRoxo + "O PAGAMENTO DA CONSULTA CÓDIGO: " + existeC.get(i).getCodigo() + ", com o valor de " + existeC.get(i).getValor() + "€. ESTÁ A SER PROCESSADA" +textoNormal );

                    marcacoes.set(i, existeC.get(i).getCodigo() + ":" + existeC.get(i).getData() + ":" + existeC.get(i).getHora() + ":" +  existeC.get(i).getNIFEmpresa() + ":" +  existeC.get(i).getNIFFuncionario() + ":" +  existeC.get(i).getNIFDono() + ":" +  existeC.get(i).getCodAnimal() + ":" +  existeC.get(i).getConfirmada() + ":" + existeC.get(i).getValor() + ":" + existeC.get(i).getRealizada() + ":" + "1");

                    FileWriter file = new FileWriter("marcacoes.dat", false);
                    Formatter formatter = new Formatter(file);
                    for(int w = 0; w < marcacoes.size(); w ++) {
                        formatter.format((String) marcacoes.get(w) + "\n");
                        formatter.flush();
                    }
                    formatter.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "A CONSULTA FOI PAGA COM SUCESSO!" + textoNormal);

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO DE PAGAMENTO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                }
            }

        }
    }

    /*
       confirmar CONSULTAS
    */
    public void confirmarConsulta(String nifF) throws IOException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, valorP=0, valor2=0;
        String nomeF = null;
        String especie = null;
        String nomeA = null;
        Ficheiro f = new Ficheiro();
        Animais a = new Animais();
        Empresas e = new Empresas();
        Scanner sc = new Scanner(System.in);
        String[] divLinha;
        String linha;

        Scanner s = new Scanner(new File("marcacoes.dat"));
        while (s.hasNext()){
            marcacoes.add(s.next());
        }
        s.close();

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFFuncionario().equals(nifF)){
                if (existeC.get(i).getConfirmada().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFDono());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DO PEDIDO DE CONSULTA -----" + textoNormal);
                    System.out.println(textoRoxo + "\t----- CÓDIGO RÁPIDO " + i + " -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DO D1OME - " + nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());

                    valor++;
                }
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADAS MARCAÇÕES PENDENTES!" + textoNormal);
        }else{

            do {
                System.out.println(System.lineSeparator().repeat(1));
                System.out.print( textoRoxo + "INDIQUE O CÓDIGO RÁPIDO DA CONSULTA QUE DESEJA EFETUAR : ");
                valorP = sc.nextInt();
                System.out.print(textoNormal);
            }while(valorP<existeC.size() && valorP>existeC.size());

            do {
                System.out.println(System.lineSeparator().repeat(1));
                System.out.println( textoRoxo + "\t\t--   MENU    --");
                System.out.println( "\t\t1 - CONFIRMAR PEDIDO DE CONSULTA");
                System.out.println( "\t\t2 - ANULAR PEDIDO DE CONSULTA");
                System.out.println(System.lineSeparator().repeat(2));

                System.out.print( "\tINDIQUE A SUA OPÇÃO:");
                valor2 = sc.nextInt();
                System.out.print(textoNormal);
            }while(valor2<1 && valor2>2);

            for (int i=0; i<existeC.size(); i++){
                if (i==valorP){
                    if (valor2==1){
                        System.out.println(System.lineSeparator().repeat(1));

                        System.out.println( textoRoxo + "A CONSULTA CÓDIGO: " + existeC.get(i).getCodigo() + ", ESTÁ CONFIRMADA" +textoNormal );

                        marcacoes.set(i, existeC.get(i).getCodigo() + ":" + existeC.get(i).getData() + ":" + existeC.get(i).getHora() + ":" + existeC.get(i).getNIFEmpresa() + ":" +  existeC.get(i).getNIFFuncionario() + ":" +  existeC.get(i).getNIFDono() + ":" +  existeC.get(i).getCodAnimal() + ":" +  "1" + ":" + existeC.get(i).getValor() + ":" + existeC.get(i).getRealizada() + ":" + existeC.get(i).getPaga());

                        FileWriter file = new FileWriter("marcacoes.dat", false);
                        Formatter formatter = new Formatter(file);
                        for(int w = 0; w < marcacoes.size(); w ++) {
                            formatter.format((String) marcacoes.get(w) + "\n");
                            formatter.flush();
                        }
                        formatter.close();

                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\t\tA INFORMAÇÃO DE CONFIRMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    }else{
                        System.out.println(System.lineSeparator().repeat(1));

                        System.out.println( textoRoxo + "A CONSULTA CÓDIGO: " + existeC.get(i).getCodigo() + ", ESTÁ ANULADA" +textoNormal );

                        marcacoes.set(i, existeC.get(i).getCodigo() + ":" + existeC.get(i).getData() + ":" +  existeC.get(i).getNIFEmpresa() + ":" +  existeC.get(i).getNIFFuncionario() + ":" +  existeC.get(i).getNIFDono() + ":" +  existeC.get(i).getCodAnimal() + ":" +  "2" + ":" + existeC.get(i).getValor() + ":" + existeC.get(i).getRealizada() + ":" + existeC.get(i).getPaga());

                        FileWriter file = new FileWriter("marcacoes.dat", false);
                        Formatter formatter = new Formatter(file);
                        for(int w = 0; w < marcacoes.size(); w ++) {
                            formatter.format((String) marcacoes.get(w) + "\n");
                            formatter.flush();
                        }
                        formatter.close();

                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\t\tA INFORMAÇÃO DE CONFIRMAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);

                    }
                }
            }
        }
    }

    /*
        REGISTAR CONSULTA
     */
    public void registarConsulta(String nif) throws IOException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        String nifEmpresa = null;
        String nifFuncionario = null;
        String codigoAnimal = null;
        String dataAux;
        int horaAux;
        String hora = null;
        int valor = 0, animal=0, func=0, existeEmpresas;
        Scanner sc = new Scanner(System.in);

        Empresas e = new Empresas();
        Animais a = new Animais();
        Ficheiro f = new Ficheiro();

        System.out.print( textoRoxo + "INDIQUE A DATA EM QUE DESEJA MARCAR CONSULTA NO FORMATO DD/MM/AAAA: " + textoNormal);
        dataAux = sc.nextLine();

        do {
            System.out.print( textoRoxo + "INDIQUE A HORA EM QUE DESEJA MARCAR CONSULTA NO FORMATO HH: " + textoNormal);
            horaAux = sc.nextInt();
        }while (horaAux>24 && horaAux<0);

        hora = String.valueOf(horaAux);

        if (e.existeE.size()==0 || a.existeA.size()==0 || e.existeF.size()==0){
            System.out.println(textoVermelho + "\t\t NÃO EXISTEM EMPRESAS OU ANIMAIS OU FUNCIONÁRIOS REGISTADOS" + textoNormal);

        }else{
            //OBTEM O I PARA A EMPRESA
            existeEmpresas = e.listarTodasEmpresasNBloqueadas();

            if (existeEmpresas==0) {        //existem empresas
                do {
                    System.out.println(System.lineSeparator().repeat(1));
                    System.out.print(textoRoxo + "INDIQUE O CÓDIGO DA EMPRESA DA EMPRESA QUE DESEJA MARCAR CONSULTA: ");
                    valor = sc.nextInt();
                    System.out.print(textoNormal);
                } while (valor < e.existeE.size() && valor > e.existeE.size());

                //OBTEM O I PARA O ANIMAL
                a.listarAnimais(nif);

                do {
                    System.out.println(System.lineSeparator().repeat(1));
                    System.out.print(textoRoxo + "INDIQUE O CÓDIGO DO ANIMAL QUE DESEJA MARCAR CONSULTA: ");
                    animal = sc.nextInt();
                    System.out.print(textoNormal);
                } while (animal < a.existeA.size() && animal > a.existeA.size());

                //OBTEM O I PARA O FUNCIONÁRIO
                e.lerFuncEmp(valor);

                do {
                    System.out.println(System.lineSeparator().repeat(1));
                    System.out.print(textoRoxo + "INDIQUE O CÓDIGO DO FUNCIONÁRIO QUE DESEJA MARCAR CONSULTA: ");
                    func = sc.nextInt();
                    System.out.print(textoNormal);
                } while (func < f.existeU.size() && func > f.existeU.size());

                //OBTEM O NIF DA EMPRESA
                for (int i = 0; i < e.existeE.size(); i++) {
                    if (i == valor) {
                        nifEmpresa = e.existeE.get(i).getNifEmpresa();
                        break;
                    }
                }

                //OBTEM O NIF DO FUNCIONARIO
                for (int j = 0; j < f.existeU.size(); j++) {
                    if (j == func) {
                        nifFuncionario = f.existeU.get(j).getNIF();
                        break;
                    }
                }

                //OBTEM O CÓDIGO DO ANIMAL
                for (int i = 0; i < a.existeA.size(); i++) {
                    if (i == animal) {
                        codigoAnimal = a.existeA.get(i).getCodAnimal();
                    }
                }

                //GERA CODIGO DE CONSULTA
                String codConsulta = UUID.randomUUID().toString();

                //REGISTA CONSULTA
                FileWriter associar = new FileWriter("marcacoes.dat", true);
                Formatter formatter;

                try {
                    formatter = new Formatter(associar);
                    formatter.format(codConsulta + ":");
                    formatter.format(dataAux + ":");
                    formatter.format(hora + ":");
                    formatter.format(nifEmpresa + ":");
                    formatter.format(nifFuncionario + ":");
                    formatter.format(nif + ":");
                    formatter.format(codigoAnimal + ":");
                    formatter.format("0" + ":");
                    formatter.format("0" + ":");
                    formatter.format("0" + ":");
                    formatter.format("0" + "\n");
                    formatter.flush();
                    formatter.close();

                    if (formatter != null) {

                        existeC.add(new Consulta(codConsulta, dataAux, hora, nifEmpresa, nifFuncionario, nif, codigoAnimal, "0", "0", "0", "0"));
                        System.out.println(System.lineSeparator().repeat(2));
                        System.out.println(textoVerde + "\n\tA CONSULTA FOI REGISTADO COM O CÓDIGO: " + codConsulta + textoNormal);
                        System.out.println(System.lineSeparator().repeat(1));

                    } else {
                        System.out.println(textoVermelho + "ERRO: CONSULTA NÃO REGISTADA" + textoNormal);
                    }

                } catch (Exception y) {
                    System.out.println(textoVermelho + "FICHEIRO NÃO ENCONTRADO!" + textoNormal);
                    System.exit(0);
                }
            }else{
                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoVermelho + "\n\tNÃO EXISTEM EMPRESAS REGISTADAS " + textoNormal);

            }
        }
    }

    /*
        MOSTRA CONSULTAS AGENDADAS
     */
    public void mostrarConsultasAgendadas(String nifF) throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0;
        String nomeD = null;
        String especie = null;
        String nomeA = null;

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFFuncionario().equals(nifF) && existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("0")){
                Ficheiro f = new Ficheiro();
                nomeD = f.devolveNome(existeC.get(i).getNIFDono());

                Animais a = new Animais();
                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DE CONSULTA -----" + textoNormal);
                System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tNIF DONO- " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());

                valor++;
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS MARCAÇÕES PARA SI!" + textoNormal);
        }
    }

    /*
       MARCAR COMO REALIZADA
    */
    public void marcarRealizadaConsulta(String nifF) throws IOException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, valorP=0, valor2=0;
        String nomeF = null;
        String especie = null;
        String nomeA = null;
        Float valorC;
        Ficheiro f = new Ficheiro();
        Animais a = new Animais();
        Empresas e = new Empresas();
        Scanner sc = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        String[] divLinha;
        String linha;

        Scanner s = new Scanner(new File("marcacoes.dat"));
        while (s.hasNext()){
            marcacoes.add(s.next());
        }
        s.close();

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFFuncionario().equals(nifF)){
                if (existeC.get(i).getConfirmada().equals("1")&&existeC.get(i).getRealizada().equals("0")){
                    nomeF = f.devolveNome(existeC.get(i).getNIFDono());

                    especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                    nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoRoxo + "\t----- DADOS DO PEDIDO DE CONSULTA -----" + textoNormal);
                    System.out.println(textoRoxo + "\t----- CÓDIGO RÁPIDO " + i + " -----" + textoNormal);
                    System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                    System.out.println("\t\tDATA - " + existeC.get(i).getData());
                    System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                    System.out.println("\t\tNOME DO NOME - " + nomeF);
                    System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                    System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                    System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());

                    valor++;
                }
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADAS MARCAÇÕES PENDENTES!" + textoNormal);
        }else{

            do {
                System.out.println(System.lineSeparator().repeat(1));
                System.out.print( textoRoxo + "INDIQUE O CÓDIGO RÁPIDO DA CONSULTA QUE DESEJA MARCAR COMO REALIZADA: ");
                valorP = sc.nextInt();
                System.out.print(textoNormal);
            }while(valorP<existeC.size() && valorP>existeC.size());

            System.out.println(System.lineSeparator().repeat(2));
            System.out.println( textoRoxo + "INDIQUE O VALOR BASE DA CONSULTA" +textoNormal );
            valorC = sc.nextFloat();

            String resp=null, desc, codConsulta = null;
            String valorE;
            do {
                System.out.print(textoRoxo + "\n\tDESEJA ADICIONAR EXTRAS [S/N]: " + textoNormal);
                resp = scn.nextLine();

                if (resp.equalsIgnoreCase("S")) {
                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println( textoRoxo + "INDIQUE O NOME DO EXTRA A ADICIONAR NA CONSULTA: " +textoNormal );
                    desc = scn.nextLine();

                    System.out.println( textoRoxo + "INDIQUE O VALOR DO EXTRA A ADICIONAR NA CONSULTA: " +textoNormal );
                    valorE = scn.nextLine();

                    for (int i=0; i<existeC.size(); i++){
                        if (i==valorP){
                            codConsulta = existeC.get(i).getCodigo();
                        }
                    }
                    //ADICIONA DADOS AO ARRAY DOS EXTRAS
                    existeExtra.add(new ExtrasConsulta(codConsulta, desc, valorE));

                    //GUARDAR OS REGISTOS NO FICHEIROS
                    FileWriter registar = new FileWriter("extras.dat", true);
                    Formatter formatter;
                    formatter = new Formatter(registar);
                    formatter.format(codConsulta + ":");
                    formatter.format(desc + ":");
                    formatter.format(valorE + "\n");
                    formatter.flush();
                    formatter.close();

                    if(formatter != null){
                        System.out.println("\n\tFOI ADICIONADO UM EXTRA A CONSULTA");
                    }else{
                        System.out.println("ERRO: EXTRA NÃO FOI ADICIONADO!");
                    }
                }
            }while(resp.equalsIgnoreCase("S"));


            for (int i=0; i<existeC.size(); i++){
                if (i==valorP){
                    String cc = existeC.get(i).getCodigo();
                    float totalE=0, totalEAux=0, totalFatura=0;
                    String totalS=null;

                    for (int x=0; x<existeExtra.size(); x++){
                        if (existeExtra.get(x).getCodConsulta().equals(cc)){
                            totalEAux= Float.valueOf(existeExtra.get(x).getValor());
                            totalE=totalE+totalEAux;
                        }
                    }

                    totalFatura=totalE+valorC;

                    totalS = String.valueOf(totalFatura);

                    System.out.println(System.lineSeparator().repeat(1));

                    System.out.println( textoRoxo + "A CONSULTA CÓDIGO: " + existeC.get(i).getCodigo() + ", ESTÁ EM PROCESSAMENTO" +textoNormal );

                    marcacoes.set(i, existeC.get(i).getCodigo() + ":" + existeC.get(i).getData() + ":" + existeC.get(i).getHora() + ":" + existeC.get(i).getNIFEmpresa() + ":" +  existeC.get(i).getNIFFuncionario() + ":" +  existeC.get(i).getNIFDono() + ":" +  existeC.get(i).getCodAnimal() + ":" +  existeC.get(i).getConfirmada() + ":" + totalS + ":" + "1" + ":" + existeC.get(i).getPaga());

                    FileWriter file = new FileWriter("marcacoes.dat", false);
                    Formatter formatter1 = new Formatter(file);
                    for(int w = 0; w < marcacoes.size(); w ++) {
                        formatter1.format((String) marcacoes.get(w) + "\n");
                        formatter1.flush();
                    }
                    formatter1.close();

                    System.out.println(System.lineSeparator().repeat(2));
                    System.out.println(textoVerde + "\t\tA INFORMAÇÃO DE REALIZAÇÃO SÓ FICA VÁLIDA APÓS RENICIAR" + textoNormal);
                }
            }
        }
    }

    /*
        MOSTRA TODAS AS MARCAÇÕES
     */
    public void mostrarTodasMarcacoes() throws FileNotFoundException, ParseException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, vExtras=0;
        String nomeF = null;
        String especie = null;
        String nomeA = null;
        String nomeD = null;
        Ficheiro f = new Ficheiro();
        Animais a = new Animais();
        Empresas e = new Empresas();

        for (int i=0; i<existeC.size(); i++){
            if (existeC.get(i).getConfirmada().equals("0")&& existeC.get(i).getRealizada().equals("0") && existeC.get(i).getPaga().equals("0")){
                nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());
                nomeD = f.devolveNome(existeC.get(i).getNIFDono());

                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DO PEDIDO DE CONSULTA -----" + textoNormal);
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tNIF DO DONO - " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                System.out.println("\t\tESTADO - PENDENTE / A AGUARDAR CONFIRMAÇÃO");

                valor++;
            }
            if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("0") && existeC.get(i).getPaga().equals("0")){
                nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (APROVADA) -----" + textoNormal);
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tNIF DO DONO - " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                System.out.println("\t\tESTADO - APROVADA / AGUARDAR CONSULTA");

                valor++;
            }
            if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("1") && existeC.get(i).getPaga().equals("0")){
                nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (EM PAGAMENTO) -----" + textoNormal);
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tNIF DO DONO - " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                System.out.println("\t\tVALOR A PAGAR - " +  existeC.get(i).getValor() + " €");
                System.out.println("\t\tESTADO - CONSULTA REALIZADA / A AGUARDAR PAGAMENTO");

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DOS EXTRAS ADICIONADOS -----" + textoNormal);

                for(int x=0; x<existeExtra.size(); x++){
                    if (existeExtra.get(x).getCodConsulta().equals(existeC.get(i).getCodigo())){
                        System.out.println("\tNOME - " + existeExtra.get(x).getDesc());
                        System.out.println("\tVALOR - " + existeExtra.get(x).getValor() + "€");
                        vExtras++;
                    }
                }

                if (vExtras==0){
                    System.out.println(textoRoxo + "\t----- NÃO EXISTEM EXTRAS NESTA CONSULTA -----" + textoNormal);
                }
            }
            if (existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("1") && existeC.get(i).getPaga().equals("1")){
                nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DA CONSULTA (REALIZADA) -----" + textoNormal);
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DA EMPRESA - " +  e.devolverNomeEmp(existeC.get(i).getNIFEmpresa()));
                System.out.println("\t\tNIF DA EMPRESA - " +  existeC.get(i).getNIFEmpresa());
                System.out.println("\t\tNOME DO FUNCIONÁRIO - " +  nomeF);
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tNIF DO DONO - " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());
                System.out.println("\t\tVALOR A PAGO - " +  existeC.get(i).getValor() + " €");
                System.out.println("\t\tESTADO - CONSULTA REALIZADA / PAGA");

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DOS EXTRAS ADICIONADOS -----");
                System.out.println("\t       NOME             VALOR     " + textoNormal);

                for(int x=0; x<existeExtra.size(); x++){
                    if (existeExtra.get(x).getCodConsulta().equals(existeC.get(i).getCodigo())){
                        System.out.println("\t  " + existeExtra.get(x).getDesc() + "            " + existeExtra.get(x).getValor() + "€   ");
                        vExtras++;
                    }
                }

                if (vExtras==0){
                    System.out.println(textoRoxo + "\t|      NÃO EXISTEM EXTRAS NESTA CONSULTA      |" + textoNormal);
                }

                valor++;
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS MARCAÇÕES REGISTADOS!" + textoNormal);
        }
    }

    /*
        MOSTRA CONSULTAS AGENDADAS
     */
    public void mostrarConsultasAgendadasE(String nifD) throws FileNotFoundException {
        String textoVermelho = "\033[31m";
        String textoRoxo = "\033[95m";
        String textoVerde = "\033[32m";
        String textoNormal = "\033[0m";
        int valor = 0, codE;
        String nifE=null, nomeD, especie, nomeA, nomeF;

        Scanner sc = new Scanner(System.in);

        Empresas e = new Empresas();
        e.listarEmpresas(nifD);
        do {
            System.out.print("INSIRA O CÓDIGO DA EMPRESA QUE DESEJA CONSULTAR: ");
            codE = sc.nextInt();
        }while(Empresas.existeE.size()>codE && Empresas.existeE.size()<codE);

        for (int x=0; x<Empresas.existeE.size(); x++){
            if (x==codE){
                nifE=Empresas.existeE.get(x).getNifEmpresa();
            }
        }

        for (int i=0; i<existeC.size(); i++){
            if(existeC.get(i).getNIFEmpresa().equals(nifE) && existeC.get(i).getConfirmada().equals("1") && existeC.get(i).getRealizada().equals("0")){
                Ficheiro f = new Ficheiro();
                nomeD = f.devolveNome(existeC.get(i).getNIFDono());
                nomeF = f.devolveNome(existeC.get(i).getNIFFuncionario());

                Animais a = new Animais();
                especie = a.devolveEspecie(existeC.get(i).getCodAnimal());
                nomeA = a.devolveNome(existeC.get(i).getCodAnimal());

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- DADOS DE CONSULTA -----" + textoNormal);
                System.out.println("\t\tCÓDIGO - " + existeC.get(i).getCodigo());
                System.out.println("\t\tFUNCIONÁRIO - " + nomeF);
                System.out.println("\t\tDATA - " + existeC.get(i).getData());
                System.out.println("\t\tHORA - " + existeC.get(i).getHora());
                System.out.println("\t\tNOME DO DONO - " +  nomeD);
                System.out.println("\t\tESPECIE DO ANIMAL - " + especie);
                System.out.println("\t\tNOME DO ANIMAL - " +  nomeA);
                System.out.println("\t\tNIF DONO- " +  existeC.get(i).getNIFDono());
                System.out.println("\t\tCÓDIGO ANIMAL - " +  existeC.get(i).getCodAnimal());

                valor++;
            }
        }

        if (valor==0){
            System.out.println(System.lineSeparator().repeat(2));
            System.out.println(textoVermelho + "NÃO FORAM ENCONTRADOS MARCAÇÕES!" + textoNormal);
        }
    }

}
