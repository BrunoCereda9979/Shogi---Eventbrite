/*
    ESTA CLASE COMO SU NOMBRE LO INDICA, ES UN AYUDANTE DEL MAIN 
    Y DEL TABLERO GENERA LAS PIEZAS NECESARIAS, INICIALIZA A LOS 
    JUGADORES CON SUS DATOS CORRESPONDIENTES, ETC.
 */
package shogi.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import shogi.movimientos.*;

/**
 *
 * @author Flia_cer
 */
public class Ayudante {
    
    /*-------------------------------INICIALIZAR JUGADORES-------------------------------*/
    //Este metodo inicializa a los jugadores con un nombre y un color aleatorio
    
    public void inicializarJugadores (Jugador jugador1, Jugador jugador2) {
        String nombreJugador;
        String colorAleatorio = null;
        Scanner leerNombre = new Scanner(System.in);
        Random r = new Random();
        int numeroAleatorio = r.nextInt((1 - 0) + 1) + 0; //Numero aleatorio entre 0 y 1
        
        System.out.println("       ¡Bienvenidos al juego de Shogi!");
        
        System.out.println("\n*********** INGRESEN SUS NOMBRES **********");
        
        System.out.println("Nombre del JUGADOR 1: ");
        nombreJugador = leerNombre.nextLine();
        jugador1.setNombre(nombreJugador);
        
        System.out.println("Nombre del JUGADOR 2: ");
        nombreJugador = leerNombre.nextLine();
        jugador2.setNombre(nombreJugador);
        
        if (numeroAleatorio == 0) {
            jugador1.setColor("blanco");
        }
        else {
            jugador1.setColor("negro");
        }
        
        if ("blanco".equals(jugador1.getColor())) {
            jugador2.setColor("negro");
        }
        else {
            jugador2.setColor("blanco");
        }
    }
    
    /*-------------------------------PEDIR LAS COORDENDAS PARA MOVER UNA PIEZA-------------------------------*/
    //Este metodo permite que los jugadores ingresen las coordenadas necesarias
    
    public ArrayList<Integer> pedirCoordenadas () {
        ArrayList<Integer> coordenadas = new ArrayList();
        Scanner leerCoordenada = new Scanner(System.in);
        int deX, deY, aX, aY;
        
        do {
            System.out.println("Mover desde (FILA): ");
            
            while (!leerCoordenada.hasNextInt()) {
                System.err.println("Esa coordenada no es valida!");
                leerCoordenada.next();
            }
            
            deX = leerCoordenada.nextInt();
            
        } while (deX < 0);
        
        do {
            System.out.println("Mover desde (COLUMNA): ");
            
            while (!leerCoordenada.hasNextInt()) {
                System.err.println("Esa coordenada no es valida!");
                leerCoordenada.next();
            }
            
            deY = leerCoordenada.nextInt();
            
        } while (deY < 0);
        
        do {
            System.out.println("Mover hasta (FILA): ");
            
            while (!leerCoordenada.hasNextInt()) {
                System.err.println("Esa coordenada no es valida!");
                leerCoordenada.next();
            }
            
            aX = leerCoordenada.nextInt();
            
        } while (aX < 0);
        
        do {
            System.out.println("Mover hasta (FILA): ");
            
            while (!leerCoordenada.hasNextInt()) {
                System.err.println("Esa coordenada no es valida!");
                leerCoordenada.next();
            }
            
            aY = leerCoordenada.nextInt();
            
        } while (aY < 0);
        
        coordenadas.add(deX); //Posicion 0
        coordenadas.add(deY); //Posicion 1
        coordenadas.add(aX);  //Posicion 2
        coordenadas.add(aY);  //Posicion 3
        
        return coordenadas;
    }
    
    
    /*-------------------------------ELEGIR UNA PIEZA CAPTURADA-------------------------------*/
    //Este metodo sirve para que el jugador elija que pieza desea reintroducir en el tablero
    
