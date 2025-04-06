package model;


public class Controller {

    private Jugador miJugadorHumano;
    private Jugador miJugadorMaquina;

    public Controller(String nombreHumano){
        this.miJugadorHumano = new Jugador(nombreHumano);
        this.miJugadorMaquina = new Jugador("Maquina");
    }

    public boolean colocarBarcoHumano(int x, int y, boolean horientacion, TipoBarco tipoBarco){
        return miJugadorHumano.addBarco(x, y, horientacion, tipoBarco);
    }

    public void ataqueHumano(int x, int y){
        miJugadorMaquina.ataque(x, y);
    }

    public void colocarBarcoMaquina(TipoBarco tipoBarco, boolean orientacionFija){
        miJugadorMaquina.colocarBarcoAleatorio(tipoBarco, orientacionFija);
    }

    public void mostrarBarcosAtacados(){
        miJugadorMaquina.mostrarBarcosAtacados();
    }

    public void inicializarTablero() {
        miJugadorHumano.inicializarTablero();
        miJugadorMaquina.inicializarTablero();
    }
    
    public Tablero getTablero() {
        return miJugadorHumano.getTablero();
    }
    public Tablero getTableroMaquina() {
        return miJugadorMaquina.getTablero();
    }
    

    public void  mostrarTablero(){
        miJugadorHumano.getPosT();
    }
    
    public void mostrarTableroMaquina() {
        miJugadorMaquina.getPosT();
    }
}
    