
package core;

/**
 * This class represents a result from classifying an instance.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Result
{
    /** Numerical value */
    private double num_value;
    /** Nominal value */
    private String nom_value;
    
    /** Result type */
    private int type = 0;
    /** Nominal (string) attribute */
    private final int NOMINAL = 0;
    /** Numerical attribute */
    private final int NUMERICAL = 1;
    
    /**
     * Creates a new numerical result.
     * 
     * @param value Numerical result
     */
    public Result(double value)
    {
        this.num_value = value;
        type = NUMERICAL;
    }
    
    /**
     * Creates a new nominal result.
     * 
     * @param value Nominal result
     */
    public Result(String value)
    {
        this.nom_value = value;
        type = NOMINAL;
    }
    
    /**
     * Returns the numerical result.
     * 
     * @return Numerical result
     */
    public double numericalValue()
    {
        return num_value;
    }
    
    /**
     * Returns the nominal result.
     * 
     * @return Nominal result
     */
    public String nominalValue()
    {
        return nom_value;
    }
    
    /**
     * Checks if this result is numerical.
     * 
     * @return True if numerical, false otherwise
     */
    public boolean isNumerical()
    {
        return type == NUMERICAL;
    }
    
    /**
     * Checks if this result is nominal.
     * 
     * @return True if nominal, false otherwise
     */
    public boolean isNominal()
    {
        return type == NOMINAL;
    }
    
    @Override
    public String toString()
    {
        if (isNumerical())
        {
            return "{" + String.format("%.2f", num_value) + "}";
        }
        else if (isNominal())
        {
            return "{" + nom_value + "}";
        }
        return "";
    }
}
