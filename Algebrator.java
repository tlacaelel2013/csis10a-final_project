
/**
 * This class contains the main method to process algebraic expressions
 * 
 * @author: Luis Acevedo-Arreguin
 * @version: May 07, 2014
 */
public class Algebrator
{
    public static void main(String[] args) {

        System.out.println("\f");

        Term first = new Term(3, 1, "y", 2);
        Term second = new Term(2, 1, "y", 2);
        Term third = new Term(2, 1, "x", 1);

        Term newTerm = new Term();
        newTerm = first.add(second);
        System.out.println(first + " + " + second + " = " + newTerm);

        newTerm = first.add(third);
        System.out.println(first + " + " + third + " = " + newTerm);

        Term fourth = new Term(-3, 1, "y", 2);
        newTerm = first.add(fourth);
        System.out.println(first + " + " + fourth + " = " + newTerm);
        
        newTerm = first.multiply(second);
        System.out.println(first + " * " + second + " = " + newTerm);
        
        newTerm = first.multiply(third);
        System.out.println(first + " * " + third + " = " + newTerm);



    }
}
