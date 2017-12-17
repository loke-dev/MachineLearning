
package core;

/**
 * Interface for a datafile reader implementation.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public interface DataReader 
{
    /**
     * Reads the dataset file and generates dataset.
     * 
     * @param filename Path to the dataset file
     */
    public void readFile(String filename);
    
    /**
     * Reads the dataset file and generates dataset.
     * 
     * @param filename Path to the dataset file
     * @param separator Separator char that separates columns in the dataset file
     */
    public void readFile(String filename, String separator);
    
    /**
     * Returns the dataset.
     * 
     * @return The dataset
     */
    public Dataset getData();
}
