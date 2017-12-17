
package core;

/**
 * This class represents an attribute (feature) in an instance.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Attribute
{
    /** Attribute numerical value */
    private double num_value;
    /** Attribute nominal value */
    private String nom_value;
    
    /** Attribute type */
    private int type = 0;
    /** Nominal (string) attribute */
    private final int NOMINAL = 0;
    /** Numerical attribute */
    private final int NUMERICAL = 1;
    
    /**
     * Creates a new attribute.
     * 
     * @param value Attribute value
     */
    public Attribute(double value)
    {
        this.num_value = value;
        type = NUMERICAL;
    }
    
    /**
     * Creates a new attribute.
     * 
     * @param value Attribute value
     */
    public Attribute(String value)
    {
        this.nom_value = value;
        type = NOMINAL;
    }
    
    /**
     * Returns the numerical value of this attribute.
     * 
     * @return Attribute value
     */
    public double numericalValue()
    {
        return num_value;
    }
    
    /**
     * Returns the nominal value of this attribute.
     * 
     * @return Attribute value
     */
    public String nominalValue()
    {
        return nom_value;
    }
    
    /**
     * Returns the value as a string, regardless
     * of type.
     * 
     * @return The value
     */
    public String value()
    {
        if (type == NOMINAL) return nom_value;
        else return num_value + "";
    }
    
    /**
     * Sets the numerical value for this attribute.
     * 
     * @param value Attribute value to set
     */
    public void setValue(double value)
    {
        this.num_value = value;
        type = NUMERICAL;
    }
    
    /**
     * Sets the nominal value for this attribute.
     * 
     * @param value Attribute value to set
     */
    public void setValue(String value)
    {
        this.nom_value = value;
        type = NUMERICAL;
    }
    
    /**
     * Checks if this attribute is numerical.
     * 
     * @return True if numerical, false otherwise
     */
    public boolean isNumerical()
    {
        return type == NUMERICAL;
    }
    
    /**
     * Checks if this attribute is nominal.
     * 
     * @return True if nominal, false otherwise
     */
    public boolean isNominal()
    {
        return type == NOMINAL;
    }
}
