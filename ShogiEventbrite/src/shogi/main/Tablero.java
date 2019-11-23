/*
    ESTA CLASE ES LA ENCARGADA DE ARMAR Y ADMINISTRAR EL TABLERO DE JUEGO. 
    ADEMAS LE BRINDA A LOS JUGADORES LA POSIBILIDAD DE MOVER SUS PIEZAS Y 
    TAMBIEN REINTRODUCIR LAS PIEZAS QUE HAN CAPTURADO DEL ADVERSARIO.
    ES LA CLASE ENCARGADA DE VERIFICAR QUE HAYA UN GANADOR.
 */
package shogi.main;

import java.util.ArrayList;
import java.util.Scanner;
import shogi.movimientos.MovAlfilCoronado;
import shogi.movimientos.MovGeneralOro;
import shogi.movimientos.MovTorreCoronada;

/**
 *
 * @author Flia_cer
 */
public class Tablero {

    private final int tamTablero = 10; //Tamaño del tablero
    private final Casillero[][] tablero = new Casillero[tamTablero][tamTablero]; //Tablero de casilleros
    private Ayudante miAyudante = new Ayudante();
    MovGeneralOro movOroCoronado = new MovGeneralOro();  //Movimiento del general de oro
    MovAlfilCoronado movAlfilCoronado = new MovAlfilCoronado();
    MovTorreCoronada movTorreCoronada = new MovTorreCoronada();
    
