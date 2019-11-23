/*

 */
package shogi.movimientos;

import shogi.main.IMovimiento;

/**
 *
 * @author Flia_cer
 */
public class MovTorreCoronada implements IMovimiento {

    @Override
    public boolean movimientoEsValido(String color, int deX, int deY, int aX, int aY) {
        boolean esValido = false;

        if (deX == aX && deY == aY) {
            esValido = false;
        } else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }

        if (deX == aX || deY == aY) { //Si el movimiento es horizontal o vertical ES VALIDO
            esValido = true;
        }
        else if (aX == deX + 1) { //Movimiento diagonal hacia abajo de a una casilla
            if (aY == deY + 1) {
                esValido = true;
            }
            else if (aY == deY - 1) {
                esValido = true;
            }
        }
        else if (aX == deX - 1) { ////Movimiento diagonal hacia arriba de a una casilla
            if (aY == deY + 1) {
                esValido = true;
            }
            else if (aY == deY - 1) {
                esValido = true;
            }
        }
        
        return esValido;
    }
}