    public Pieza elegirPiezaCapturada (Jugador jugador) {
        
        Pieza piezaSeleccionada = null;
        ArrayList <Pieza> listaDePiezas = jugador.getPiezasCapturadas();
        Scanner leerNumero = new Scanner (System.in);
        int numeroPieza = 0;
        
        if (listaDePiezas.isEmpty()) { // 1) Si la lista esta vacia, piezaSeleccionada = null
            piezaSeleccionada = null;
        }
        else { // Si la lista no esta vacia, mostrarle al jugador sus piezas capturadas
            System.out.println("*** PIEZAS QUE HAS CAPTURADO ***");
            
            for (int i = 0; i < listaDePiezas.size(); i++) {
                System.out.println(i + "-" + "[" + listaDePiezas.get(i).getAcronimoPieza() + "]");
            }
            
            // 2) El jugador debe elegir una utilizando su acronimo
            System.out.println("- Elige una pieza para reintroducir (SELECCIONA SU NUMERO) -");
            numeroPieza = leerNumero.nextInt();
            
            // 3) Devolver la pieza seleccionada
            piezaSeleccionada = listaDePiezas.get(numeroPieza);
        }
        
        return piezaSeleccionada;
    }
    
    /*-------------------------------CAMBIAR DE TURNOS ENTRE JUGADORES-------------------------------*/
    //Este metodo sirve para que los turnos vayan variando entre los dos jugadores
    
    public int cambiarTurno (int turno) {
        if (turno == 0) {
            turno += 1;
        }
        else {
            turno -= 1;
        }
        
        return turno;
    }
    
    /*-------------------------------GENERAR PIEZAS DEL TABLERO-------------------------------*/
    //Este metodo genera todas las piezas que luego se le daran al tablero para que las acomode
    
