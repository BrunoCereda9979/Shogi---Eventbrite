/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shogi.main;

/**
 *
 * @author Flia_cer
 */
public class Pieza {
    private IMovimiento miMovimiento; //Esta es la regla de movimiento de la pieza
    private String miColor; //Color de la pieza
    private String nombrePieza;
    private String acronimoPieza;
    private final int colocarEnX; //Donde acomodar la pieza al principio del juego
    private final int colocarEnY;
    private final boolean puedeSerCoronada; //Indica si la pieza puede o no ser coronada
    private boolean estaCoronada = false; //Indica si la pieza YA ESTA CORONADA
    
    /*-------------CONSTRUCTOR-------------*/
    public Pieza(IMovimiento movimiento, String nombre, String acronimo, String color, int acomodarX, int acomodarY, boolean coronada) {
        this.miMovimiento = movimiento;
        this.nombrePieza = nombre;
        this.acronimoPieza = acronimo;
        this.miColor = color;
        this.colocarEnX = acomodarX;
        this.colocarEnY = acomodarY;
        this.puedeSerCoronada = coronada;
    }

    /*-------------GETTERS Y SETTERS-------------*/
    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombre) {
        this.nombrePieza = nombre;
    }

    public String getAcronimoPieza() {
        return acronimoPieza;
    }

    public void setAcronimoPieza(String acronimo) {
        this.acronimoPieza = acronimo;
    }

    public IMovimiento getMiMovimiento() {
        return miMovimiento;
    }

    public void setMiMovimiento(IMovimiento movimiento) {
        miMovimiento = movimiento;
    }

    public String getColorPieza() {
        return miColor;
    }
    
    public void setColorPieza (String color) {
        miColor = color;
    }
    
    public int getAcomodarEnX() {
        return colocarEnX;
    }

    public int getAcomodarEnY() {
        return colocarEnY;
    }
    
    public boolean puedeCoronarse () {
        return puedeSerCoronada;
    }

    public boolean estaCoronada() {
        return estaCoronada;
    }

    public void setEstaCoronada (boolean estaCoronada) {
        this.estaCoronada = estaCoronada;
    }
    
    
    /*-------------METODOS-------------*/
    
    /*-------------REVISAR MOVIMIENTO-------------*/
    /*Este metodo ejecuta la regla de movimiento de esta pieza*/
    
    public boolean revisarMovimiento(String miColor, int deX, int deY, int aX, int aY){
        boolean flagExitoso;
        
        flagExitoso = miMovimiento.movimientoEsValido(miColor, deX, deY, aX, aY);
        
        return flagExitoso;
    }
}