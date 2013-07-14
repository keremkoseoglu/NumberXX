/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package numberxx;

/**
 * Investigation thread
 * @author kerem
 */

import java.awt.Event;

public class InvestigationThread implements Runnable {

    private Thread investigator;
    private Solution solution;
    private String phrase;
    private int number;
    private boolean solved;
    
    private int numberRecursion;
    
    public Event threadOverEvent;
    
    public InvestigationThread() {
        investigator = new Thread(this, "Investigator");
        investigator.start();
    }
    
    public void run() {
        clearSolution();
        phrase = Main.form1.getPhrase();
        number = Main.form1.getNumber();
        analyseString(phrase);
        Main.form1.threadOver(getSolution());
    }
    
    private void clearSolution() {
        solution = new Solution();
        solved = false;
    }
    
    public Solution getSolution() {
        return solution;
    }

    private void analyseString(String S) {
        
        String trim = Utility.trimPhrase(S);
        
        // Phrase
        solution.appendLine("Analysing " + S, Solution.FORMAT.H1);
        
        // Check length of string
        solution.appendLine(trim + " has " + String.valueOf(trim.length()) + " characters", Solution.FORMAT.H2);
        //solution.appendLine("Checking phrase length", Solution.FORMAT.H2);
        analyseNumber(trim.length());
        if (solved) return;
        
        // Check alphabetical orders of letters
        solution.appendLine("Checking alphabetical values", Solution.FORMAT.H2);
        AlphabeticalOrder ao = new AlphabeticalOrder(trim);
        solution.appendLine(ao.getText());
        analyseNumber(ao.getNumber());
        if (solved) return;
        
        // Analyse each word in the phrase separately
        String[] words = Utility.splitPhraseIntoWords(S);
        if (words != null && words.length > 1) {
            for (int n = 0; n < words.length; n++) {
                analyseString(words[n]);
                if (solved) return;
            }
        }
        
        // No luck so far?
        if (S.equals(phrase) && !solved) {
            solution.appendLine("Sorry, no relation found!", Solution.FORMAT.H1);
        }
    }
    
    private void analyseNumber(int N) {
        analyseNumber(N, true, true);
    }
    
    private void analyseNumber(int N, boolean CheckDigitSumAndMultiplication, boolean CheckReverse) {
        
        numberRecursion++;
        String spaceb4 = "";
        for (int n = 0; n < numberRecursion; n++) spaceb4 += ".";
        
        solution.appendLine(spaceb4 + "Checking number " + String.valueOf(N));
        
        // Check number itself
        if (N == number) {
            solution.appendLine(spaceb4 + "There you go! " + String.valueOf(number));
            solved = true;
            return;
        } 
        
        // Can the number be divided successfully?
        if (N < number && N > 9) {
            if (N != 0 && number % N == 0) {
                solution.appendLine(spaceb4 + String.valueOf(N) + " multiplied by " + String.valueOf(number / N) + " equals " + String.valueOf(number));
                solved = true;
                return;
            }
        }
        else {
            if (number != 0 && N % number == 0) {
                solution.appendLine(spaceb4 + String.valueOf(N) + " is " + String.valueOf(number) + " multiplied by " + String.valueOf(N / number));
                solved = true;
                return;
            }            
        }
        
        // If number has more than 1 digit, check sum
        if (CheckDigitSumAndMultiplication && N > 9) {
            String ns = String.valueOf(N);
            String xplain = "";
            int digitSum = 0;
            for (int n = 0; n < ns.length(); n++) {
                String letter = ns.substring(n, n + 1);
                digitSum += Integer.parseInt(letter);
                if (n > 0) xplain += " +";
                xplain += " " + letter;
            }
            solution.appendLine(spaceb4 + xplain + " = " + String.valueOf(digitSum));
            analyseNumber(digitSum);
            if (solved) return;
        }
                    
        // If number has more than 1 digit, check multiplication
        if (CheckDigitSumAndMultiplication && N > 9 && !Utility.numberHasZeroWithin(N)) {
            String ns = String.valueOf(N);
            String xplain = "";
            int digitSum = 1;
            for (int n = 0; n < ns.length(); n++) {
                String letter = ns.substring(n, n + 1);
                digitSum *= Integer.parseInt(letter);
                if (n > 0) xplain += " x";
                xplain += " " + letter;
            }
            solution.appendLine(spaceb4 + xplain + " = " + String.valueOf(digitSum));
            analyseNumber(digitSum);
            if (solved) return;
        }   
        
        // Try reversing the number
        if (CheckReverse && N > 9) {
            int rev = Utility.reverseNumber(N);
            if (rev != N) {
                solution.appendLine(spaceb4 + "Reverse of " + String.valueOf(N) + " is " + String.valueOf(rev));
                analyseNumber(rev, false, false);
                if (solved) return;
            }
        }
        
        // No luck
        solution.appendLine(spaceb4 + "Sorry, no luck with " + String.valueOf(N));
        numberRecursion--;
        
    }
    
    
}
