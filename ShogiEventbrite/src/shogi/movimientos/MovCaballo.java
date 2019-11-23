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
public class MovCaballo implements IMovimiento {

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
            if (aY == deY + 1 || aY == deY - 1 && aX == deX + 2) { //Movimiento en L para BLANCO
                esValido = true;
            }
        }
        else {
            if (aY == deY + 1 || aY == deY - 1 && aX == deX - 2) { //Movimiento en L PARA NEGRO
                esValido = true;
            }
        }
        
        return esValido;
    }
}
