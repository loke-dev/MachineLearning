
package core;

import java.io.*;
import java.util.ArrayList;

/**
 * This class reads a dataset from an ARFF file.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class ARFFreader implements DataReader
{
    /** The dataset */
    private Dataset data;
    
    /**
     * Constructor.
     */
    public ARFFreader()
    {
        
    }
    
    /**
     * Reads the dataset file and generates dataset.
     * 
     * @param filename Path to the dataset file
     */
    @Override
    public void readFile(String filename)
    {
        try
        {
            readFile(filename, ",");
        }
        catch (Exception ex)
        {
            System.out.println("Error reading data file: " + ex.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Reads the dataset file and generates dataset.
     * 
     * @param filename Path to the dataset file
     * @param separator Separator char that separates columns in the dataset file
     */
    @Override
    public void readFile(String filename, String separator)
    {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            ArrayList<String> n_list = new ArrayList<>();
            String[] names = null;
            boolean dataSection = false;

            while ((line = in.readLine()) != null)
            {
                if (line.startsWith("@attribute"))
                {
                    //Attribute description row
                    String[] tokens = line.split(" ");
                    n_list.add(tokens[1]);
                }
                else if (line.startsWith("@data"))
                {
                    //Done with all attributes.
                    //Copy data to array
                    names = new String[n_list.size()];
                    for (int i = 0; i < n_list.size(); i++)
                    {
                        names[i] = n_list.get(i);
                    }
                    //Create the empty dataset
                    data = new Dataset(names.length);
                    data.setAttributeNames(names);
                    dataSection = true;
                }
                else if (dataSection && !line.equals(""))
                {
                    String[] values = line.split(separator);
                    if (values.length != data.noAttributes())
                    {
                        throw new RuntimeException("Invalid number of attributes: " + values.length + "/" + data.noAttributes());
                    }
                    
                    Instance i = new Instance(values.length);

                    //Iterate over each value
                    for (int a = 0; a < values.length; a++)
                    {
                        String val = values[a];

                        //Check the type of attribute
                        if (isNumber(val))
                        {
                            double num_val = Double.parseDouble(val);
                            i.setAttributeValue(num_val, a);
                        }
                        else
                        {
                            i.setAttributeValue(val, a);
                        }
                    }

                    data.addInstance(i);
                }
            }               
        }
        catch (Exception ex)
        {
            System.out.println("Error reading data file: " + ex.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Checks if a string is a number.
     * 
     * @param v The string
     * @return True if the string is a number, false otherwise
     */
    private boolean isNumber(String v)
    {
        try
        {
            Double.parseDouble(v);
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }
    
    /**
     * Returns the dataset.
     * 
     * @return The dataset
     */
    @Override
    public Dataset getData()
    {
        return data;
    }
}
