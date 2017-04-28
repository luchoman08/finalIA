/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia2;

/**
 *
 * @author lucho
 */
public class MinMax {
    public Nodo nodoInicial;
    public int nivelMaximoProfundidad;
    public FuncionesGenerales funciones;
    
    public MinMax(int[][] estado, Tuple posicionMin, Tuple posicionMax){
        this.funciones = new FuncionesGenerales();
        this.nodoInicial = new Nodo(estado, posicionMin,posicionMax, 1000);
        this.nodoInicial.valorHeuristica = heuristica1(this.nodoInicial);
    }
    
    public void expandir(){
        
    }
    
    /*
    Supone que mueve el jugador min a una posicion nueva en un nodo dado,
    siempre y cuando esta sea valida y bloquea en la que estaba si se puede
    mover a la posicion dada
    */
    
    public boolean suponerMoverMin(Nodo nodo, Tuple posicionNueva){
        if(funciones.posicionValida(nodo.estadoActual, posicionNueva)){
            nodo.estadoActual[nodo.posicionMin.x][nodo.posicionMin.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
            nodo.estadoActual[posicionNueva.x][posicionNueva.y] = EnumEstadoMundo.OCUPADO_MIN;
            return true;
        }
        return false;
    }
    
    /*
    Supone que mueve el jugador max a una posicion nueva en un nodo dado,
    siempre y cuando esta sea valida y bloquea en la que estaba si se puede
    mover a la posicion dada
    */
    
    public boolean suponerMoverMax(Nodo nodo, Tuple posicionNueva){
        if(funciones.posicionValida(nodo.estadoActual, posicionNueva)){
            nodo.estadoActual[nodo.posicionMax.x][nodo.posicionMax.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
            nodo.estadoActual[posicionNueva.x][posicionNueva.y] = EnumEstadoMundo.OCUPADO_MAX;
            return true;
        }
        return false;
    }
    
    public int heuristica1(Nodo nodo){
        
        return funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMax) 
                -
               funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMin);
    }
    
    public Tuple hallarMejorJugada(){
        Tuple mejorJugada = new Tuple(0, 0);
        return mejorJugada;
    }
}
