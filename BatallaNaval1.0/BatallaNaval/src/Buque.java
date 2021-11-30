public class Buque{
    int largo;
    String nombre;
    int filas;
    int columnas;
    int bombas;
    int vida = 16;
    int orientacion;
    
    public void setBombas(int bombas) {
        this.bombas = bombas;
    }
    public int getBombas() {
        return bombas;
    }
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    public int getColumnas() {
        return columnas;
    }
    public void setFilas(int filas) {
        this.filas = filas;
    }
    public int getFilas() {
        return filas;
    }
    public void setLargo(int largo) {
        this.largo = largo;
    }
    public int getLargo() {
        return largo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }
    public int getOrientacion() {
        return orientacion;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getVida() {
        return vida;
    }
}