
package shogi.main;

/**
 *
 * @author Flia_cer
 */
public class Casillero {
    private Pieza miPieza; //Pieza que esta dentro del casillero
    private final int coordenadaX; //Coordenada X del casillero
    private final int coordenadaY; //Coordenada Y del casillero
    private boolean casilleroOcupado; //Si hay una pieza "true" sino "false"

    /*CONSTRUCTOR*/
    public Casillero(int coorX, int coorY) {
        this.coordenadaX = coorX;
        this.coordenadaY = coorY;
    }
    
    /*GETTERS Y SETTERS*/
    public Pieza getPieza () {
        return miPieza;
    }
    
    public void setPieza (Pieza pieza) {
        miPieza = pieza;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }
    
    public int getCoordenadaY() {
        return coordenadaY;
    }
    
    public boolean casilleroEstaOcupado () {
        boolean flag = false;
        
        if (miPieza != null) { //Si hay una pieza devolver "TRUE"
            flag = true;
        }
        
        return flag;
    }
    
    /*METODOS*/
    
    //Este metodo sirve para eliminar una pieza de un casillero cuando es comida
    public Pieza comerPieza () {
        Pieza piezaRemovida = this.miPieza;
        
        this.miPieza = null;
        
        return piezaRemovida; //Esta pieza sera colocada en una lista de piezas removidas
    }
    
    //Este metodo solo desocupa un casillero
    public void desocuparCasillero () {
        this.miPieza = null;
    }
}
