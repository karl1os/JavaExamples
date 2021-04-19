package cat.itb.asix.m03.UF2;

import java.util.Scanner;

/**
 * Calculadora multi metodo: utilizando un metodo para cada tipo de operacion, otro para el menu y solo dos lineas en el main
 * @author Carlos Hernandez Navarro
 * @since 12/02/2021
 */

public class Calculadora {
//Declaramos primero el Scanner para diferentes metodos que lo usaran.
    Scanner lector = new Scanner(System.in);
//el main llama al menu que sera quien se encarge de gestionar los diferentes metodos a usar
    public static void main(String[] args){
        Calculadora program = new Calculadora();
        program.menu();
    }
// dependiendo de la opcion que elijamos ejecutaremos un metodo u otro excepto con la opcion 5 que sirve para salir del bucle y detener el programa.
    private void menu() {

        int opcioTriada;
        boolean opcionValida;
        System.out.println("Elija la opcion deseada");
        System.out.println("1. sumar");
        System.out.println("2. restar");
        System.out.println("3. multiplicar");
        System.out.println("4. dividir");
        System.out.println("5. Salir");
        opcionValida = lector.hasNextInt();//ademas comprovaremos la opcion introducida

        if (opcionValida) {
            opcioTriada = lector.nextInt();
            switch (opcioTriada) {

                case 1://ejecutamos el metodo sumar y una vez finalizado volvemos al menu

                    sumar();
                    menu();
                    break;

                case 2://ejecutamos el metodo restar y una vez finalizado volvemos al menu

                    restar();
                    menu();
                    break;

                case 3://ejecutamos el metodo multiplicar y una vez finalizado volvemos al menu

                    multiplicar();
                    menu();
                    break;

                case 4://ejecutamos el metodo dividir y una vez finalizado volvemos al menu

                    dividir();
                    menu();
                    break;

                case 5://salimos del switch y de menu, terminando el programa

                    break;

            }

        }else {

            System.out.println("Has introducido un valor invalido \n");
            menu();

        }

    }

    private double[] demanarOperador() {
//Inicializamos los dos operadores con los datos introducidos por el usuario y definimos un array temporal con los dos operadores para devolverlo al metodo que lo ha llamado.
        double operador1;
        double operador2;
        System.out.println("introduzca un operador: ");
        operador1 = lector.nextDouble();
        System.out.println("introduzca el segundo operador: ");
        operador2 = lector.nextDouble();
        double[] operadoresTemporales;
        operadoresTemporales = new double[]{operador1,operador2};
        return operadoresTemporales;

    }

    private void sumar() {//inicializa el array de operadores con el return del metodo demanarOperador, la operacion suma los dos valores e imprime el resultado

        double[] operadoresSuma = demanarOperador();
        double resultadoSuma = operadoresSuma[0] + operadoresSuma[1];
        System.out.println("El resultado de dividir los dos operadores es: " + resultadoSuma + "\n" + "---------------------" + "\n");

    }

    private void restar() {//inicializa el array de operadores con el return del metodo demanarOperador, la operacion resta los dos valores e imprime el resultado

        double[] operadoresResta = demanarOperador();
        double resultadoResta = operadoresResta[0] - operadoresResta[1];
        System.out.println("El resultado de dividir los dos operadores es: " + resultadoResta + "\n" + "---------------------" + "\n");

    }

    private void multiplicar() {//inicializa el array de operadores con el return del metodo demanarOperador, la operacion multiplica los dos valores e imprime el resultado

        double[] operadoresMulti = demanarOperador();
        double resultadoMulti = operadoresMulti[0] * operadoresMulti[1];
        System.out.println("El resultado de dividir los dos operadores es: " + resultadoMulti + "\n" + "---------------------" + "\n");

    }

    private void dividir() {//inicializa el array de operadores con el return del metodo demanarOperador, la operacion divide los dos valores e imprime el resultado

        double[] operadoresDiv = demanarOperador();
        double resultadoDiv = operadoresDiv[0] / operadoresDiv[1];
        System.out.println("El resultado de dividir los dos operadores es: " + resultadoDiv + "\n" + "---------------------" + "\n");

    }

}
