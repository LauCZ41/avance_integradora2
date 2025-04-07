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

    public String ataqueHumano(int x, int y){
        return miJugadorMaquina.ataque(x, y);
    }
    
    public String ataqueMaquina() {
        return miJugadorHumano.ataqueMaquina();
    }

    public void colocarBarcoMaquina(TipoBarco tipoBarco, boolean orientacionFija){
        miJugadorMaquina.colocarBarcoAleatorio(tipoBarco, orientacionFija);
    }

    public void mostrarBarcosAtacados(){
        miJugadorMaquina.mostrarBarcosAtacados();
    }

    public String mostrarPartidasGanadas() {
        return miJugadorHumano.getNombre() + " ha ganado " + miJugadorHumano.getPartidasGanadas() + " partida(s).\n"
             + miJugadorMaquina.getNombre() + " ha ganado " + miJugadorMaquina.getPartidasGanadas() + " partida(s).";
    }
    

    public boolean evaluarFinPartida(){
        if(miJugadorHumano.evaluarFinPartida()){
            miJugadorMaquina.aumentarPartidasGanadas();
            return true;
        } else if(miJugadorMaquina.evaluarFinPartida()){
            miJugadorHumano.aumentarPartidasGanadas();
            return true;
        }
        return false;
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
    