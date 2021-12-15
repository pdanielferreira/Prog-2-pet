package PetBeauty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.UUID;

public class Animais {
    public static ArrayList<Animal> existeA = new ArrayList<>();

    /*
        IMPORTA TODA A INFORMAÇÃO DO FICHEIRO ANIMAIS.DAT
     */
    public void importaAnimais() throws FileNotFoundException {
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
            formatter.format("0:0:0:0\n");
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
        LISTA TODOS OS ANIMAIS DO MESMO DONO
     */
    public void listarAnimais(String nifD){
        String textoVermelho = "\033[31m";
        String textoVerde = "\033[32m";
        String textoAmarelo = "\033[93m";
        String textoRoxo = "\033[95m";
        String textoNormal = "\033[0m";
        int count=0;

        System.out.println(textoRoxo + "\t\t TODOS ANIMAIS ASSOCIADOS A SI:" + textoNormal);

        for(int i=0; i<existeA.size(); i++) {
            if(nifD.equals(existeA.get(i).getNifDono())){

                System.out.println(System.lineSeparator().repeat(2));
                System.out.println(textoRoxo + "\t----- ANIMAL: " + existeA.get(i).getCodAnimal() + " -----" + textoNormal);
                System.out.println("\t\tNOME - " + existeA.get(i).getNome());
                System.out.println("\t\tESPECIE - " + existeA.get(i).getEspecie());
                System.out.println("\t\tNIF DO DONO - " + existeA.get(i).getNifDono());
                count++;
            }
        }
        if(count==0){
            System.out.println(textoVermelho + "\t\t NÃO EXISTEM ANIMAIS REGISTADOS COM O SEU NIF" + textoNormal);
        }

    }

    /*
        CRIAR NOVOS ANIMAIS
     */
    public void RegistarAnimal(String nifD) throws IOException {
        FileWriter registar = new FileWriter("animais.dat", true);
        Scanner sc = new Scanner(System.in);
        Formatter formatter;
        String nome, especie;

        try{
            //gera valores aleatorios
            String codAnimal = UUID.randomUUID().toString();

            //CRIA INFORMAÇÃO DO ANIMAL
            System.out.print("NOME DO ANIMAL: ");
            nome = sc.nextLine();
            System.out.print("ESPECIE DO ANIMAL: ");
            especie = sc.nextLine();

            //ADICIONA DADOS AO ARRAY DOS ANIMAIS
            existeA.add(new Animal(codAnimal, nome, especie, nifD));

            //GUARDAR OS REGISTOS NO FICHEIROS
            formatter = new Formatter(registar);
            formatter.format(codAnimal + ":");
            formatter.format(nome + ":");
            formatter.format(especie + ":");
            formatter.format(nifD + "\n");
            formatter.flush();
            formatter.close();

            if(formatter != null){
                System.out.println("\n\tFOI ADICIONADO O REGISTO DO ANIMAL:  " + codAnimal + " COM O NOME DE: " + nome);
            }else{
                System.out.println("REGISTO NÃO EFETUADO");
            }

        }catch (Exception e) {
            System.out.println("FICHEIRO NÃO ENCONTRADO!");

        }
    }
}
