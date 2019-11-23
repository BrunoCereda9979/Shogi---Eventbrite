
package shogi.main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Flia_cer
 */
public class Shogi {

    public static void main(String[] args) throws InterruptedException {
        
        // TODO code application logic here

        Ayudante miAyudante = new Ayudante(); //AYUDANTE
        Tablero tableroJuego = new Tablero(miAyudante); //TABLERO VACIO
        Jugador jugadorUno = new Jugador(); //Jugador 1  
        Jugador jugadorDos = new Jugador(); //Jugador 2
        Jugador jugadorActual; //Jugador que tiene el turno
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList <Jugador> jugadores = new ArrayList (); //Lista de jugadores
        int turno = 0; //Varia entre 0 (Jugador 1) y 1 (Jugador 2)
        boolean hayGanador = false;
        Scanner leerOpcion = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        
        miAyudante.inicializarJugadores(jugadorUno, jugadorDos);
        
        jugadores.add(jugadorUno); //Agrego a los jugadores a la lista
        jugadores.add(jugadorDos);
        
        tableroJuego.colocarPiezas(); //Coloco todas las piezas en el tablero
        
        System.out.println("\n     ************* ESTE ES EL TABLERO DE JUEGO *************\n");
        
        tableroJuego.displayTablero();
        
        System.out.println("\nLa piezas de ARRIBA pertenecen al jugador BLANCO y las de ABAJO al jugador NEGRO");
        
        do {
            jugadorActual = jugadores.get(turno); 
            
            System.out.println("\n****************** TURNO DE " + jugadorActual.getNombre() + " (Jugador " + jugadorActual.getColor() + ") ******************");
            
            do {
                
                System.out.println("1) Mover Pieza");
                System.out.println("2) Reintroducir Pieza");
                opcion = leerOpcion.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.err.println("-MOVER-");
                        hayGanador = tableroJuego.moverPieza(jugadorActual);
                        tableroJuego.coronarPieza(); //Revisar si hay que coronar una pieza
                        salir = true;
                        break;
                    case 2:
                        salir = tableroJuego.reintroducirPieza(jugadorActual);
                        break;
                    default:
                        System.out.println("Esa opcion no es valida\n");
                        tableroJuego.coronarPieza(); //Revisar si hay que coronar una pieza
                        salir = false;
                        break;
                }
            } while (salir == false); //No salir mientras jugadorActual no haya movido o reintroducido una pieza
                
            tableroJuego.displayTablero();

            turno = miAyudante.cambiarTurno(turno);
            
        } while (!hayGanador);
        
        System.out.println("\n*-*-*-*-*-*-*-*-*-* ¡FELICIDADES " + jugadorActual.getNombre() + " ERES EL GANADOR! *-*-*-*-*-*-*-*-*-*");
    }    
}
