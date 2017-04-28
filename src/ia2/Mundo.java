/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia2;
import jade.core.Agent;

/**
 *
 * @author lucho
 * 
 * En todo momento se espera que el mundo sea cuadrado, no importa el tamaño siempre
 * y cuando esto se cumpla
 */
public class Mundo extends Agent{
    int [][] estado ;
    Jugador humano;
    Jugador maquina;
    FuncionesGenerales funciones;
    
    public  void Mundo(int[][] estado, Tuple posicionMin, Tuple posicionMax){
        
        funciones = new FuncionesGenerales();
        funciones.duplicarArrayValor(estado, this.estado);
        this.humano.posicion = new Tuple(posicionMin);
        this.maquina.posicion = new Tuple(posicionMax);
        
    }
    
    public void takedown(){
        
    }
    
}
