
package core;

import java.io.*;

/**
 * This class reads a dataset from a CSV file.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class CSVreader implements DataReader
{
    /** The dataset */
    private Dataset data;
    
    /**
     * Constructor.
     */
    public CSVreader()
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
            readFile(filename, ";");
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
        //Read header line (with attribute names)
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            //Read header line (with attribute names)
            String line = in.readLine();
            String[] names = line.split(separator);
            data.setAttributeNames(names);
            data = new Dataset(names.length);

            while ((line = in.readLine()) != null)
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
