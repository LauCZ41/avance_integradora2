package model;

public class Barco {
    private TipoBarco tipoBarco;
    private int fila;
    private int columna;
    private boolean  orientacion;
    private int[][] coordenadas;
    private boolean hundido = false;

    public Barco(int columna, int fila, TipoBarco tipoBarco, boolean orientacion){
        this.tipoBarco = tipoBarco;
        this.fila = fila;
        this.columna = columna;
        this.orientacion = orientacion;

        int tamano = tipoBarco.getLargoBarco();
        coordenadas = new int[tamano][2]; 

        for (int i = 0; i < tamano; i++) {
            if (orientacion) { 
                coordenadas[i][0] = columna + i;
                coordenadas[i][1] = fila;
            } else { 
                coordenadas[i][0] = columna;
                coordenadas[i][1] = fila + i;
            }
        }
    }
    // true = horizontal || false = vertical

    public boolean estaHundido(int[][] tablero) {
        if (hundido) return false; 
    
        for (int[] c : coordenadas) {
            int x = c[0];
            int y = c[1];
            if (tablero[y][x] != 2) {
                return false;
            }
        }
    
        hundido = true; 
        return true;
    }

    
    // get
    public TipoBarco getTipoBarco(){
        return tipoBarco;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public boolean getorientacion(){
        return orientacion;
    }
    public int[][] getCoordenadas(){
        return coordenadas; 
    }
    // set
    public void setFila(int newFila){
        fila=newFila;
    }
    public void setColumna(int newColumna){
        columna=newColumna;
    }
}
