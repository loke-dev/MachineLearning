
package core;

import java.util.ArrayList;

/**
 * This is a singleton class holding the names of the attributes
 * in a dataset.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class AttributeNames 
{
    /** Singleton instance */
    private static AttributeNames instance;
    /** List of attribute names */
    private ArrayList<String> attr_names;
    
    /**
     * Returns an instance reference to the class.
     * 
     * @return Instance reference
     */
    public static AttributeNames getInstance()
    {
        if (instance == null)
        {
            instance = new AttributeNames();
        }
        return instance;
    }
    
    /**
     * Private constructor (singleton class).
     */
    private AttributeNames()
    {
        attr_names = new ArrayList<>();
    }
    
    /**
     * Sets the attribute names.
     * 
     * @param names List of attribute names
     */
    public void setNames(String[] names)
    {
        for (int i = 0; i < names.length; i++)
        {
            attr_names.add(i, names[i]);
        }
    }
    
    /**
     * Sets an attribute name.
     * 
     * @param name Attribute name
     * @param index Attribute index
     */
    public void setName(String name, int index)
    {
        attr_names.add(index, name);
    }
    
    /**
     * Returns the name of an attribute.
     * 
     * @param index Attribute index
     * @return Attribute name
     */
    public String getAttributeName(int index)
    {
        try 
        {
            return attr_names.get(index);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return "(" + index + ")";
        }
    }
}
