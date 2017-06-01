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
 * En todo momento se espera que el mundo sea cuadrado, no importa el tamaño
 * siempre y cuando esto se cumpla
 */
public class Mundo extends Agent {

    /**
     * @return the estado
     */
    public int[][] getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int[][] estado) {
        this.estado = estado;
    }

    /**
     * @return the humano
     */
    public Jugador getHumano() {
        return humano;
    }

    /**
     * @param humano the humano to set
     */
    public void setHumano(Jugador humano) {
        this.humano = humano;
    }

    /**
     * @return the maquina
     */
    public Jugador getMaquina() {
        return maquina;
    }

    /**
     * @param maquina the maquina to set
     */
    public void setMaquina(Jugador maquina) {
        this.maquina = maquina;
    }

    /**
     * @return the funciones
     */
    public FuncionesGenerales getFunciones() {
        return funciones;
    }

    public void setPosicionHumano(Tuple nuevaPosicionHumano) {
        System.out.println(nuevaPosicionHumano.x + " " + nuevaPosicionHumano.y);
        
        if (this.humano.posicion.x != -1) {
            this.estado[this.humano.posicion.x][this.humano.posicion.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
           
        }

        this.estado[nuevaPosicionHumano.x][nuevaPosicionHumano.y] = EnumEstadoMundo.OCUPADO_MIN;
        this.humano.posicion.x = nuevaPosicionHumano.x;
        this.humano.posicion.y = nuevaPosicionHumano.y;
    }
    
    public void setPosicionMaquina(Tuple nuevaPosicionMaquina) {
         if (this.maquina.posicion.x != -1) {
        this.estado[this.maquina.posicion.x][this.maquina.posicion.y] = EnumEstadoMundo.POSICION_BLOQUEADA;
         }
        this.estado[nuevaPosicionMaquina.x][nuevaPosicionMaquina.y] = EnumEstadoMundo.OCUPADO_MAX;
        this.maquina.posicion.x = nuevaPosicionMaquina.x;
        this.maquina.posicion.y = nuevaPosicionMaquina.y;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(FuncionesGenerales funciones) {
        this.funciones = funciones;
    }
    private int[][] estado;
    private Jugador humano;
    private Jugador maquina;
    private FuncionesGenerales funciones;

    public Mundo(int tamanio) {
        setFunciones(new FuncionesGenerales());
        this.setHumano(new Jugador());
        this.estado = new int[tamanio][tamanio];
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado.length; j++) {
                this.estado[i][j] = EnumEstadoMundo.POSICION_DISPONIBLE;

            }

        }
        this.humano = new Jugador();
        this.maquina = new Jugador();
        this.setMaquina(new Jugador());
    }

    public Mundo(int[][] estado, Tuple posicionMin, Tuple posicionMax) {

        setFunciones(new FuncionesGenerales());
        getFunciones().duplicarArrayValor(estado, this.getEstado());
        this.humano.posicion = new Tuple(posicionMin);
        this.maquina.posicion = new Tuple(posicionMax);

    }

    public void takedown() {

    }

}