    /*CONSTRUCTOR*/
    public Tablero (Ayudante ayudante) {
        this.miAyudante = ayudante; //Le doy un ayudante
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                this.tablero[i][j] = new Casillero(i, j);
            }
        }
    }

    /*GETTERS Y SETTERS*/
    public Casillero getCasillero (int x, int y) {
        return this.tablero[x][y];
    }
    
    /*-------------------------------------METODOS-------------------------------------*/
    
    /*-------------------------------COLOCAR LAS PIEZAS EN EL TABLERO-------------------------------*/
    //Este metodo acomoda todas las piezas en el tablero vacio
    
    public void colocarPiezas() {
        ArrayList <Pieza> piezasTablero = miAyudante.generarPiezas();
        int enX, enY;
        
        for (int i = 0; i < piezasTablero.size(); i++) {
            Pieza pieza = piezasTablero.get(i);
            enX = piezasTablero.get(i).getAcomodarEnX();
            enY = piezasTablero.get(i).getAcomodarEnY();
            
            tablero[enX][enY].setPieza(pieza);
        }
    }
    
    /*-------------------------------MOSTRAR EL TABLERO DE JUEGO-------------------------------*/
    //Este metodo permite mostrar por pantalla el tablero de juego
    
    public void displayTablero() {
        for (int i = 0; i < tamTablero; i++) {
            for (int j = 0; j < tamTablero; j++) {
                if (tablero[i][j].casilleroEstaOcupado() == true) { //Si el casillero esta ocupado mostrar el acronimo de la pieza
                    System.out.printf("[" + tablero[i][j].getPieza().getAcronimoPieza() + "]" + " ");
                } else {
                    if (i == 0) {
                        if (j == 0) {
                            System.out.printf("     " + " ");
                        }
                        else {
                            System.out.printf("[ " + j + " ]" + " ");
                        }
                    }
                    else if (j == 0) {  
                        System.out.printf("[ " + i + " ]" + " ");
                    }
                    else {
                        System.out.printf("[---]" + " ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    /*-------------------------------MOVER UNA PIEZA-------------------------------*/
    //Este metodo permite que los jugadores muevan sus piezas
    
    public boolean moverPieza (Jugador jugador) {
        
        ArrayList <Integer> coordenadasJugador = new ArrayList(); //Lista de coordenadas
        Pieza piezaSeleccionada = null; //Pieza que el jugador selecciono
        boolean movimientoEsValido = false; //Verificar que el movimiento de la pieza seleccionada es valido
        String colorJugador = jugador.getColor(); //Color del jugador
        String colorPiezaSeleccionada = ""; //Color de la pieza seleccionada
        String nombrePiezaSeleccionada = ""; //Nombre de la pieza seleccionada
        String colorPiezaDestino = ""; //Color de la pieza en el casillero destino
        String nombrePiezaDestino = ""; //Nombre de la pieza en el casillero destino
        String acronimoPiezaDestino = ""; //Acronimo de la pieza en el casillero destino
        Casillero casilleroOrigen = null; //Casillero donde esta la pieza
        Casillero casilleroDestino = null; //Casillero donde se quiere mover la pieza
        int deX = 0, deY = 0, aX = 0, aY = 0;
        boolean seguirTirando = true;
        boolean reyComido = false;
        
        do {
            coordenadasJugador = miAyudante.pedirCoordenadas(); //Le pido las coordenadas de movimiento al jugador

            deX = coordenadasJugador.get(0); //Coordenada Origen X
            deY = coordenadasJugador.get(1); //Coordenada Origen Y
            aX = coordenadasJugador.get(2); //Coordenada Destino X
            aY = coordenadasJugador.get(3); //Coordenada Destino Y

            casilleroOrigen = tablero[deX][deY];
            casilleroDestino = tablero[aX][aY];
        
            /*VERIFICACIONES PREVIAS A REALIZAR EL MOVIMIENTO*/
            if (casilleroOrigen.casilleroEstaOcupado() == true) { //El casillero origen NO ESTA VACIO

                piezaSeleccionada = casilleroOrigen.getPieza(); //Seleccionar la pieza de ese casillero
                colorPiezaSeleccionada = piezaSeleccionada.getColorPieza(); //Obtengo el color de esa pieza
                nombrePiezaSeleccionada = piezaSeleccionada.getNombrePieza(); //Obtengo el nombre de esa pieza

                movimientoEsValido = piezaSeleccionada.revisarMovimiento(colorJugador, deX, deY, aX, aY); //Verifico su movimiento

                if (colorPiezaSeleccionada == colorJugador) { //La pieza seleccionada pertenece al jugador
                    if (movimientoEsValido) { //El movimiento que quiere hacer el jugador es valido para la pieza seleccionada
                        if (casilleroDestino.casilleroEstaOcupado() == true) { //El casillero destino esta ocupado

                            colorPiezaDestino = casilleroDestino.getPieza().getColorPieza();

                            if (colorPiezaDestino != colorJugador) { //La pieza del casillero destino es del ADVERSARIO

                                //COMER PIEZA DEL ADVERSARIO
                                nombrePiezaDestino = casilleroDestino.getPieza().getNombrePieza();
                                acronimoPiezaDestino = casilleroDestino.getPieza().getAcronimoPieza();
                                
                                if (acronimoPiezaDestino == "REY") { //Si la pieza comida es el rey entonces HAY GANADOR
                                    reyComido = true;
                                }
                                
                                casilleroOrigen.desocuparCasillero(); //Desocupo el casillero origen

                                jugador.agregarPiezaCapturada(casilleroDestino.comerPieza()); //Agrego la pieza eliminada a las piezas del jugador
                                
                                System.err.println("\n********** HAS COMIDO AL " + nombrePiezaDestino + " DEL ADVERSARIO **********\n");

                                casilleroDestino.setPieza(piezaSeleccionada); //Pongo la pieza seleccionada el el destino
                                
                                seguirTirando = false; 
                            }
                            else { //La pieza del casillero destino ES DEL MISMO JUGADOR
                                System.err.println("El casillero (" + aX + ", " + aY + ") ESTA OCUPADO CON UNA PIEZA TUYA!\n");
                            }
                        }
                        else { //El casillero destino NO ESTA OCUPADO

                            //SOLAMENTE MOVER LA PIEZA SELECCIONADA A ESE CASILLERO
                            casilleroOrigen.desocuparCasillero();

                            casilleroDestino.setPieza(piezaSeleccionada);
                            
                            System.err.println("\n********** HAS MOVIDO A " + nombrePiezaSeleccionada + " DE ("+ deX + ", " + deY + ") A (" + aX + ", " + aY + ") **********\n");
                            
                            seguirTirando = false;
                        }
                    }
                    else { //El movimiento que desea hacer el jugador NO ES VALIDO
                        System.err.println("Ese movimiento NO ES VALIDO PARA ESA PIEZA!\n");
                    }
                }
                else { //La pieza seleccionada NO PERTENECE AL JUGADOR
                    System.err.println("Esa pieza NO ES TUYA!\n");
                }
            }
            else { //El casillero seleccionado ESTA VACIO
                System.err.println("No hay ninguna pieza en el casillero (" + deX + "," + deY + ")\n");
            }
        } while (seguirTirando);
        
        return reyComido;
    }
    
    /*-------------------------------REINTRODUCIR UNA PIEZA-------------------------------*/
    //Este metodo permite volver a colocar una pieza capturada adentro del tablero
    //Devuelve un true si el jugador ha reintroducido una pieza y un false si no ha capturado ningua
    //Si se devuelve un true el jugador ya no puede seguir su turno, sino puede seguir
    
    public boolean reintroducirPieza (Jugador jugador) {
        Pieza piezaAReintroducir = null;
        Scanner leerCoordeandas = new Scanner(System.in);
        int enX, enY;
        boolean seguirIntentando = true;
        boolean exito = true;
        
        //El jugador elige la pieza que desea reintroducir
        piezaAReintroducir = miAyudante.elegirPiezaCapturada(jugador);
        
        if (piezaAReintroducir == null) { //Si el jugador todavia no ha capturado ninguna pieza
            System.err.println("Todavia no has capturado ningua pieza!");
            
            exito = false;
        }
        else {
            //Cambio el color de la pieza capturada al color del jugador
            piezaAReintroducir.setColorPieza(jugador.getColor());
            
            do {
                //Coordenadas en donde el jugador quiere colocar la pieza
                System.out.println("\nColocar en (FILA):");
                enX = leerCoordeandas.nextInt();
                System.out.println("Colocar en (COLUMNA):");
                enY = leerCoordeandas.nextInt();
                
                if (tablero[enX][enY].casilleroEstaOcupado() == true) { //Si el casillero seleccionado esta ocupado
                    System.out.println("Ese casillero esta ocupado, vuelve a intentar con otro!");
                    
                    exito = false;
                }
                else { //Si el casillero seleccionado esta VACIO, colocar la pieza en el
                    tablero[enX][enY].setPieza(piezaAReintroducir);
                    
                    System.err.println("\nPIEZA REINTRODUCIDA EN (" + enX + ", " + enY + ")\n");
                    
                    seguirIntentando = false;
                }
                
            } while (seguirIntentando);
        }
        
        return exito;
    }
    
    /*-------------------------------CORONAR UNA PIEZA-------------------------------*/
    //Este metodo verifica que las piezas que llegen a su ZONA DE PROMOCION sean coronadas
    //Va a ser un proceso automatico y no depende del jugador
    
    public void coronarPieza () {
        Pieza pieza;
        String colorPieza = "";
        String acronimoPieza = "";
        
        //DESDE LA FILA 1 A LA 3 -> ZONA DE CORONACION PARA LAS PIEZAS NEGRAS (porque es la zona de las blancas)
        for (int i = 1; i < 4; i++) { 
            for (int j = 0; j < tamTablero; j++) {
                
                if (tablero[i][j].casilleroEstaOcupado() == true) { //El casillero debe estar lleno
                    
                    pieza = tablero[i][j].getPieza(); //Pieza en el casillero i, j
                    colorPieza = pieza.getColorPieza();
                    acronimoPieza = pieza.getAcronimoPieza();
                    
                    if (pieza.estaCoronada() == false) { //Si la pieza NO HA SIDO CORONADA
                        if (pieza.puedeCoronarse() && colorPieza == "negro") { //La pieza debe poder coronarse y deber ser blanca
                            switch (acronimoPieza) {
                                case "ALF": //LA PIEZA ES UN ALFIL
                                    //Set movimiento alfil coronado
                                    pieza.setMiMovimiento(movAlfilCoronado);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    break;
                                case "TOR": //LA PIEZA ES UNA TORRE
                                    //Set movimiento torre coronada
                                    pieza.setMiMovimiento(movTorreCoronada);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    break;
                                default: //CUALQUIER OTRA QUE NO SEA ALFIL O TORRE DEBE AHORA MOVERSE COMO UN GENERAL DE ORO
                                    pieza.setMiMovimiento(movOroCoronado);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    break;
                            }
                            
                            System.out.println("************* LA PIEZA " + pieza.getNombrePieza() + " HA SIDO CORONADA *************\n");
                        }
                    }    
                }
            
            }
            
            pieza = null;
        }
        
        //DESDE LA FILA 7 A LA 9 -> ZONA DE CORONACION PARA LAS PIEZAS BLANCAS (porque es la zona de las negras)
        for (int i = 7; i < tamTablero; i++) { 
            for (int j = 0; j < tamTablero; j++) {
                
                if (tablero[i][j].casilleroEstaOcupado() == true) {
                    
                    pieza = tablero[i][j].getPieza(); //Pieza en el casillero i, j
                    colorPieza = pieza.getColorPieza();
                    acronimoPieza = pieza.getAcronimoPieza();
                    
                    if (pieza.estaCoronada() == false) { //Si la pieza NO HA SIDO CORONADA
                        if (pieza.puedeCoronarse() && colorPieza == "blanco") { //La pieza debe poder coronarse y deber ser blanca
                            switch (acronimoPieza) {
                                case "ALF": //LA PIEZA ES UN ALFIL
                                    //Set movimiento alfil coronado
                                    pieza.setMiMovimiento(movAlfilCoronado);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    System.err.println(pieza.getNombrePieza() + " HA SIDO CORONADA!");
                                    break;
                                case "TOR": //LA PIEZA ES UNA TORRE
                                    //Set movimiento torre coronada
                                    pieza.setMiMovimiento(movTorreCoronada);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    System.err.println(pieza.getNombrePieza() + " HA SIDO CORONADA!");
                                    break;
                                default: //CUALQUIER OTRA QUE NO SEA ALFIL O TORRE DEBE AHORA MOVERSE COMO UN GENERAL DE ORO
                                    pieza.setMiMovimiento(movOroCoronado);
                                    pieza.setEstaCoronada(true); //Corono la pieza
                                    System.err.println(pieza.getNombrePieza() + " HA SIDO CORONADA!");
                                    break;
                            }

                        }
                    }
                }
            }
            
            pieza = null;
        }
    }
}