    public ArrayList<Pieza> generarPiezas () {
        String blanco = "blanco";
        String negro = "negro";
        ArrayList <Pieza> piezasGeneradas = new ArrayList (); //Lista de piezas generadas

        
        /*GENERO LAS REGLAS DE MOVIMIENTO DE LAS PIEZAS*/
        IMovimiento movRey = new MovRey();
        IMovimiento movGeneralOro = new MovGeneralOro();
        IMovimiento movGeneralPlata = new MovGeneralPlata();
        IMovimiento movCaballo = new MovCaballo();
        IMovimiento movLancero = new MovLancero();
        IMovimiento movAlfil = new MovAlfil();
        IMovimiento movTorre = new MovTorre();
        IMovimiento movPeon = new MovPeon();
        
        /*GENÉRO LAS PIEZAS*/
        Pieza rey1 = new Pieza(movRey, "Rey 1", "REY", blanco, 1, 5, false);
        Pieza rey2 = new Pieza(movRey, "Rey 2", "REY", negro, 9, 5, false);
        Pieza generalOro1 = new Pieza(movGeneralOro, "General de Oro 1", "GDO", blanco, 1, 4, false);
        Pieza generalOro2 = new Pieza(movGeneralOro, "General de Oro 2", "GDO", blanco, 1, 6, false);
        Pieza generalOro3 = new Pieza(movGeneralOro, "General de Oro 3", "GDO", negro, 9, 4, false);
        Pieza generalOro4 = new Pieza(movGeneralOro, "General de Oro 4", "GDO", negro, 9, 6, false);
        Pieza generalPlata1 = new Pieza(movGeneralPlata, "General de Plata 1", "GDP", blanco, 1, 3, true);
        Pieza generalPlata2 = new Pieza(movGeneralPlata, "General de Plata 2", "GDP", blanco, 1, 7, true);
        Pieza generalPlata3 = new Pieza(movGeneralPlata, "General de Plata 3", "GDP", negro, 9, 3, true);
        Pieza generalPlata4 = new Pieza(movGeneralPlata, "General de Plata 4", "GDP", negro, 9, 7, true);
        Pieza caballo1 = new Pieza(movCaballo, "Caballo 1", "CAB", blanco, 1, 2, true);
        Pieza caballo2 = new Pieza(movCaballo, "Caballo 2", "CAB", blanco, 1, 8, true);
        Pieza caballo3 = new Pieza(movCaballo, "Caballo 3", "CAB", negro, 9, 2, true);
        Pieza caballo4 = new Pieza(movCaballo, "Caballo 4", "CAB", negro, 9, 8, true);
        Pieza lancero1 = new Pieza(movLancero, "Lancero 1", "LAN", blanco, 1, 1, true);
        Pieza lancero2 = new Pieza(movLancero, "Lancero 2", "LAN", blanco, 1, 9, true);
        Pieza lancero3 = new Pieza(movLancero, "Lancero 3", "LAN", negro, 9, 1, true);
        Pieza lancero4 = new Pieza(movLancero, "Lancero 4", "LAN", negro, 9, 9, true);
        Pieza alfil1 = new Pieza(movAlfil, "Alfil 1", "ALF", blanco, 2, 8, true);
        Pieza alfil2 = new Pieza(movAlfil, "Alfil 2", "ALF", negro, 8, 2, true);
        Pieza torre1 = new Pieza(movTorre, "Torre 1", "TOR", blanco, 2, 2, true);
        Pieza torre2 = new Pieza(movTorre, "Torre 2", "TOR", negro, 8, 8, true);
        
        Pieza peon1 = new Pieza(movPeon, "Peon 1", "PN1", blanco, 3, 1, true);
        Pieza peon2 = new Pieza(movPeon, "Peon 2", "PN2", blanco, 3, 2, true);
        Pieza peon3 = new Pieza(movPeon, "Peon 3", "PN3", blanco, 3, 3, true);
        Pieza peon4 = new Pieza(movPeon, "Peon 4", "PN4", blanco, 3, 4, true);
        Pieza peon5 = new Pieza(movPeon, "Peon 5", "PN5", blanco, 3, 5, true);
        Pieza peon6 = new Pieza(movPeon, "Peon 6", "PN6", blanco, 3, 6, true);
        Pieza peon7 = new Pieza(movPeon, "Peon 7", "PN7", blanco, 3, 7, true);
        Pieza peon8 = new Pieza(movPeon, "Peon 8", "PN8", blanco, 3, 8, true);
        Pieza peon9 = new Pieza(movPeon, "Peon 9", "PN9", blanco, 3, 9, true);
        Pieza peon10 = new Pieza(movPeon, "Peon 10", "P10", negro, 7, 1, true);
        Pieza peon11 = new Pieza(movPeon, "Peon 11", "P11", negro, 7, 2, true);
        Pieza peon12 = new Pieza(movPeon, "Peon 12", "P12", negro, 7, 3, true);
        Pieza peon13 = new Pieza(movPeon, "Peon 13", "P13", negro, 7, 4, true);
        Pieza peon14 = new Pieza(movPeon, "Peon 14", "P14", negro, 7, 5, true);
        Pieza peon15 = new Pieza(movPeon, "Peon 15", "P15", negro, 7, 6, true);
        Pieza peon16 = new Pieza(movPeon, "Peon 16", "P16", negro, 7, 7, true);
        Pieza peon17 = new Pieza(movPeon, "Peon 17", "P17 ", negro, 7, 8, true);
        Pieza peon18 = new Pieza(movPeon, "Peon 18", "P18", negro, 7, 9, true);
        
        piezasGeneradas.add(rey1);
        piezasGeneradas.add(rey2);
        piezasGeneradas.add(generalOro1);
        piezasGeneradas.add(generalOro2);
        piezasGeneradas.add(generalOro3);
        piezasGeneradas.add(generalOro4);
        piezasGeneradas.add(generalPlata1);
        piezasGeneradas.add(generalPlata2);
        piezasGeneradas.add(generalPlata3);
        piezasGeneradas.add(generalPlata4);
        piezasGeneradas.add(caballo1);
        piezasGeneradas.add(caballo2);
        piezasGeneradas.add(caballo3);
        piezasGeneradas.add(caballo4);
        piezasGeneradas.add(lancero1);
        piezasGeneradas.add(lancero2);
        piezasGeneradas.add(lancero3);
        piezasGeneradas.add(lancero4);
        piezasGeneradas.add(alfil1);
        piezasGeneradas.add(alfil2);
        piezasGeneradas.add(torre1);
        piezasGeneradas.add(torre2);
        
        piezasGeneradas.add(peon1);
        piezasGeneradas.add(peon2);
        piezasGeneradas.add(peon3);
        piezasGeneradas.add(peon4);
        piezasGeneradas.add(peon5);
        piezasGeneradas.add(peon6);
        piezasGeneradas.add(peon7);
        piezasGeneradas.add(peon8);
        piezasGeneradas.add(peon9);
        piezasGeneradas.add(peon10);
        piezasGeneradas.add(peon11);
        piezasGeneradas.add(peon12);
        piezasGeneradas.add(peon13);
        piezasGeneradas.add(peon14);
        piezasGeneradas.add(peon15);
        piezasGeneradas.add(peon16);
        piezasGeneradas.add(peon17);
        piezasGeneradas.add(peon18);
        
        return piezasGeneradas;
    }
}
