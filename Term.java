
/**
 * This class create the object term, which is the smallest unit of an algebraic expression
 * 
 * @author: Luis Acevedo-Arreguin 
 * @version: May 07, 2014
 */
public class Term
{
    // instance variables - replace the example below with your own
    private int coefexpo;
    private int varexpo;
    private String var;
    private double coef;

    /**
     * Constructors for objects of class Term
     */
    public Term()
    {
        // initialise instance variables
        this.coefexpo = 1;
        this.varexpo = 1;
        this.var = "x";
        this.coef = 0;
    }

    public Term(double coef, int coefexpo, String var, int varexpo) {
        this.coefexpo = coefexpo;
        this.varexpo = varexpo;
        this.coef = coef;
        this.var = var;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  another   an expression to compare to a given term
     * @return     whether this term and the another term are like terms 
     */
    public boolean isLikeTerm(Term another)
    {
        boolean b = false;
        if (another instanceof Term) {
            if (this.var == another.var && this.varexpo == another.varexpo) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public String toString()
    {
        String formula = "0";
        if (coef != 0) {
            formula = "" + coef;
            if (coefexpo != 1) {
                formula = formula + "^{" + coefexpo + "}";
            }
            if (varexpo != 0) {
               
                formula = formula + var;
                if ((int)(Math.round(coef)) == 1) formula = var; 
                if (varexpo != 1) {
                    formula = formula + "^{" + varexpo + "}";
                }
            }
        }
            
        return new String(formula);
    }

    public Term add(Term another) 
    {
        Term result = new Term();
        if (this.isLikeTerm(another)) {
            result.coef = this.coef+another.coef;
            result.coefexpo = this.coefexpo; // coefexpo is assumed to be one for both terms
            result.var = this.var;
            result.varexpo = this.varexpo;
        }
        else {
            result.coef = 1;
            result.coefexpo = 1;
            result.var = "("+this.toString()+"+ "+another.toString()+")";
            result.varexpo = 1;
        }
        return result;
    }
    
    public Term multiply(Term another)
    {
       Term result = new Term();
       result.coef = this.coef * another.coef;
       result.coefexpo = this.coefexpo; // coefexpo is assumed to be one for both terms
       if (this.var == another.var) {
           result.var = this.var;
           result.varexpo = this.varexpo + another.varexpo; 
        }
        else {
            result.var = "(" + this.var + "^{" + this.varexpo + "}" + another.var + "^{"
                + another.varexpo + "})";
            result.varexpo = 1;
        }
       return result;
    }

}
