/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 * EN TODO CASO SE SUPONE QUE A MENOR LA HEURISTICA MEJOR ES ESTA


 */
package ia2;

import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author lucho
 */
public class MinMax {

    public Nodo nodoInicial;
    public int nivelMaximoProfundidad;
    public FuncionesGenerales funciones;

    public MinMax(int[][] estado, Tuple posicionMin, Tuple posicionMax, int profundidadMaxima) {
        this.funciones = new FuncionesGenerales();
        this.nodoInicial = new Nodo(estado, posicionMin, posicionMax, 1000);
        this.nodoInicial.valorHeuristica = heuristica1(this.nodoInicial);
        this.nivelMaximoProfundidad = profundidadMaxima;
    }
    
    /**
     * Funcion que dice si el nodo actual ya ha sido recorrido hacia arriba
     * por minmax
     * @param nodo el nodo a preguntar
     * @return si el nodo ha sido recorrido o no basado en su mejorMovimientoHijo
     */
    
    public boolean nodoSubido(Nodo nodo){
        if(nodo.mejorMovimientoHijo.x == -1000 && nodo.mejorMovimientoHijo.y == -1000){
            return false;
        }
        return true;
    }
    
    public void subirRecursivo(Nodo nodo){
        if(nodoSubido(nodo.padre)){
            return;
        }
        else{
            nodo.padre.mejorMovimientoHijo = nodo.padre.hijos.getFirst().posicionMax;
            nodo.padre.valorHeuristica = nodo.padre.hijos.getFirst().valorHeuristica;
        }
    }
    
    public void bajarRecursivo(Nodo nodo){
        
        if(nodo.hijos.size() == 0){
            subirRecursivo(nodo);
        }
        else{
            for(Nodo nodoTmp:nodo.hijos){
                bajarRecursivo(nodoTmp);
            }
        }
        
    }
    
    public void expandirRecursivo(Nodo nodo) {

        if (esMeta(nodo)) {
            return ;
        }
        
        if(nodo.profundidad > (this.nivelMaximoProfundidad - 1)){
            return;
        }
        
        ArrayList<Tuple> posicionesDisponiblesJugador = new ArrayList<>();
        Nodo nodoTmp;

        if (nodo.jugador == EnumJugador.MAX) {
            posicionesDisponiblesJugador = funciones.posicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMax);
            for (Tuple posicionDisponible : posicionesDisponiblesJugador) {
                nodoTmp = suponerMoverMax(nodo, posicionDisponible);
                nodoTmp.profundidad = nodo.profundidad + 1;
                nodoTmp.valorHeuristica = heuristica1(nodoTmp);
                nodoTmp.padre = nodo;
                nodoTmp.jugador = (nodo.jugador == EnumJugador.MAX) ? EnumJugador.MIN: EnumJugador.MAX;
                funciones.insersionOrdenadaArrayTuplasPorHeuristica(nodo.hijos, nodoTmp);
            }
        }
        if (nodo.jugador == EnumJugador.MIN) {
            posicionesDisponiblesJugador = funciones.posicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMin);
            for (Tuple posicionDisponible : posicionesDisponiblesJugador) {
                nodoTmp = suponerMoverMin(nodo, posicionDisponible);
                nodoTmp.profundidad = nodo.profundidad + 1;
                nodoTmp.valorHeuristica = heuristica1(nodoTmp);
                nodoTmp.padre = nodo;
                nodoTmp.jugador = (nodo.jugador == EnumJugador.MAX) ? EnumJugador.MIN: EnumJugador.MAX;
                funciones.insersionOrdenadaArrayTuplasPorHeuristica(nodo.hijos, nodoTmp);
            }
        }
        for(Nodo hijo: nodo.hijos){
            expandirRecursivo(hijo);
        }
    }

    /*
    Esta funcion que retorna si el nodo es meta o no
     */
    public boolean esMeta(Nodo nodo) {
        if (funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMax) == 0) {
            return true;
        }
        if (funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMin) == 0) {
            return true;
        } else {
            return false;
        }
    }


    /*
    Funcion que retorna el ganador de una partida, recibiendo un nodo
    que ya fue seleccionado como meta, lo asume, si no es meta y nadie ha ganado
    retorna -1
     */
    public int ganador(Nodo nodo) {
        if (funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMax) == 0) {
            return EnumJugador.MIN;
        }
        if (funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMin) == 0) {
            return EnumJugador.MAX;
        } else {
            return -1;
        }
    }

    /*
    Supone que mueve el jugador min a una posicion nueva en un nodo dado,
    siempre y cuando esta sea valida y bloquea en la que estaba si se puede
    mover a la posicion dada, retorna un nuevo nodo resultante luego de mover
    si el movimiento no era posible retorna el mismo nodo, solo altera el estado
     */
    public Nodo suponerMoverMin(Nodo nodo, Tuple posicionNueva) {

        Nodo nuevoNodo = new Nodo(nodo.padre, nodo.estadoActual, nodo.posicionMin, nodo.posicionMax, nodo.valorHeuristica);

        if (funciones.posicionValida(nodo.estadoActual, posicionNueva)) {
            nuevoNodo.estadoActual[nodo.posicionMin.x][nodo.posicionMin.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
            nuevoNodo.estadoActual[posicionNueva.x][posicionNueva.y] = EnumEstadoMundo.OCUPADO_MIN;
            nuevoNodo.posicionMin.x = posicionNueva.x;
            nuevoNodo.posicionMin.y = posicionNueva.y;
            return nuevoNodo;
        }
        return nuevoNodo;
    }

    /*
    Supone que mueve el jugador max a una posicion nueva en un nodo dado,
    siempre y cuando esta sea valida y bloquea en la que estaba si se puede
    mover a la posicion dada
     */
    public Nodo suponerMoverMax(Nodo nodo, Tuple posicionNueva) {

        Nodo nuevoNodo = new Nodo( nodo.estadoActual, nodo.posicionMin, nodo.posicionMax, nodo.valorHeuristica);

        if (funciones.posicionValida(nodo.estadoActual, posicionNueva)) {
            nuevoNodo.estadoActual[nodo.posicionMax.x][nodo.posicionMax.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
            nuevoNodo.estadoActual[posicionNueva.x][posicionNueva.y] = EnumEstadoMundo.OCUPADO_MAX;
            nuevoNodo.posicionMax.x = posicionNueva.x;
            nuevoNodo.posicionMax.y = posicionNueva.y;
            return nuevoNodo;
        }
        return nuevoNodo;
    }

    public int heuristica1(Nodo nodo) {

        return funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMin)
                - funciones.cantidadPosicionesPosiblesValidasCaballo(nodo.estadoActual, nodo.posicionMax);
    }

    public Tuple hallarMejorJugada() {
        Tuple mejorJugada = new Tuple(0, 0);
        return mejorJugada;
    }
}
