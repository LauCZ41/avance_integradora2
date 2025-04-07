package model;
import java.util.ArrayList;


public class Tablero {
    private final int TAMANO = 10;
    private int[][] tablero;
    private ArrayList<Barco> barcos;

    public Tablero() {
        tablero = new int[TAMANO][TAMANO];
        barcos = new ArrayList<>();
        inicializarTablero();
    }
    
    public void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = 0; // Agua
            }
        }
    }

    public String maquinaRecibe(int x, int y) {
        if (tablero[y][x] == 1) {
            tablero[y][x] = 2;
            for (Barco barco : barcos) {
                if (barco.estaHundido(tablero)) {
                    for (int[] c : barco.getCoordenadas()) {
                        tablero[c[1]][c[0]] = 3;
                    }
                    return "¡Hundiste el barco " + barco.getTipoBarco() + "!";
                }
            }
            return "¡Le has dado a un objetivo enemigo!";
        } else {
            return "¡No hay nada allí!";
        }
    }

    public boolean evaluarFinPartida(){
        int contador = 0;
        for(int i = 0; i<TAMANO; i++){
            for(int j = 0; j <TAMANO; j++){
                if(tablero[i][j] == 3){
                    contador++;
                }
            }
        }
        if(contador>=18){
            return true;
        }
        return false; 
    }

    public String humanoRecibe(int x, int y) {
        if (tablero[y][x] == 1) {
            tablero[y][x] = 2;
            for (Barco barco : barcos) {
                if (barco.estaHundido(tablero)) {
                    for (int[] c : barco.getCoordenadas()) {
                        tablero[c[1]][c[0]] = 3;
                    }
                    return "¡Te hundieron el barco " + barco.getTipoBarco() + "!";
                }
            }
            return "¡Te han dado!";
        } else {
            return "¡No hay nada allí!";
        }
    }
    


    public void addBarco(Barco barco){
        barcos.add(barco);
    }


    public boolean validarPosBarcos(int fila, int columna, TipoBarco tipoBarco, boolean horizontal) {
        int largoBarco = tipoBarco.getLargoBarco();
        // limites tablero
        if ((horizontal && (columna + largoBarco > TAMANO)) || (!horizontal && (fila + largoBarco > TAMANO))) {
    
            return false; 
        }
    
        // solaparse
        for (int i = 0; i < largoBarco; i++) {
            if (horizontal) { 
                if (tablero[fila][columna + i] != 0) { 
                    return false;
                }
            } else { // Barco vertical
                if (tablero[fila + i][columna] != 0) { 
                    return false;
                }
            }
        }
    
        return true;
    }
    
    public boolean colocarBarco(Barco barco) {
        TipoBarco tipoBarco = barco.getTipoBarco();
        int fila = barco.getFila();
        int columna = barco.getColumna();
        boolean horizontal = barco.getorientacion();
    
        if (!validarPosBarcos(fila, columna, tipoBarco, horizontal)) {
            return false; 
        }
    
        for (int i = 0; i < tipoBarco.getLargoBarco(); i++) {
            if (horizontal) { 
                tablero[fila][columna + i] = 1;
            } else {
                tablero[fila + i][columna] = 1;
            }
        }
        barcos.add(barco); 
        return true;
    }
    

    public void mostrarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(tablero[i][j] + " "); 
            }
            System.out.println();
        }
    }

    public void mostrarBarcosAtacados() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] != 1) {
                    System.out.print(tablero[i][j] + " "); 
                } else {
                    System.out.print("0 "); 
                }
            }
            System.out.println(); 
        }
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                sb.append(tablero[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}