package model;
import java.util.Random;

public class Jugador{
    private String nombre;
    private int partidasGanadas;
    private Tablero myTablero;
    private Random random;


    public Jugador(String nombre){
        this.nombre = nombre;
        this.partidasGanadas = 0;
        this.myTablero = new Tablero();
        this.random = new Random(); 
    }

    public boolean addBarco(int x, int y, boolean orientacion, TipoBarco tipoBarco) {
        Barco barco = new Barco( x, y, tipoBarco ,orientacion); 
        return myTablero.colocarBarco(barco);  
    }


    public int[] generarCoordenadasAleatorias(TipoBarco tipoBarco, boolean orientacionFija) {
        int x, y;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
            
        } while (!myTablero.validarPosBarcos(x, y, tipoBarco, orientacionFija));
        return new int[]{x, y};
    }

    public void colocarBarcoAleatorio(TipoBarco tipoBarco, boolean orientacionFija) {
        int[] coordenadas;
        boolean colocado = false;
    
        while (!colocado) {
            coordenadas = generarCoordenadasAleatorias(tipoBarco, orientacionFija);
            int fila = coordenadas[0];
            int columna = coordenadas[1];

            Barco barco = new Barco( fila, columna,tipoBarco, orientacionFija);
            if (myTablero.colocarBarco(barco)) { 
                colocado = true;  
            }
        }
    }


    public void ataque(int x, int y){
        myTablero.recibirAtaque(x, y);
    }

    
    public int[] ataqueMaquina(){
        int x,y;
        x=random.nextInt(10);
        y=random.nextInt(10);

        return new int[]{x,y};
    }


    public void getPosT(){
        myTablero.mostrarTablero();
    }

    public void mostrarBarcosAtacados(){
        myTablero.mostrarBarcosAtacados();
    }

    public void inicializarTablero(){
        myTablero.inicializarTablero();
    }

    //get 
    public String getNombre(){
        return nombre;
    }
    public int getPartidasGanadas(){
        return partidasGanadas;
    }
    public Tablero getTablero() {
        return this.myTablero;
    }


    //set
    public void setNombre(String newNombre){
        nombre=newNombre;
    }
    public void setParitdasGanadas(int newPartidasGanadas){
        partidasGanadas+=newPartidasGanadas;
    }
}