/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lucho
 */
public class FuncionesGenerales {

    public void printMatrix(int[][] m){
    try{
        int rows = m.length;
        int columns = m[0].length;
        String str = "|\t";

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                str += m[i][j] + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }

    }catch(Exception e){System.out.println("Matrix is empty!!");}
}
    
    public void printArrayListTuples(ArrayList <Tuple> tuplas){
        for(Tuple tupla : tuplas){
            System.out.println("("+String.valueOf(tupla.x)+"," +String.valueOf(tupla.y)+")" );
        }
    }
    
    void duplicarArrayValor(int[][] arrayEntrada, int[][] arrayDestino) {
        int filas, columnas;
        filas = arrayEntrada.length;
        columnas = arrayEntrada.length;

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {
                arrayDestino[i][j] = arrayEntrada[i][j];
            }

        }
    }

    /*
    Chequea que una posicion dada en x,y este dentro de un Mundo cuadrado
     */
    boolean posicionDentroMundo(int[][] estado, Tuple posicion) {

        int tamanio_estado = estado.length;
        return (posicion.x >= 0 && posicion.x < tamanio_estado && posicion.y >= 0 && posicion.y < tamanio_estado);

    }

    boolean posicionValida(int[][] estado, Tuple posicion) {

       if(!(posicionDentroMundo(estado, posicion)))
       {
           return false;
       }
        if(!(estado[posicion.x][posicion.y] == EnumEstadoMundo.POSICION_DISPONIBLE)){
               return false;
       }
       else{
            return true;
        }

    }

    /*
    Funcion que recibe un array de posiciones y retorna un array con 
    las posiciones que sean validas de dicho array, las otras se descartan
     */
    ArrayList<Tuple> posicionesValidas(int[][] estado, ArrayList<Tuple> posiciones) {

        ArrayList<Tuple> posicionesVailidas = new ArrayList();
        Tuple posicionTemporal = new Tuple(-1, -1);

        for (Tuple posicion : posiciones) {
            posicionTemporal = new Tuple(posicion);
            if (posicionValida(estado, posicion)) {
                posicionesVailidas.add(posicionTemporal);
            }

        }
        return posicionesVailidas;
    }

    /*
     _
    |
    |
    |
    
     */
    public Tuple posicionCaballoArribaDerecha(Tuple posicionActual) {
        Tuple arribaDerecha = new Tuple(posicionActual.x + 1, posicionActual.y - 3);
        return arribaDerecha;
    }

    /*
    _ 
     |
     |
     |
    
     */
    public Tuple posicionCaballoArribaIzquierda(Tuple posicionActual) {
        Tuple arribaIzquierda = new Tuple(posicionActual.x - 1, posicionActual.y - 2);
        return arribaIzquierda;
    }

    /*
     
     _ _ _ |
    
     */
    public Tuple posicionCaballoDerechaArriba(Tuple posicionActual) {
        Tuple derechaArriba = new Tuple(posicionActual.x + 2, posicionActual.y - 1);
        return derechaArriba;
    }

    /*
     
     _ _ _ 
          |  
    
     */
    public Tuple posicionCaballoDerechaAbajo(Tuple posicionActual) {
        Tuple derechaAbajo = new Tuple(posicionActual.x + 2, posicionActual.y + 1);
        return derechaAbajo;
    }

    /*
     
    |
    |
    |_
    
     */
    public Tuple posicionCaballoAbajoDerecha(Tuple posicionActual) {
        Tuple abajoDerecha = new Tuple(posicionActual.x + 1, posicionActual.y + 2);
        return abajoDerecha;
    }

    /*
     
     |
     |
    _|
    
     */
    public Tuple posicionCaballoAbajoIzquierda(Tuple posicionActual) {
        Tuple abajoIzquierda = new Tuple(posicionActual.x - 1, posicionActual.y + 2);
        return abajoIzquierda;
    }

    /*
     
    |_ _ _
    
     */
    public Tuple posicionCaballoIzquierdaArriba(Tuple posicionActual) {
        Tuple izquierdaArriba = new Tuple(posicionActual.x - 2, posicionActual.y - 1);
        return izquierdaArriba;
    }

    /*
     
    |_ _ _
    
     */
    public Tuple posicionCaballoIzquierdaAbajo(Tuple posicionActual) {
        Tuple izquierdaAbajo = new Tuple(posicionActual.x - 2, posicionActual.y + 1);
        return izquierdaAbajo;
    }
    
    /*
    Retorna todas las posibles posiciones que puede tomar un caballo, incluso
    las invalidas
    */
    
    public ArrayList<Tuple> posicionesPosiblesCaballo(int[][] estado, Tuple posicionActor) {

        ArrayList<Tuple> posiciones = new ArrayList<>();
        
        posiciones.add(posicionCaballoAbajoDerecha(posicionActor));
        posiciones.add(posicionCaballoAbajoIzquierda(posicionActor));
        posiciones.add(posicionCaballoDerechaAbajo(posicionActor));
        posiciones.add(posicionCaballoDerechaArriba(posicionActor));
        posiciones.add(posicionCaballoIzquierdaAbajo(posicionActor));
        posiciones.add(posicionCaballoIzquierdaArriba(posicionActor));
        posiciones.add(posicionCaballoArribaDerecha(posicionActor));
        posiciones.add(posicionCaballoArribaIzquierda(posicionActor));
        
        return posiciones ;
    }
    /*
    Retorna las posibles posiciones validas de un caballo
    */
    public ArrayList<Tuple> posicionesPosiblesValidasCaballo(int[][] estado, Tuple posicionActor) {
        return posicionesValidas(estado, posicionesPosiblesCaballo(estado, posicionActor));
    }
    
        public int cantidadPosicionesPosiblesValidasCaballo(int[][] estado, Tuple posicionActor) {
        return posicionesValidas(estado, posicionesPosiblesCaballo(estado, posicionActor)).size();
    }

}
