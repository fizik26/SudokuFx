/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Enum;
/**
 *
 * @author p1607434
 */
public enum Value {
    ZERO(0),
    UN(1), 
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    HUIT(8),
    NEUF(9);

    int i;
    
    Value(int i) {
        this.i = i;
    }
    
    /**
     *
     * @param i
     * @return
     */
   /* public static Value transformeInt(int i)
    {
        for (Value valeurARetourner : Value.values())
        {
            if (valeurARetourner.getInt() == i)
                return valeurARetourner;
        }
        return null;
    }
    
    public int getInt()
    {
        return this.i;
    }*/
}
