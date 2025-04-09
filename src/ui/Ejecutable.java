package ui;
import java.util.Scanner;
import model.Controller;
import model.TipoBarco;

public class Ejecutable {
    private static Scanner reader = new Scanner(System.in);

    //relaciones 
    public static Controller myController;

    public void menu(){
        int opcion = 0;
        do {
            System.out.println("\nBienvenido a la simulacion de Batalla Naval para los estudiantes de Economia de la Universidad ICESI\n");
            System.out.println("Te presentamos las siguientes opciones, ingresa:");
            System.out.println("1 - Partida perzonalizada");
            System.out.println("2 - Partida estandar");
            System.out.println("3 - Ver registro de partidas ganadas");
            System.out.println("4 - Salir del programa :(");
            opcion = reader.nextInt();
            reader.nextLine();

            switch(opcion){
                case 1: partidaPersonalizada();
                    break;
                case 2: partidaEstandar();
                    break;
                case 3: mostrarPartidasGanadas();
                    break;
                case 4: 
                    System.out.println("Gracias por jugar <3");
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        Ejecutable exe = new Ejecutable();
        exe.menu();
    }

    public static void partidaEstandar(){
        reader.nextLine();
        System.out.println("Ingresa tu nombre ");
        String nombre = reader.nextLine();
        myController = new Controller(nombre);
        myController.inicializarTablero();

        System.out.println("A continuacion, se te pedira que coloques tus barcos en el tablero ");
        System.out.println("Los barcos no deben solaparse y deben ajustarse a las reglas de orientacion ");
        System.out.println("Indica las coordenadas X, Y para cada barco. Recuerda que el tablero es de 10x10");


        preguntarBarco(TipoBarco.LANCHA, true);
        preguntarBarco(TipoBarco.MEDICO, false);
        preguntarBarco(TipoBarco.PROVISION, true);
        preguntarBarco(TipoBarco.MUNICION, false);
        //myController.mostrarTablero();
        preguntarBarco(TipoBarco.GUERRA, true);
        //myController.mostrarTablero();
        preguntarBarco(TipoBarco.PORTAVIONES, false);
        System.out.println("Finalmente la disposicion de tu tablero con tus barcos queda asi: ");
        myController.mostrarTablero();

        myController.colocarBarcoMaquina(TipoBarco.LANCHA, true);
        myController.colocarBarcoMaquina(TipoBarco.MEDICO, false);
        myController.colocarBarcoMaquina(TipoBarco.PROVISION, true);
        myController.colocarBarcoMaquina(TipoBarco.MUNICION, false);
        myController.colocarBarcoMaquina(TipoBarco.GUERRA, true);
        myController.colocarBarcoMaquina(TipoBarco.PORTAVIONES, false);
        System.out.println("   ");
        //myController.mostrarTableroMaquina();
        System.out.println("¡Muy bien! ¡Ahora vamos a jugar! ");
        boolean juegoTerminado = false;
        do { 
            ataqueHumano();
            juegoTerminado = evaluarFinPartida();
        
            if (!juegoTerminado) {
                myController.mostrarBarcosAtacados();
                ataqueMaquina();
                juegoTerminado = evaluarFinPartida();
        
                if (!juegoTerminado) {
                    System.out.println("Finalmente la disposición de tu tablero con tus barcos queda asi:");
                    myController.mostrarTablero();
                }
            }
        
        } while (!juegoTerminado);
    }

    public static void partidaPersonalizada(){
        System.out.println("Ingresa tu nombre ");
        String nombre = reader.nextLine();
        myController  = new Controller(nombre);
        myController.inicializarTablero();

        int cantidadDeBarcos = 0;
        int contador = 0;
        do { 
            System.out.println("¿Con cuantos barcos quieres jugar? (5 max)");
            cantidadDeBarcos = reader.nextInt();
            if(cantidadDeBarcos > 5 || cantidadDeBarcos<1){
                System.out.println("Error, cantidad no valida");
            }
        } while (cantidadDeBarcos > 5 || cantidadDeBarcos<1);
        do{
            preguntarBarcoPersonalizado(contador);
            myController.mostrarTablero();
            System.out.println(" ");
            myController.mostrarTableroMaquina();
            contador++;
        }while(cantidadDeBarcos>contador);

        
       
    }


    public static void preguntarBarco(TipoBarco tipoBarco, boolean orientacion) {
        String o = orientacion ? "horizontal" : "vertical";
        System.out.println("Coloca tu Barco " + tipoBarco + " (" + tipoBarco.getLargoBarco() + " Casillas, " + o + ")");
    
        int fila, col;
        boolean barcoColocado = false; 
    
        do { 
            do { 
                System.out.println(" Ingresa la coordenada X de " + tipoBarco + " (1-10): ");
                col = reader.nextInt();
            } while (col < 1 || col > 10);
            do { 
    
                System.out.println(" Ingresa la coordenada Y de " + tipoBarco + " (1-10): ");
                fila = reader.nextInt();
            } while (fila < 1 || fila > 10); 
            barcoColocado = myController.colocarBarcoHumano(fila - 1, col - 1, orientacion, tipoBarco);
    
            if (!barcoColocado) {
                System.out.println(" No se puede colocar el barco en esa posicion. Intenta de nuevo.");
            } 
            
            System.out.print("El " + tipoBarco + " se colocará " + 
            (orientacion ? "horizontalmente" : "verticalmente") + " en las coordenadas ");
            int largo = tipoBarco.getLargoBarco();
            for (int i = 0; i < largo; i++) {
                int x = orientacion ? col + i : col;
                int y = orientacion ? fila : fila + i;
                System.out.print("(" + x + ", " + y + ")");
                if (i < largo - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println(".");
                }
            }
        } while (!barcoColocado);  
    }

    public static void preguntarBarcoPersonalizado(int i){
        int longitudBarco = 0;
        int codigoOrientacion=0;
        boolean orientacion;
        boolean barcoColocado = false;
        int x, y;
        do{
            do { 
                System.out.println(" Ingrese la loongitud de su barco personalizado "+(i+1)+" (max 5 casillas)");
                longitudBarco = reader.nextInt();
                if(longitudBarco<1|| longitudBarco>5){
                    System.out.println("la longitud del barco tiene que ser mayor a 0 y menor o igual 5 ");
                }
            } while (longitudBarco<1 || longitudBarco>5);
            do { 
                System.out.println("Ingrese la orientacion de su barco "+(i+1)+", 1 para horizontal y 2 para vertical");
                codigoOrientacion = reader.nextInt();
                if(codigoOrientacion!=1 && codigoOrientacion!=2){
                    System.out.println("Numero no valido");
                }
                if(codigoOrientacion == 1){
                    orientacion = true;
                } else{
                    orientacion = false;
                }
            } while (codigoOrientacion<0 || codigoOrientacion>3);
            do { 
                System.out.println(" Ingresa la coordenada X (1-10): ");
                x = reader.nextInt();
            } while (x < 1 || x > 10); 
            do { 
                System.out.println(" Ingresa la coordenada Y (1-10): ");
                y = reader.nextInt();
            } while (y < 1 || y > 10);

            barcoColocado = myController.colocarBarcoPersonalizado(x-1, y-1, longitudBarco, orientacion);
            myController.colocarBarcoPersonalizadoMaquina(longitudBarco, orientacion);

        }while(!barcoColocado);

    }

    public static void ataqueHumano(){
        int x = 0;
        int y = 0;
        do { 
            System.out.println("Dime la coordenada X a atacar en el tablero rival:");
            x = reader.nextInt();
            x--;
            if(x<0 || x>10){
                System.out.println("Erro, coordenada fuera del tablero, intente de nuevo");
            }
        } while (x<0 || x>10);
        do { 
            System.out.println("Dime la coordenada y a atacar en el tablero rival:");
            y = reader.nextInt();
            y--;
            if(y<0 || y>10){
                System.out.println("Erro, coordenada fuera del tablero, intente de nuevo");
            }
        } while (y<0 || y>10);
        String mensaje = myController.ataqueHumano(x, y);
        System.out.println(mensaje);
    }

    public static void ataqueMaquina(){
        String mensaje = myController.ataqueMaquina();
        System.out.println(mensaje);
    }

    public static boolean evaluarFinPartida(){
        if(myController.evaluarFinPartida()){
            System.out.println("La partida ha finalizado");
            return true;
        }
        return false;
    }

    public static void mostrarPartidasGanadas(){
        if (myController == null) {
            System.out.println("Aun no has iniciado ninguna partida.");
        } else {
            System.out.println(myController.mostrarPartidasGanadas());
        }
    }
}

