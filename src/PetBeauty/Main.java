package PetBeauty;

import java.io.*;
import java.util.*;

public class Main {
    //public static ArrayList<User> existeUser = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        int op = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("\n-------- MENU --------");
            System.out.println("\t1 - LOGIN");
            System.out.println("\t0 - SAIR");

            do{
                System.out.print("Escolha uma opção: ");
                op = sc.nextInt();
            }while(op < 0 || op > 1);


            switch(op){
                case 1:{
                    Registo login = new Registo();
                    login.login();
                    break;
                }
                default:{
                    System.out.println("A SAIR ...");
                    System.exit(0);
                }
            }
        }while(op == 0);
    }
}
