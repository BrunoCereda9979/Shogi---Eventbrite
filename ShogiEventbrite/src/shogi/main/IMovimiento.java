/*
   ESTA INTERFAZ CONTIENE EL METODO QUE VERIFICARA EL MOVIMIENTO DEL JUGADOR 
   PARA SABER SI ES VALIDO, SE NECESITA SABER EL COLOR DE LA PIEZA PARA SABER SI SE TIENE
   QUE MOVER SUMANDO (en caso de que la pieza este en las filas 0..2 -> "blanco") O SI SE TIENE QUE
   MOVER RESTANDO (en caso de que la pieza este en las filas 6..8 -> "negro") 

   LAS PIEZAS blancas ESTARAN UBICADAS EN LAS FILAS 0, 1 y 2 Y LAS PIEZAS negras ESTARAN
   UBICADAS EN LAS FILAS 6, 7 y 8.
 */
package shogi.main;

/**
 *
 * @author Flia_cer
 */
public interface IMovimiento {

    /*Este metodo verifica que el movimiento que quiere hacer el usuario de una pieza es valido*/
    public boolean movimientoEsValido(String color, int deX, int deY, int aX, int aY);
}
