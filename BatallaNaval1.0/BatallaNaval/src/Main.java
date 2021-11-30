// SEBASTIAN MARTIN PONCE, MARCO MIGNACCO, SANTIAGO PEREZ.  
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GameManager mecanismos = new GameManager();
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        int contador = 0;
        int inInt1 = 0;
        int inInt2 = 0;
        Boolean flag = true;
        Boolean flag1 = false;

        mecanismos.crearTablero();
        System.out.println("Por favor, indique la dificultad: \n(1) Baja. \n(2) Media. \n(3) Alta.");

        while (flag1 == false) {
            try {
                opcion = entrada.nextInt();
            } catch (InputMismatchException er1) {
                System.out.println("Por favor, indique la dificultad (Ingrese un numero): \n(1) Baja. \n(2) Media. \n(3) Alta.");
                entrada.next();
                continue;
            }
            if (opcion == 1 || opcion == 2 || opcion == 3) {
                mecanismos.elegirDificultad(opcion);
                flag1 = true;
            }else{
                System.out.println("Por favor, indique la dificultad (Ingrese un numero Ej: 1, 2 o 3): \n(1) Baja. \n(2) Media. \n(3) Alta.");
                continue;
            }
        }
       
        
        
       
        while (contador < 5) {
            mecanismos.cargarDatos(contador);
            System.out.println("Por favor, indique la orientacion del barco " + mecanismos.buque.getNombre() + ". \n(1) Orientacion horizontal. \n(2) Orientacion vertical."); 
            try {
                mecanismos.buque.setOrientacion(entrada.nextInt());
            } catch (InputMismatchException er2) {
                System.out.println("Indique la orientacion del barco (Con un numero 1 o 2)"); 
                entrada.next();
                continue;
            }
           
           
            if (mecanismos.buque.getOrientacion() == 1) {

                mecanismos.introducirBarcosHorizontal();

            }else if(mecanismos.buque.getOrientacion() == 2){

                mecanismos.introducirBarcosVertical();
                
            }else {
                System.out.println("Lo siento, debe elegir 1 o 2.");
                continue;
            }
            contador++;
        }
    

        while (mecanismos.buque.getBombas() > 0 && mecanismos.buque.getVida() > 0) {
            mecanismos.mostrarTablero();
            System.out.println("Cantidad de bombas: " + mecanismos.buque.getBombas());
            
            
            while (flag == true) {
                if (mecanismos.getCoordenadaFila() < 1 || mecanismos.getCoordenadaFila() > 10) {
                    System.out.print("Ingrese la FILA (del 1 al 10): ");
                    try {
                        inInt1 = entrada.nextInt();
                    } catch (InputMismatchException er3) {
                        System.out.println("Ingrese un numero (1 al 10)\n");;
                        entrada.next();
                        continue;
                    }
                    if (inInt1 > 0 && inInt1 <= 10) {
                        mecanismos.setCoordenadaFila(inInt1);
                    }else{
                        System.out.println("Numero Ingresado Invalido\n");
                        continue;
                    }
                }else   
                    flag = false;
            }

            flag = true;

            while (flag == true) {
                if (mecanismos.getCoordenadaColumna() < 1 || mecanismos.getCoordenadaColumna() > 10) {
                    System.out.print("Ingrese la COLUMNA (del 1 al 10): ");
                    try {
                        inInt2 = entrada.nextInt();
                    } catch (InputMismatchException er3) {
                        System.out.println("Ingrese un numero (1 al 10)\n");;
                        entrada.next();
                        continue;
                    }
                    if (inInt2 > 0 && inInt2 <= 10) {
                        mecanismos.setCoordenadaColumna(inInt2);
                    }else{
                        System.out.println("Numero Ingresado Invalido\n");
                        continue;
                    }


                }else if(mecanismos.tableroVisual[mecanismos.getCoordenadaFila()][mecanismos.getCoordenadaColumna()] == "*" || mecanismos.tableroVisual[mecanismos.getCoordenadaFila()][mecanismos.getCoordenadaColumna()] == "B"){
                    System.out.println("\nLo siento. Debe ingresar coordenadas no descubiertas.");
                    mecanismos.setCoordenadaFila(0);
                    mecanismos.setCoordenadaColumna(0);
                    
                    flag = false;
                }else{
                    mecanismos.insertarHit(mecanismos.getCoordenadaFila(), mecanismos.getCoordenadaColumna());
                    mecanismos.setCoordenadaFila(0);
                    mecanismos.setCoordenadaColumna(0);
                    // Luego de haber introducido el hit se les debe atribuir el valor 0 (o un valor menor a 1 o mayor a 10) a las variables FILA y COLUMNA, las cuales sirven para establecer la coordenada donde se lanzará una bomba dentro del tablero. Esto se debe hacer ya que si a las variables no se les modifica el valor anterior, en las siguientes iteraciones provocará que se salteen los condicionales if, ya que no cumplen con las condiciones para entrar. Eso a su vez provocará un nuevo hit automatico en bucle hasta que las bombas se terminen.
                    flag = false;
                }
            }

            flag = true;
            
            if (mecanismos.buque.getVida() == 0) {
                System.out.println("Felicidades! Ha ganado!!");
            }else if(mecanismos.buque.getBombas() == 0){
                mecanismos.mostrarTablero();
                System.out.println("Cantidad de bombas: " + mecanismos.buque.getBombas());
                System.out.println("Lo siento! Ha perdido!");
            }
        }
        entrada.close();
    }   
}
