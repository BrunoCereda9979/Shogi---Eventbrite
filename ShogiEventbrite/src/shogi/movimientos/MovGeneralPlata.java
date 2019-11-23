/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shogi.movimientos;

import shogi.main.IMovimiento;

/**
 *
 * @author Flia_cer
 */
public class MovGeneralPlata implements IMovimiento {

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
        
        if ("blanco".equals(color)) { //Si la pieza es BLANCA
            if (aY == deY) { //Movimiento vertical 
                if (aX == deX + 1) { //Un casillero hacia abajo
                    esValido = true;
                }
            }
            else if (aX == deX + 1 || aX == deX - 1) { //Movimiento diagonal abajo/arriba
                if (aY == deY + 1) { //Movimiento diagonal hacia la derecha
                    esValido = true;
                }
                else if (aY == deY - 1) { //Movimiento diagonal hacia la izquierda
                    esValido = true;
                }
            }
        }
        else { //Si la pieza es NEGRA
            if (aY == deY) { //Movimiento vertical 
                if (aX == deX - 1) { //Un casillero hacia abajo
                    esValido = true;
                }
            }
            else if (aX == deX + 1 || aX == deX - 1) { //Movimiento diagonal abajo/arriba
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