import java.util.Random;
public class GameManager{
    String [][] tableroVisual = new String[11][11]; 
    Boolean [][] tableroTecnico = new Boolean[11][11];
    Buque buque = new Buque();
    Random random = new Random();
    int incrementoi = 0;
    int incrementoj = 0;
    int coordenadaFila = 0;
    int coordenadaColumna = 0;
    boolean lugaresVacios = false;


    // public Buque(String nombre, int largo){
    //     this.nombre = nombre;
    //     this.largo = largo;
    // }


    
    public void setCoordenadaFila(int coordenadaFila) {
        this.coordenadaFila = coordenadaFila;
    }
    public int getCoordenadaFila() {
        return coordenadaFila;
    }
    public void setCoordenadaColumna(int coordenadaColumna) {
        this.coordenadaColumna = coordenadaColumna;
    }
    public int getCoordenadaColumna() {
        return coordenadaColumna;
    }


    public void crearTablero(){
        for (int i = 0; i < tableroVisual.length; i++) {
            for (int j = 0; j < tableroVisual.length; j++) {
                if (i == 0 && j == 0){// En la posicion [0][0] del array visible va a generar un valor de tipo string vacio
                    tableroVisual[i][j] = "  ";
                    tableroTecnico[i][j] = null;
                }else if(i == 0){// Si estamos parados en la posicion 0 de las filas, se generara el numero de columnas correspondiente a la posicion. Ej: [0][1] = 1, [0][2] = 2... 
                    int u = j;
                    String numero = Integer.toString(u);
                    tableroVisual[i][j] = numero;
                    tableroTecnico[i][j] =null;
                }else if (i != 10 && j == 0){
                    int u = i;
                    String numero = Integer.toString(u);
                    tableroVisual[i][j] =(" " + numero);
                }else if(j == 0){// Si estamos parados en la posicion 0 de las columnas, se va a generar el numero de filas correspondientes. Ej: [1][0] = 1, [2][0] = 2...
                    int u = i;
                    String numero = Integer.toString(u);
                    tableroVisual[i][j] = numero;
                    tableroVisual[i][j] = null;
                }else{// Aca simplemente se van a generar los cuadros en las posiciones que se va a jugar
                    tableroTecnico[i][j] = false; //false = vacio 
                    tableroVisual[i][j] = "▢";   
                }
            }
            
        }
    }

    public void mostrarTablero(){
        for (int i = 0; i < tableroVisual.length; i++) {
            for (int j = 0; j < tableroVisual.length; j++) {
                System.out.print(tableroVisual[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void elegirDificultad(int opcion){
        switch(opcion) {
            case 1:
                buque.setBombas(50);
                break;
            
            case 2:
                buque.setBombas(35);
                break;

            case 3:
                buque.setBombas(20);
                break;
        }
    }
    
    public void cargarDatos(int contador){
        switch (contador) {
            case 0:
            buque.setLargo(2);   
            buque.setNombre("Monitor 1");
                break;
            case 1:
            buque.setLargo(2); 
            buque.setNombre("Monitor 2");
                break;
            case 2:
            buque.setLargo(3); 
            buque.setNombre("Fragata");
                break;
            case 3:
            buque.setLargo(4); 
            buque.setNombre("Acorazado");
                break;
            case 4:
            buque.setLargo(5); 
            buque.setNombre("Destructor");
                break;
        }
    }

    public void checkDisponibilidad(){
        int acumulador = 0;
        buque.filas = random.nextInt(10);
        buque.columnas = random.nextInt(10);
        
        if (buque.getOrientacion() == 1) {
                checkIsInTablero();
                while (incrementoj < buque.largo) {
                    if (tableroTecnico[buque.filas + 1][(buque.columnas + 1) + incrementoj] == false) {
                        acumulador++;
                    }
                    incrementoj++;
                }
                if (acumulador != buque.largo) {
                    incrementoi--;
                }
            
        }else if(buque.getOrientacion() == 2){
                checkIsInTablero();
                while (incrementoj < buque.largo) {
                    if (tableroTecnico[(buque.filas + 1) + incrementoj][buque.columnas + 1] == false) {
                        acumulador++;
                    }
                    incrementoj++;
                }
                if (acumulador != buque.largo) {
                    incrementoi--;
                }         
        }
    }

    public void checkIsInTablero(){// CHEQUEA SI ESTA EN EL TABLERO
        if (buque.getOrientacion() == 1) {
            while (((buque.columnas + 1) + buque.largo ) > tableroTecnico.length) {
                buque.columnas = random.nextInt(10);
            }
        }else if(buque.getOrientacion() == 2){
            while (((buque.filas + 1) + buque.largo ) > tableroTecnico.length) {
                buque.filas = random.nextInt(10);
            }
        }
    }

    public void introducirBarcosHorizontal(){
        while (incrementoi < 1) {
            checkDisponibilidad();
            incrementoj = 0;
            incrementoi++;
        }
        incrementoi--;
        while (incrementoi < buque.largo) {
            tableroTecnico[buque.filas + 1][(buque.columnas + incrementoi) + 1] = true;
            incrementoi++;
        }
        incrementoi = 0;
    }

    public void introducirBarcosVertical(){
        while (incrementoi < 1) {
            checkDisponibilidad();
            incrementoj = 0;
            incrementoi++;
        }
        incrementoi--;
        while (incrementoi < buque.largo ) {
            tableroTecnico[(buque.filas + incrementoi) + 1][buque.columnas + 1] = true;
            incrementoi++;
        }
        incrementoi = 0;
    }



    public void insertarHit(int coordenadaFila, int coordenadaColumna){
        if(tableroTecnico[coordenadaFila][coordenadaColumna] == true){
            tableroVisual[coordenadaFila][coordenadaColumna] = "B";
            buque.setVida(buque.vida - 1);
        }else
            tableroVisual[coordenadaFila][coordenadaColumna] = "*";
            buque.setBombas(buque.bombas - 1);
    }

}