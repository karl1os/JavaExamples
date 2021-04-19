/*@author Carlos Hernandez Navarro
@since 06/04/2021
@use Implementar en Java un programa que pida una frase por teclat y la devuelva por pantalla mezclando el centro de cada palabra, excepto la primera y la ultima letra
*/
package cat.itb.asix.m03.uf2;

public class Main {

    public static void main (String[] args){

        CrazyWords cw = new CrazyWords();       //inicializamos el la clase CrazyWords
        String fraseMezclada = cw.iniciar();        //Pedimos a cw(CrazyWords).iniciar un String llamado fraseMezclada
        System.out.println(fraseMezclada);      //imprimimos el String frase mezclada devuelta por cw

    }

}
