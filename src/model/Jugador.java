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

    public boolean addBarcoPersonalizado(int x, int y, int largoBarco, boolean orientacion){
        Barco barco = new Barco(x, y, largoBarco, orientacion);
        return myTablero.colocarBarcoPersonalizado(barco);
    }


    public int[] generarCoordenadasAleatorias(TipoBarco tipoBarco, boolean orientacionFija) {
        int x, y;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
            
        } while (!myTablero.validarPosBarcos(x, y, tipoBarco, orientacionFija));
        return new int[]{x, y};
    }

    public int[] generarCoordenadasAleatoriasPersonalizada(int largoBarco, boolean orientacion){
        int x,y;
        do { 
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (!myTablero.validarPosBarcosPersonalizados(x, y, largoBarco, orientacion));
        return new int[]{x, y};
    }

    public void colocarBarcoMaquinaPersonalizado(int largoBarco, boolean orientacion){
        int[] coordenadas;
        boolean colocado = false;
    
        while (!colocado) {
            coordenadas = generarCoordenadasAleatoriasPersonalizada(largoBarco, orientacion);
            int fila = coordenadas[0];
            int columna = coordenadas[1];
            //int columna, int fila, int casillasPersonalizada, boolean orientacion
            Barco barco = new Barco(fila, columna, largoBarco, orientacion );
            if (myTablero.colocarBarcoPersonalizado(barco)) { 
                colocado = true;  
            }
        }
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


    public String ataque(int x, int y){
        return myTablero.maquinaRecibe(x, y);
    }

    
    public String ataqueMaquina() {
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        String resultado = myTablero.humanoRecibe(x, y);
        return "La máquina atacó en (" + (x + 1) + ", " + (y + 1) + "): " + resultado;
    }

    public boolean evaluarFinPartida(){
        return myTablero.evaluarFinPartida();
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

    public void aumentarPartidasGanadas(){
        partidasGanadas++;
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