/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Observable;
/**
 *
 * @author p1607434
 */

public class Modele extends Observable {

    double lastValue;
    boolean err = false;

   public boolean getErr() {
        return err;
    }
    
    public double getValue() {
        return lastValue;
    }
}