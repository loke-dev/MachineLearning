
package core;

import java.util.ArrayList;

/**
 * This class represents an instance (example, field) in a dataset.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Instance 
{
    /** List of attributes, the last is the class attribute */
    private ArrayList<Attribute> attr;
    
    /**
     * Creates a new instance.
     * 
     * @param n Number of attributes (including class attribute)
     */
    public Instance(int n)
    {
        attr = new ArrayList<>(n);
    }
    
    /**
     * Sets a numerical attribute for this instance.
     * 
     * @param value Attribute value
     * @param attr_index Index of attribute
     */
    public void setAttributeValue(double value, int attr_index)
    {
        attr.add(attr_index, new Attribute(value));
    }
    
    /**
     * Adds a nominal attribute to this instance.
     * 
     * @param value Attribute value
     * @param attr_index Index of attribute
     */
    public void setAttributeValue(String value, int attr_index)
    {
        attr.add(attr_index, new Attribute(value));
    }
    
    /**
     * Returns the attribute at the specified index.
     * 
     * @param attr_index Attribute index
     * @return The attribute
     */
    public Attribute getAttribute(int attr_index)
    {
        return attr.get(attr_index);
    }
    
    /**
     * Returns the name of the attribute at the specified index.
     * 
     * @param attr_index Attribute index
     * @return Attribute name
     */
    public String getAttributeName(int attr_index)
    {
        return AttributeNames.getInstance().getAttributeName(attr_index);
    }
    
    /**
     * Returns the attributes for this instance.
     * 
     * @return The attributes
     */
    public ArrayList<Attribute> getAttributes()
    {
        return attr;
    }
    
    /**
     * Returns the class attribute.
     * 
     * @return The class attribute
     */
    public Attribute getClassAttribute()
    {
        return attr.get(attr.size() - 1);
    }
    
    /**
     * Returns the number of attributes (including class attribute)
     * for this instance.
     * 
     * @return Number of attributes
     */
    public int noAttributes()
    {
        return attr.size();
    }
    
    /**
     * Returns a list of the attribute values for this instance, excluding
     * the class attribute value.
     * 
     * @return List of attribute values
     */
    public double[] getAttributeArrayNumerical()
    {
        double[] vals = new double[attr.size() - 1];
        for (int i = 0; i < attr.size() - 1; i++)
        {
            vals[i] = attr.get(i).numericalValue();
        }
        return vals;
    }
    
    /**
     * Returns a list of the attribute values for this instance, excluding
     * the class attribute value.
     * 
     * @return List of attribute values
     */
    public String[] getAttributeArrayNominal()
    {
        String[] vals = new String[attr.size() - 1];
        for (int i = 0; i < attr.size() - 1; i++)
        {
            vals[i] = attr.get(i).nominalValue();
        }
        return vals;
    }
    
    @Override
    public String toString()
    {
        String str = "{";
        for (int i = 0; i < attr.size() - 1; i++)
        {
            Attribute a = attr.get(i);
            
            String v = "";
            if (a.isNominal())
            {
                v = a.nominalValue();
            }
            else if (a.isNumerical())
            {
                v = String.format( "%.2f", a.numericalValue());
            }
                
            
            str += AttributeNames.getInstance().getAttributeName(i) + " = " + v + ", ";
        }
        
        //Class attribute
        Attribute a = attr.get(attr.size() - 1);
        
        String v = "";
        if (a.isNominal())
        {
            v = a.nominalValue();
        }
        else if (a.isNumerical())
        {
            v = String.format( "%.2f", a.numericalValue());
        }
        
        str += AttributeNames.getInstance().getAttributeName(attr.size() - 1) + " = " + v + "}";
        
        return str;
    }
}
