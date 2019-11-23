/*
    ESTA CLASE REPRESENTA A UN JUGADOR, ESTE TENDRA UN NOMBRE QUE PODRA ELEGIR, 
    UN COLOR QUE SE LE DARA ALEATORIAMENTE Y UNA LISTA CON TODAS LAS PIEZAS 
    QUE HA CAPTURADO DEL ADVERSARIO
 */
package shogi.main;

import java.util.ArrayList;

/**
 *
 * @author Flia_cer
 */
public class Jugador {
    private String nombreJugador;
    private String colorJugador;
    private final ArrayList <Pieza> piezasCapturadas = new ArrayList ();
    
    public String getNombre() {
        return nombreJugador;
    }
    
    public void setNombre (String nombre) {
        this.nombreJugador = nombre;
    }
    
    public String getColor() {
        return colorJugador;
    }
    
    public void setColor (String color) {
        this.colorJugador = color;
    }

    public ArrayList<Pieza> getPiezasCapturadas() {
        return piezasCapturadas;
    }
    
    public void agregarPiezaCapturada (Pieza pieza) {
        this.piezasCapturadas.add(pieza);
    }
}