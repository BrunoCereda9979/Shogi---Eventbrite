/*
   UN PEON SOLO PUEDE MOVERSE UN CASILLERO HACIA ADELANTE, POR LO TANTO
   SOLO PUDE MOVERSE EN EL EJE X, el eje Y permanecera estático.

          Y
      [-][o][-]                    [-][-][-]
    X [-][P][-] --> "negro"        [-][P][-] --> "blanco"
      [-][-][-]                    [-][o][-]  

    X = X - 1 e Y = Y -> En caso de que la pieza sea negra
    X = X + 1 e Y = Y -> En caso de que la pieza sea blanca
 */
package shogi.movimientos;

import shogi.main.IMovimiento;

/**
 * @author Flia_cer
 */
public class MovPeon implements IMovimiento {

    @Override
    public boolean movimientoEsValido(String color, int deX, int deY, int aX, int aY) {
        boolean esValido = false;
        
        if (deX == aX && deY == aY) {
            esValido = false;
        }
        else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }
        
        if ("blanco".equals(color)) {
            if (aX == deX + 1 && deY == aY) {
                esValido = true;
            }
        }
        else {
            if (aX == deX - 1 && deY == aY) {
                esValido = true;
            }
        }
        
        return esValido;
    }   
}