/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package numberxx;

/**
 * Solution to the number problem
 * @author kerem
 */

import java.util.ArrayList;

public class Solution {

    public enum FORMAT {H1, H2, NORMAL};
    
    private ArrayList list;
    
    public Solution() {
        clear();
    }
    
    public void clear() {
        list = new ArrayList();
    }
    
    public void appendLine(String Line) {
        appendLine(Line, FORMAT.NORMAL);
    }
    
    public void appendLine(String Line, FORMAT F) {
        if (F == FORMAT.H1) {
            list.add("======================");
            list.add(Line);
            list.add("======================");
            return;
        }

        if (F == FORMAT.H2) {
            list.add(Line + ".......");
            return;
        }        
        
        if (F == FORMAT.NORMAL) {
            list.add(Line);
            return;
        }              
        
    }    
    
    public String[] getSolution() {
        String ret[] = new String[list.size()];
        for (int n = 0; n < list.size(); n++) {
            ret[n] = (String) list.get(n);
        }
        
        return ret;
    }
    
}
