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
public class MovAlfilCoronado implements IMovimiento {

    @Override
    public boolean movimientoEsValido(String color, int deX, int deY, int aX, int aY) {
        
        boolean esValido = true;
        
        if (deX == aX && deY == aY) {
            esValido = false;
        }
        else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }
        //Si el movimiento NO ES DIAGONAL O SOLAMENTE UN CASILLERO VERTICAL U HORIZONTAL ENTONCES no es valido
        else if ( (deX == aX || deY == aY) || (deX == aX + 2 || deY == deY +2) ) { 
            esValido = false;
        }
        
        return esValido;
    }
}
