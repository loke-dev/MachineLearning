
package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * This class holds the dataset, a inst of instance objects.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Dataset 
{
    /** The dataset */
    private ArrayList<Instance> inst;
    /** List of distinct class values */
    private DistinctValues class_values;
    /** Number of attributes (including class attribute) */
    private int num_attr;
    
    //Used for scaling numerical inst
    private double[] min;
    private double[] max;
    
    /**
     * Creates a new empty dataset.
     * 
     * @param no_attr Number of attributes (including class attribiute)
     */
    public Dataset(int no_attr)
    {
        inst = new ArrayList<>();
        class_values = new DistinctValues();
        num_attr = no_attr;
    }
    
    /**
     * Creates a new dataset from a list of instances.
     * 
     * @param data List of instances
     */
    public Dataset(ArrayList<Instance> data)
    {
        inst = new ArrayList<>();
        class_values = new DistinctValues();
        num_attr = data.get(0).noAttributes();
       
        data.stream().forEach(this::addInstance);
    }
    
    /**
     * Returns a list of the inst in this dataset.
     * 
     * @return List of inst.
     */
    public ArrayList<Instance> toList()
    {
        return inst;
    }
    
    /**
     * Returns the instance at the specified row.
     * 
     * @param row Row index for the instance
     * @return An instance
     */
    public Instance getInstance(int row)
    {
        return inst.get(row);
    }
    
    /**
     * Sets the name of each attribute.
     * 
     * @param names List of attribute names
     */
    public void setAttributeNames(String[] names)
    {
        AttributeNames.getInstance().setNames(names);
    }
    
    /**
     * Returns the name of the specified attribute.
     * 
     * @param attr_index Index for the attribute
     * @return Name of the attribute
     */
    public String getAttributeName(int attr_index)
    {
        return AttributeNames.getInstance().getAttributeName(attr_index);
    }
    
    /**
     * Returns the distinct values for the class attribute.
     * 
     * @return Distinct values
     */
    public DistinctValues getDistinctClassValues()
    {
        return class_values;
    }
    
    /**
     * Returns the distinct values for the specified attribute.
     * 
     * @param attr_index Attribute index
     * @return Distinct values
     */
    public DistinctValues getDistinctValues(int attr_index)
    {
        DistinctValues vals = new DistinctValues();
       
        inst.stream()
                .map((i) -> i.getAttribute(attr_index))
                .forEach((a) -> {
                    if (a.isNominal())
                    {
                        vals.addValue(a.nominalValue());
                    }
                    if (a.isNumerical())
                    {
                        vals.addValue(a.numericalValue());
                    }
                });
        
        return vals;
    }

    /**
     * Returns the number of inst in this dataset.
     * 
     * @return Number of inst
     */
    public int noInstances()
    {
        return inst.size();
    }
    
    /**
     * Returns the number of attributes in this dataset.
     * 
     * @return Number of attributes
     */
    public int noAttributes()
    {
        return num_attr;
    }
    
    /**
     * Returns the number of distinct class values in the dataset.
     * 
     * @return Number of class values
     */
    public int noClassValues()
    {
        return class_values.noValues();
    }
    
    /**
     * Adds an instance to the dataset.
     * 
     * @param values List of attribute values
     */
    public void addInstance(double[] values)
    {
        Instance i = new Instance(num_attr);
        //Create each attribute
        for (int a = 0; a < num_attr; a++)
        {
            double v = values[a];
            i.setAttributeValue(v, a);
        }
        inst.add(i);
        
        //Add to possible class values (if not already added)
        class_values.addValue(i.getClassAttribute().numericalValue());
        class_values.addValue(i.getClassAttribute().nominalValue());
    }
    
    /**
     * Adds an instance to the dataset.
     * 
     * @param values List of attribute values
     */
    public void addInstance(String[] values)
    {
        Instance i = new Instance(num_attr);
        //Create each attribute
        for (int a = 0; a < num_attr; a++)
        {
            String v = values[a];
            i.setAttributeValue(v, a);
        }
        inst.add(i);
        
        //Add to possible class values (if not already added)
        class_values.addValue(i.getClassAttribute().nominalValue());
    }
    
    /**
     * Adds an instance to the dataset.
     * 
     * @param i The instance
     */
    public void addInstance(Instance i)
    {
        //Add to possible class values (if not already added)
        Attribute class_attr = i.getClassAttribute();
        if (class_attr.isNumerical())
        {
            class_values.addValue(class_attr.numericalValue());
        }
        if (class_attr.isNominal())
        {
            class_values.addValue(class_attr.nominalValue());
        }
        
        //Add instance
        inst.add(i);
    }
    
    /**
     * Scales an instance so all attribute values (except the class value) is between
     * 0 and 1.
     * 
     * @param inst The instance to scale
     */
    public void scaleInstance(Instance inst)
    {
        //Rescale all attributes except the class attribute
        for (int i = 0; i < num_attr - 1; i++)
        {
            Attribute a = inst.getAttribute(i);
            if (a.isNumerical())
            {
                double nVal = (a.numericalValue() - min[i]) / (max[i] - min[i]);
                a.setValue(nVal);
            }
        }
    }
    
    /**
     * Scales all inst so all attribute values (except the class value) is between
 0 and 1. Nominal values are skipped.
     */
    public void scaleData()
    {
        min = new double[num_attr - 1];
        max = new double[num_attr - 1];
        
        //Rescale all attributes except the class attribute
        for (int a = 0; a < num_attr - 1; a++)
        {
            //Find lowest and highest value for the attribute
            double low = 10000;
            double high = -10000;
            for (Instance i : inst)
            {
                Attribute attr = i.getAttribute(a);
                if (attr.isNumerical())
                {
                    double v = i.getAttribute(a).numericalValue();
                    if (v < low) low = v;
                    if (v > high) high = v;
                }
            }
            min[a] = low;
            max[a] = high;
            
            //When we have found low and high, scale the attribute
            for (Instance i : inst)
            {
                Attribute attr = i.getAttribute(a);
                if (attr.isNumerical())
                {
                    double nVal = (attr.numericalValue() - low) / (high - low);
                    attr.setValue(nVal);
                }
            }
        }
    }
    
    /**
     * Returns an iterator over all inst in the dataset.
     * 
     * @return Instance iterator
     */
    public Iterator<Instance> iterator()
    {
        return inst.iterator();
    }
    
    @Override
    public String toString()
    {
        String str = inst.stream()
                .map((i) -> i.toString())
                .collect(Collectors.joining("\n"));
        
        return str;
    }
}
