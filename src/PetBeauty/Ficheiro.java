package PetBeauty;

import java.io.*;
import java.util.*;

public class Ficheiro {
    //public static ArrayList<User> existeUser = new ArrayList<>();
    //ArrayList useers = new ArrayList();

    public Ficheiro() { }

    public void LerLogin() throws FileNotFoundException {

        File f = new File("registo.dat");
        Formatter formatter;
        if(!f.exists()){
            formatter = new Formatter(f);
            formatter.format("ADMIN:admin:Admin:Admin:3");
            formatter.flush();
            formatter.close();
        }else {
            Scanner scn = new Scanner(System.in);
            try {
                int flag = 0;
                int est = 0;
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
                        //existeUser.add(new User(divLinha[0].trim(), divLinha[1].trim(), divLinha[2].trim(), divLinha[3].trim(), divLinha[4].trim()));

                        if (us.equals(divLinha[0]) && pss.equals(divLinha[1])) {
                            flag = 1;
                            if (divLinha[4].equals("1")) {
                                est = 1;
                            } else if (divLinha[4].equals("2")) {
                                est = 0;
                            }
                            break;
                        }
                    } while (sc.hasNextLine());

                    if (us.equals("ADMIN") && pss.equals("ADMIN")) {
                        System.out.println("\n\tADMIN LOGADO COM SUCESSO!");
                        Thread.sleep(1000);
                        /*Admin ad = new Admin();
                        ad.MenuAdmin();*/
                        System.out.println("Entra menu admin");
                        break;
                    }
/*
                    if (flag == 1 && est == 1) {
                        System.out.println("\n\tLOGIN EFETUADO COM SUCESSO!");
                        Thread.sleep(1000);
                        User u = new User(us);
                        u.menuUser();
                    } else if (flag == 1 && est == 0) {
                        System.out.println("\n\tLOGIN EFETUADO COM SUCESSO!");
                        Thread.sleep(1000);
                        UserManager um = new UserManager(us);
                        um.menuUserManager();
                    }*/
                    if (flag == 0) {
                        System.out.println("\tUSERNAME/PASSWORD INVÁLIDOS!");
                    }
                } while (flag == 0);

            } catch (Exception e) {
                System.out.println("Ficheiro não encontrado!");
                System.exit(0);
            }
        }
    }
}
