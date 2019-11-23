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
public class MovTorre implements IMovimiento {

    @Override
    public boolean movimientoEsValido(String color,int deX, int deY, int aX, int aY) {
        boolean esValido = false;
        
        if (deX == aX && deY == aY) {
            esValido = false;
        }
        else if (deX < 0 || deX > 9 || deY < 0 || deY > 9 || aX < 0 || aX > 9 || aY < 0 || aY > 9) {
            esValido = false;
        }

        if (deX == aX || deY == aY) { //Si el movimiento es horizontal o vertical ES VALIDO
            esValido = true;
        }
        
        return esValido;
    }
    
}
