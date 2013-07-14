package kko.pet;

public class AlphabeticalOrder {

    private int number;
    private String text;
    private final String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public AlphabeticalOrder(String S) {
        text = "";
        number = 0;
        
        for (int n = 0; n < S.length(); n++) {
            String letter = S.substring(n, n + 1);
            if (n > 0) text += " +";
            text += " " + letter;
            number += all.indexOf(letter) + 1;
        }
        
        text += " = " + String.valueOf(number);
    }
    
    public int getNumber() { return number; }
    public String getText() { return text; }
        
    
}