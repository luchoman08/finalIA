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
import ia2.Nodo;

public class IA2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FuncionesGenerales funciones;
        // TODO code application logic here
        funciones = new FuncionesGenerales();
        System.out.println("hola mundo");
        Tuple posicionMax = new Tuple(0,0);
        Tuple posicionMin = new Tuple(7,7);
        Tuple moverA = new Tuple(2,1);
        int[][] mundo = 
        {
        {2,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,1}};
        MinMax minMax = new MinMax(mundo, posicionMax, posicionMax,8);
        Nodo nodo = new Nodo(mundo, posicionMin, posicionMax, 85);
        funciones.printMatrix(mundo);
          System.out.println("----------- ");
        /*
        funciones.printArrayListTuples(funciones.posicionesPosiblesCaballo(mundo, posicionMax));
        System.out.println(funciones.cantidadPosicionesPosiblesValidasCaballo(mundo, posicionMax));
        funciones.printMatrix(funciones.estadoMarcadoPosibilidades(mundo, posicionMax));
        System.out.println("--------------");
        funciones.printMatrix(minMax.suponerMoverMax(nodo, moverA).estadoActual);
        Nodo movimiento1 = minMax.suponerMoverMax(nodo, moverA);
        movimiento1.posicionMax = moverA;
        System.out.println("--------------");
        funciones.printMatrix(minMax.suponerMoverMax(movimiento1, funciones.posicionCaballoAbajoDerecha(moverA)).estadoActual);
        */
        
        minMax.expandirRecursivo(nodo);
        
     //   funciones.printEstadoNodosRecursivo(nodo);
    }
    
}
