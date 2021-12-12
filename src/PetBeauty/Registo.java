package PetBeauty;

import java.io.*;
import java.util.*;

public class Registo {
    public Registo() { }

    public void login() throws FileNotFoundException, IOException {
        Ficheiro ler = new Ficheiro();
        ler.LerLogin();
    }
}
