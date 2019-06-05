package main;

import avatares.*;


public class Main {

    public static void main(String[] args) {

        Avatar[] avatares = new Avatar[4];

        avatares[0] = new Coche(null);
        avatares[1] = new Pelota(null);
        avatares[2] = new Sombreiro(null);
        avatares[3] = new Esfinxe(null);

        for (Avatar avatar : avatares){
            System.out.println("Id: "+avatar.getId());
        }

    }
}
