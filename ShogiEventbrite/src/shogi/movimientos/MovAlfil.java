/*
    ESTA PIEZA SOLO SE PODRA MOVER EN DIAGONAL TODOS LOS ESPACIOS QUE PUEDA
 */
package shogi.movimientos;

import shogi.main.IMovimiento;

/**
 *
 * @author Flia_cer
 */
public class MovAlfil implements IMovimiento {

    @Override 
    public boolean movimientoEsValido(String color,int deX, int deY, int aX, int aY) {
        boolean esValido = true;
        
        if (deX == aX && deY == aY) {
            esValido = false;
        }
        else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }
        else if (deX == aX || deY == aY) { //Si el movimiento es HORIZONTAL O VERTICAL entonces NO ES VALIDO
            esValido = false;
        }
        
        return esValido;
    }
    
}
