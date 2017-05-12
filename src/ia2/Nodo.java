/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia2;


import java.util.LinkedList;

/**
 *
 * @author lucho
 */
public class Nodo {
    FuncionesGenerales funciones;
    public int [][] estadoActual;
    public Tuple posicionMin;
    public Tuple posicionMax;
    public Tuple mejorMovimientoHijo;
    public int valorHeuristica;
    public Nodo padre;
    public int profundidad;
    public LinkedList<Nodo> hijos = new LinkedList<Nodo>();
    public int jugador;
    
    /*
    Constructor para un nodo sin padre (nodo inicial)
    */
    
    public  Nodo(int[][] estadoActual, Tuple posicionMin, Tuple posicionMax, int valorHeuristica){
       this.funciones=new FuncionesGenerales();
       this.estadoActual = new int[estadoActual.length][estadoActual.length];
       funciones.duplicarArrayValor(estadoActual, this.estadoActual);
       this.posicionMin = new Tuple(posicionMin);
       this.posicionMax = new Tuple(posicionMax);
       this.valorHeuristica = valorHeuristica;
       this.padre = null;
       this.profundidad = 0;
       this.jugador = EnumJugador.MAX; 
       this.mejorMovimientoHijo = new Tuple(-1000, -1000);
    }
    
    /*
    Constructor para un nodo con padre 
    */
    public  Nodo(Nodo padre, int[][] nuevoEstado, Tuple posicionMin, Tuple posicionMax, int valorHeuristica){
       this.funciones=new FuncionesGenerales();
       this.estadoActual = new int[nuevoEstado.length][nuevoEstado.length];
       funciones.duplicarArrayValor(nuevoEstado, this.estadoActual);
       this.posicionMin = new Tuple(posicionMin);
       this.posicionMax = new Tuple(posicionMax);
       this.valorHeuristica = valorHeuristica;
       this.padre = padre;
       this.profundidad = padre.profundidad + 1;
       this.jugador = (padre.jugador == EnumJugador.MAX) ? EnumJugador.MIN: EnumJugador.MAX;
       this.mejorMovimientoHijo = new Tuple(-1000, -1000);
    }
    
    
}
