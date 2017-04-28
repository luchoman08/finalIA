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
public class Tuple {
    public int x;
    public int y;
    
    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Tuple(Tuple origen){
        this.x = origen.x;
        this.y = origen.y;
    }
}
