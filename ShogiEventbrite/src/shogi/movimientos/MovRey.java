/*
    EL REY PUEDE MOVERSE SOLO UNA CASILLA PERO DE FORMA HORIZONTAL, VERTICAL O DIAGONAL
 */
package shogi.movimientos;

import shogi.main.IMovimiento;

public class MovRey implements IMovimiento {

    @Override
    public boolean movimientoEsValido(String color, int deX, int deY, int aX, int aY) {
        boolean esValido = false;
        
        //Si el destino de la pieza es el mismo lugar en donde esta 
        if (deX == aX && deY == aY) {
            esValido = false;
        }
        //Si el destino de la pieza sobrepasa los limites del tablero
        else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }
        
        //No importa el color, siempre el rango sera el mismo
        if ("blanco".equals(color) || "negro".equals(color)) {
            if (aX == deX) { //Movimiento horizontal (ya que el eje X permanece quieto)
                if (aY == deY + 1 || aY == deY - 1) {
                    esValido = true;
                }
            }
            else if (aY == deY) { //Movimiento vertical (ya que el eje Y permanece quieto)
                if (aX == deX +1 || aX == deX - 1) { 
                    esValido = true;
                }
            }
            else if (aX == deX + 1 || aX == deX - 1) { //Movimiento diagonal hacia abajo/arriba
                if (aY == deY + 1) { //Movimiento diagonal hacia la derecha
                    esValido = true;
                }
                else if (aY == deY - 1) { //Movimiento diagonal hacia la izquierda
                    esValido = true;
                }
            }
        }
        
        return esValido;
    } 
}
