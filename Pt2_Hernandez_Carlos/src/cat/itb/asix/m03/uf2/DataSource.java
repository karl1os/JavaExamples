package cat.itb.asix.m03.uf2;

import java.util.Scanner;

public class DataSource {

    public String getDataFromKeyboard(){

        String fraseOriginal;
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce el texto que quieres remezclinar");
        fraseOriginal = lector.nextLine();      //guardamos la frase completa en la variable fraseOriginal

        return fraseOriginal;       //devolvemos en este caso a CrazyWords.iniciar el String fraseOriginal
    }


}
