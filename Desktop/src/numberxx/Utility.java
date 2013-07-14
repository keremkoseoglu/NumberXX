/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package numberxx;

/**
 *
 * @author kerem
 */

import java.util.ArrayList;

public class Utility {

    public static String[] splitPhraseIntoWords(String S) {
        ArrayList a = new ArrayList();
        String[] ret;
        
        String word = "";
        for (int n = 0; n < S.length(); n++) {
            String letter = S.substring(n, n + 1);
            
            if (letter.equals(" ")) {
                a.add(word);
                word = "";
            }
            else {
                word += letter;
            }
        }
        
        if (word.length() > 0) a.add(word);
        
        ret = new String[a.size()];
        for (int n = 0; n < a.size(); n++) {
            ret[n] = (String) a.get(n);
        }
        
        return ret;
    }
    
    public static String trimPhrase(String S) {
        String ret = "";
        for (int n = 0; n < S.length(); n++) {
            if (!S.substring(n, n + 1).equals(" ")) ret += S.substring(n, n + 1);
        }
        return ret;
    }
    
    public static boolean numberHasZeroWithin(int N) {
        return String.valueOf(N).indexOf("0") >= 0;
    }
    
    public static int reverseNumber(int N) {
        String s = String.valueOf(N);
        String rev = "";
        for (int n = s.length() - 1; n >= 0; n--) {
            rev += s.substring(n, n + 1);
        }
        
        return Integer.valueOf(rev);
    }
    
}
