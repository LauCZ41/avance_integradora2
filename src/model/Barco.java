package model;

public class Barco {
    private TipoBarco tipoBarco;
    private int fila;
    private int columna;
    private boolean  orientacion;
    private int[][] coordenadas;

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

    public boolean estaHundido(int[][] tablero){
        for(int[] c: coordenadas){
            int col = c[0];
            int fil = c[1];
            if (tablero[fil][col] != 2 && tablero[fil][col] != 3){
                return false;
            }
        }
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
