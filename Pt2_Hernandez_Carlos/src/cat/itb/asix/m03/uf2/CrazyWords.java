package cat.itb.asix.m03.uf2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CrazyWords {

    public String iniciar() {

        DataSource ds = new DataSource();       //inicializamos la clase DataSource
        String textDS = ds.getDataFromKeyboard();       //pedimos a ds(DataSource).getDataFromKeyboard

        return procesadorTexto(textDS);     //devolvemos a quien llame a este metodo el resultado del metodo procesadorTexto al cual pasamos por parametro el String textDS
    }

    private String procesadorTexto(String textDS) {     //este metodo procesara la frase para separarla por palabras

        StringBuilder fraseMezclada = new StringBuilder();      //inicializamor un StringBuilder para unir las palabras segun las necesidades
        String[] arrayWords = textDS.split(" ");        //inicializamos un array con las palabras por separado
        String wordForProc;     //creamos una variable que almacenara la palabra a mezclar
        for (int i = 0; i< arrayWords.length; i++){     //recorremos el array almacenando las palabras en wordForProc

            wordForProc = arrayWords[i];
            if (wordForProc.length()>3){        //si la palabra mide mas de 3 caracteres se ejecuta lo siguiente

                fraseMezclada.append(procesadorPalabras(wordForProc)).append(" ");      //a la frase mezclada se le unira el return de procesadorPalabras con el parametro Stting wordForProc separado a continuacion por un espacio

            }else {     //en caso contrario

                fraseMezclada.append(wordForProc).append(" ");      //la palabra se uni a la frase directamente

            }
        }

        return fraseMezclada.toString();        //devuelve fraseMezclada como un simple String

    }

    private String procesadorPalabras(String wordForProc) {     //el siguiente metodo procesara la palabra anteriormente seleccionada

        int largoWord = wordForProc.length();       //variable con el largo de la palabra para procesar
        String primeroSimbolo = "";     // variable que contendra los primeros simbolos, si es que existen
        String ultimoSimbolo = "";      // variable que contendra los ultimos simbolos, si es que existen
        int firstMin = 0;       //variable para determinar el punto minimo de la primera letra
        int firstMax = 1;       //variable para determinar el punto maximo de la primera letra
        int lastMin = largoWord-1;      //variable para determinar el punto minimo de la ultima letra
        int lastMax = largoWord;        //variable para determinar el punto maximo de la ultima letra
        String primeraLetra = (wordForProc.substring(0,1));     //variable que conservara la primera letra de la palabra
        while (primeraLetra.matches(".*[@#$%^&+=?¿¡!()·',].*")){        //comprovamos si la primera letra, es una letra, o un simbolo

            primeroSimbolo += primeraLetra;     //guardamos en una variable el simbolo encontrado
            firstMin ++;        //sumamos al minimo de la primera letra para no repetir el caracter
            firstMax ++;        //sumamos al maximo de la primera letra para no repetir el caracter
            largoWord --;       //reducimos el tamaño de la palabra pues los caracteres no cuentan
            primeraLetra = (wordForProc.substring(firstMin,firstMax));      //de nuevo guardamos el primer caracter con lso minimos y maximos modificados y repetimos el while

        }
        String ultimaLetra = (wordForProc.substring(lastMin,lastMax));        //variable que conservara la ultima letra de la palabra
        while (ultimaLetra.matches(".*[@#$%^&+=?¿¡!()·',].*")){

            ultimoSimbolo += ultimaLetra;       //guardamos en una variable el simbolo encontrado
            lastMin --;     //restamos al minimo de la ultima letra para no repetir el caracter
            lastMax --;     //restamos al maximo de la ultima letra para no repetir el caracter
            largoWord--;    //reducimos el tamaño de la palabra pues los caracteres no cuentan
            ultimaLetra = (wordForProc.substring(lastMin,lastMax));     //de nuevo guardamos el primer caracter con lso minimos y maximos modificados y repetimos el while

        }
        String centroPalabraCompleto = (wordForProc.substring(firstMax,lastMin));       //variable con las letras centrales para mezclar

        return primeroSimbolo+primeraLetra+mezclador(centroPalabraCompleto, largoWord)+ultimaLetra+ultimoSimbolo;       //finalmente unimos el/los primero/s simbolo/s, la primera letra con el centro mezclado usando el metodo mezclador, el/los ultimo/s simbolo/s y la ultima palabra para devolverlas
    }

    private String mezclador(String centroPalabraCompleto, int largoWord) { //metodo que mezclara el centro de la palabra

        List<String> centroPalabra = Arrays.asList(centroPalabraCompleto.split(""));        //Array list con las letras centrales separadas para ser procesadas
        String centroMezclado = "";     // creamos la variable que guardara el centro de la palabra ya mezclada
        Collections.shuffle(centroPalabra);     //mezclara las letras contenidas en la lista que actua como un array, pero no lo es
        for (int x=0; x<largoWord-2; x++){      //insertara las letras una a una convirtiendolo en una sola palabra

            centroMezclado += centroPalabra.get(x);     //insertamos la letra extraida de la posicion x del array list

        }
        return centroMezclado;      //devolvemos el centro mezclado al metodo anterior
    }

}