/*
 * Kyle Hewitt
 * khewitt5@msudenver.edu
 * CS 2050 
 * Program 4 (Recursion)
 * 
*/
package recursion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
/**
 * Program asks user to choose between three options for problems solved using 
 * recursion; reciprocal arithmetic, palindrome determination, and finding the 
 * greatest common divisor on two input numbers.
 * @author Kyle Hewitt
 */
public class Recursion {

    JFrame frame;//Frame for GUI
    JRadioButton fractions = new JRadioButton("Fractions");//Radio button to choose the fractions function
    JRadioButton gcd = new JRadioButton ("Greatest Common Divisor");//Radio button to choose the GCD function
    JRadioButton palindrome = new JRadioButton("Palindrome");//Radio button to choose the palindrome function
    JButton close = new JButton("Close");//Closes the program
    JButton okay = new JButton("Okay");//Calculate function button
    ButtonGroup group = new ButtonGroup();//ButtonGroup for JRadioButtons
    JPanel north, south, center; //used to place text and labels on the frame
    JTextField userInput1 = new JTextField(5);//Used for user to enter input
    JTextField userInput2 = new JTextField(5);//Second box for user input for GCD
    JLabel result = new JLabel();//Used to display uneditable text for the results
    JLabel label = new JLabel();//Used to display uneditable text for the button instructions
    JLabel s = new JLabel();//Used to display uneditable text for the user instructions
        //boolean expressions to set function results for the "okay" button.
    boolean frac = false;
    boolean divisor = false;
    boolean backAndForth = false;
    
///////////////////////////////////////////////////////////////////////////    
    public static void main(String[] args) {
      Recursion obj = new Recursion();
    }
///////////////////////////////////////////////////////////////////////////
    /**
     * Creates GUI for the recursion program.
     */
    public Recursion(){
        frame = new JFrame();
        frame.setBounds(650, 300, 500, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        group.add(gcd);
        group.add(fractions);
        group.add(palindrome);
        
        south = new JPanel();
        south.add(close);
        frame.add(south, BorderLayout.SOUTH);
      
        center = new JPanel();
        
        Font f = label.getFont();
        label.setText("Pick a function");
        label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
  
        north = new JPanel();
        north.add(label);
        north.add(fractions);
        north.add(gcd);
        north.add(palindrome);
        frame.add(north, BorderLayout.NORTH);
        frame.setVisible(true);
        buttonFunction();
    }//end constructor
///////////////////////////////////////////////////////////////////////////
    /**
     * Sets what happens when the avaliable buttons are pressed or selected.
     */ 
    public void buttonFunction(){
        
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                    System.exit(0);
            }
        });//end close
        
        fractions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                userInput1.setText("");
                frame.add(center,BorderLayout.CENTER);
                south.add(okay);
                showFractions();
            }
        });//end fractions
        
        gcd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               userInput1.setText("");
               frame.add(center,BorderLayout.CENTER);
               south.add(okay);
               showGCD();
            }
        });//end gcd
        
        palindrome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                userInput1.setText("");
                frame.add(center,BorderLayout.CENTER);
                south.add(okay);
                showPalindrome();
            }
        });//end palindrome
        
        okay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (frac){
                    String getText = userInput1.getText();
                    int fractionsInput = Integer.parseInt(getText);
                    System.out.println("You entered: "+ fractionsInput);
                    double backIn = fractions(fractionsInput);
                    System.out.println(backIn);
                    DecimalFormat round = new DecimalFormat("#.###");
                    backIn = Double.valueOf(round.format(backIn));
                    if(backIn == -1)
                        result.setText("Please use positive values.");
                    else
                        result.setText("Answer: "+backIn);
                }//end frac
                if(divisor){
                    s.setText("");
                    String getM = userInput1.getText();
                    String getN = userInput2.getText();
                    int m = Integer.parseInt(getM);
                    int n = Integer.parseInt(getN);
                    System.out.println("You entered: "+ m+" and "+n);
                    double backIn = GCD(m,n);
                    System.out.println(backIn);
                    if(backIn == -1)
                        result.setText("Please use positive values.");
                    else
                        result.setText("GCD: "+backIn);
                }//end divisor
                if (backAndForth){
                    String getText = userInput1.getText();
                    boolean phrase = palindrome(getText);
                    if (phrase)
                        result.setText("That IS a palindrome");
                    else
                        result.setText("That is NOT a palindrome");
                }//end backAndForth
            }// end actionPerformed
        }); //end okay   
    } //end buttonFunction
///////////////////////////////////////////////////////////////////////////    
    /**
     * Sets what the user sees on the frame when the fraction radio button is selected. 
     */
    public void showFractions(){
        s.setText("");
                frac = true;
                backAndForth = false;
                divisor = false;
                
                result.setText("");
                center.add(result);
                center.add(userInput1);
                center.remove(userInput2);
                center.add(s);
                s.setText("Enter the starting reciprical number");       
    }//showFractions
///////////////////////////////////////////////////////////////////////////
    /**
     * Sets what the user sees on the frame when the GCD radio button is selected. 
     */ 
    public void showGCD(){
        s.setText("");
        
        backAndForth = false;
        frac = false;
        divisor = true;
  
        result.setText("");
        center.add(userInput1);
        center.add(userInput2);
        center.add(result);
        center.add(s);
        s.setText("Enter two numbers to find their greatest common divisor");

    }//showGCD
///////////////////////////////////////////////////////////////////////////
    /**
     * Sets what the user sees on the frame when the palindrome radio button is selected. 
     */
    public void showPalindrome(){
        s.setText("");
        frac = false;
        divisor = false;
        backAndForth = true;
        center.remove(userInput2);
  
        result.setText("");
        center.add(userInput1);
        center.add(result);
        center.add(s);
        s.setText("\n\nEnter a word or phrase to see if it is the same forward and back");   
    }//showPalindrome
///////////////////////////////////////////////////////////////////////////
    /**
     * Looks if the current reciptical and if its even it adds the next function otherwise
     * it subtracts the next function from the sum.
     * @param k
     * @return sum
     */
    public static double fractions(int k){
        double dK = k;
        double sum = 0;
        if(dK<0)
            return -1;
        if(dK != 0){
            if(dK % 2 == 0)
                sum = (1/dK)+(fractions(k-1));
            if(dK%2 != 0)
                sum = (1/dK)-(fractions(k-1));
        }
        return sum;
    }//fractions
///////////////////////////////////////////////////////////////////////////
    /**
     * Takes two int values and finds the greatest common divisor of the two 
     * int inputs.
     * @param m
     * @param n
     * @return GCD
     */
    public static double GCD(int m, int n){
       if(m<0 || n<0)
           return -1;
       if(n == 0)
           return m;
       else if (m == 0)
           return n;
       else if (m>=n)
           return GCD(n,(m%n));
       else
           return GCD(n,m);
    }//GCD
///////////////////////////////////////////////////////////////////////////    
    /**
     * Takes a String from the user and removes any special symbols and whitespace
     * and compares the first and the last letter to find out if the string is the 
     * same backwards as it is forward.
     * @param b
     * @return boolean
     */
    public static boolean palindrome(String b){
        String cleanUp = b.replaceAll("\\W","");
        b = cleanUp.toLowerCase();
        
        System.out.println(b);
        
        String passOn = null;
        
        if(b.length() == 0 || b.length() ==1)
            return true;
        else if(b.charAt(0) == b.charAt(b.length()-1))
            return palindrome(b.substring(1, b.length()-1));
        else
            return false;
    }//palindrome
}//class
