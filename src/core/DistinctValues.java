
package core;

import java.util.ArrayList;

/**
 * This class represents a list of distinct values an attribute
 * can have.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class DistinctValues 
{
    /** List of nominal values */
    private ArrayList<String> nom_values;
    /** List of numerical values */
    private ArrayList<Double> num_values;
    /** The attribute is numerical */
    private boolean is_num;
    /** The attribute is nominal */
    private boolean is_nom;
    
    /**
     * Constructor.
     */
    public DistinctValues()
    {
        nom_values = new ArrayList<>();
        num_values = new ArrayList<>();
        is_nom = false;
        is_num = false;
    }
    
    /**
     * Adds a numerical value to the list. If the added value is not
     * in the list already, it is added to the list.
     * 
     * @param value Numerical value
     */
    public void addValue(double value)
    {
        if (!num_values.contains(value))
        {
            num_values.add(value);
        }
        is_num = true;
    }
    
    /**
     * Adds a nominal value to the list. If the added value is not
     * in the list already, it is added to the list.
     * 
     * @param value Nominal value
     */
    public void addValue(String value)
    {
        if (!nom_values.contains(value))
        {
            nom_values.add(value);
        }
        is_nom = true;
    }
    
    /**
     * Checks if this attribute is numerical.
     * 
     * @return True if numerical, false otherwise
     */
    public boolean isNumerical()
    {
        return is_num;
    }
    
    /**
     * Checks if this attribute is nominal.
     * 
     * @return True if nominal, false otherwise
     */
    public boolean isNominal()
    {
        return is_nom;
    }
    
    /**
     * Returns the list of distinct numerical values for this attribute.
     * 
     * @return List of distinct numerical values
     */
    public ArrayList<Double> getNumericalValues()
    {
        return num_values;
    }
    
    /**
     * Returns the list of distinct nominal values for this attribute.
     * 
     * @return List of distinct nominal values
     */
    public ArrayList<String> getNominalValues()
    {
        return nom_values;
    }
    
    /**
     * Returns the number of values in the list.
     * 
     * @return Number of values
     */
    public int noValues()
    {
        if (isNumerical())
        {
            return num_values.size();
        }
        else
        {
            return nom_values.size();
        }
    }
}
