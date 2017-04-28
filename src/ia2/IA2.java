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
        Tuple posicionMax = new Tuple(4,4);
        int[][] mundo = 
        {
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0}};
        funciones.printArrayListTuples(funciones.posicionesPosiblesValidasCaballo(mundo, posicionMax));
        System.out.println(funciones.posicionValida(mundo, posicionMax));
        
        
    }
    
}
