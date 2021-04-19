package cat.itb.asix.m03.uf2;

import java.util.Arrays;
import java.util.Scanner;

public class SecretWord {

/*Este es un menu muy simple con dos opciones, para iniciar el juego y cerrar el programa, tambien contempla
    que se introduzcan opciones invalidas, incluye un contador especial para el metodoCentral*/
    public static void menu() {

        int contadorEspecial = 0;
        Scanner lector = new Scanner(System.in);
        int opcion;
        System.out.println("Bienvenido al juego de las palabras secretas");
        System.out.println("Elije la opcion que quieras:");
        System.out.println("1) Jugar");
        System.out.println("2) Salir");
        opcion = lector.nextInt();
        switch (opcion){
            case 1:
                System.out.println("Debes adivinar la palabra, tienes 10 oportunidades");
                System.out.println("No importan las mayusculas, pero si los acentos");
                metodoCentral(contadorEspecial);
            case 2:
                break;
            default:
                System.out.println("Has introducido una opcion no valida\n\n");
                menu();
        }

    }

    private static void metodoCentral(int contadorEspecial) {
        //declaramos un array que contendra las letras de la secretWord en codigo ascii
        int[] arrayAsciiSecretWord = new int[5];
        //un string con la palabra a procesar y convertir a ascii
        String wordForProc;
        //un array que contendra las letras de la palabra introducida por el usuario en codigo ascii
        int[] arrayAsciiUser;
        //una variable con las oportunidades posbiles para el usuario
        int vidas = 10;
        //un boobleano que nos dira si el usuario a acertado o no la palabra
        boolean comprovacion;
        //Mientras el usuario no falle 10 veces se reproducira lo siguiente
        while (vidas>=0) {
            //Entra en juego el contador especial, que servira para procesar la secretWord una unica vez.
            if (contadorEspecial == 0) {
                //Almacenamos la secretWord en la variable que se enviara a procesar.
                wordForProc = palabraSecreta();
                System.out.println(wordForProc);//TODO eliminar, imprime la palabra secreta.
                //Inicialoizamos el array ascii de secret word con el resultado del metodo procesadorPalabra
                arrayAsciiSecretWord = procesadorPalabra(wordForProc);
                //Sumamos 1 al contador especial para que este proceso no se repita
                contadorEspecial++;

            } else {
                //Almacenamso la palabra introducida por el usuario en el metodo interfaz
                wordForProc = interfaz();
                //comprovamos el tamaño de la palabra
                if (wordForProc.length() < 6) {

                    //Inicialoizamos el array ascii del usuario con el resultado del metodo procesadorPalabra
                    arrayAsciiUser = procesadorPalabra(wordForProc);
                    //Almacenamos el resultado del comprovador
                    comprovacion = comprovador(arrayAsciiUser, arrayAsciiSecretWord, wordForProc);
                    //si es true significa que el usuario a acertado la palabra
                    if (comprovacion) {
                        System.out.println("Ets un CRACK ;-)");
                        //Salimos del metodo y por tanto lleva a que el programa se cierre
                        return;
                    /*si no ha acertado, se imprime un mensaje con las vidas restantes,
                    justo despues de restar una vida.*/
                    } else {
                        if (vidas>0) {
                            System.out.println("F");
                            vidas--;
                            System.out.println("Te quedan " + vidas + " intentos");
                        //si se queda a 0 vidas y el intento es fallido, se resta una vida mas para cerrar el while
                        }else {
                            vidas--;
                        }
                    }
                //si la palabra es demasiado larga se recuerda al usuario las reglas del sistema.
                }else {
                    System.out.println("Has introducido una palabra demasiado larga");
                    System.out.println("recuerda solo 5 caracteres");
                }
            }
        }//si se pierden todas las vidas se imprime un mensaje "insultante"
        System.out.println("Eres un L-user");
    }
    //este es el metodo que obtendra la palabra secreta
    private static String palabraSecreta() {

        DataSource ds = new DataSource();
        //recuperamos las palabras almacenadas de DataSource
        String[] arrayWords = ds.getWords();
        //esta sera la variable que almacene la palabra secreta
        String palabraRandom;
        /*creamos una variable de tipo int, que contiene un numero aleatorio con un valor maximo
         del largo del array de palabras*/
        int randomSelect = (int) (Math.random() * (arrayWords.length));
        //selecionamos la palabra en la posicion aleatoria para almacenarla y devolverla.
        palabraRandom = arrayWords[randomSelect];

        return palabraRandom;

    }
//este metodo procesara las palabras a un estado que se puedan comparar
    private static int[] procesadorPalabra(String wordForProc){
        //creamos in array de letras con el String wordForProc, convirtiendolo en caracteres individuales.
        char[] arrayLetras = wordForProc.toCharArray();
        char[] arrayLetrasLowerCase = new char[5];
        int[] arrayAscii = new int[5];
        /*recorremos todas las letras del array para convertirlas en minusculas y
        guardarlo en la variable correspondiente.*/
        for (int a = 0; a<arrayLetras.length; a++){
            arrayLetrasLowerCase[a]=Character.toLowerCase(arrayLetras[a]);
        }
        /*recorremos el array de en minusculas para convertilo en valores ascii y
        almacenarlo en la variable correspondiente*/
        for (int b = 0; b<arrayLetrasLowerCase.length; b++){
            arrayAscii[b]= arrayLetrasLowerCase[b];
        }
        //devolvemos el array ascii
        return arrayAscii;

    }
    //este es el metodo que pedira la palabra al usuario devolviendo la palabra para procesar al metodo central
    private static String interfaz(){

        Scanner lector = new Scanner(System.in);
        String wordForProc;
        System.out.println("introduce la palabra que creas: ");
        wordForProc = lector.nextLine();
        return wordForProc;
    }
    //este es el metodo mas importante, que comprovara las coincidencias de las palabras con la secretWord
    private static boolean comprovador(int[] arrayAsciiUser, int[] arrayAsciiSecretWord, String wordForProc) {

        boolean comprovacion = false;
        /*aunque inicializar la variable a parezca redundante, es necesario para que cada vez que se ejecute el metodo,
        empieze de 0 y no desde 5*/
        int a = 0;
        int aciertos = 0;
        int aciertosFalsos = 0;
        int posicionArrayUser = 0;
        //comparamos primero para saber si ha acertado de primeras, y evitar la ejecucion del resto del metodo.
        if (Arrays.equals(arrayAsciiUser, arrayAsciiSecretWord)){
            System.out.println("Has dicho: " + wordForProc);
            comprovacion=true;
        //si no ha acertado ejecutaremos la comparacion en un bucle while.
        }else {
            while (posicionArrayUser < 5) {
                /*recorreremso cada una de las letras de secretWord,
                comparandolas con cada una de las letras del array ascii del usuario*/
                for (a = 0; a < arrayAsciiSecretWord.length; a++) {
                    if (arrayAsciiUser[posicionArrayUser] == arrayAsciiSecretWord[a]) {
                        //si ademas de coincidir la letra, coincide la posicion, se suma un acierto
                        if (posicionArrayUser == a) {
                            aciertos++;
                        //si solo concuerda la letra pero no en la posicion, se suma un acierto falso
                        } else {
                            aciertosFalsos++;
                        }
                        /*con esta declaracion rompemos el for, haciendo que deje de comparar la letra anterior,
                         ya que ya se ha encontrado coincidencia.*/
                        a = arrayAsciiSecretWord.length;
                    }
                }
                /*en caso de no haber coincidencia, pasamos a la siguiente letra de la comparacion,
                sumando uno a la posicion del array del usuario*/
                posicionArrayUser++;
            }
            //una vez fianlizada al comparacion, imprimimos los resultados al usuario.
            System.out.println("Has dicho: " + wordForProc);
            System.out.println("letras coincidentes en la posicion correcta: " + aciertos);
            System.out.println("letras coincidentes en la posicion incorrecta: " + aciertosFalsos);

        }
        //devolvemos la comprovacion, que es False hasta que no se acierte la palabra.
        return comprovacion;
    }

}