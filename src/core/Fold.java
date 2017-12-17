
package core;

import java.util.ArrayList;

/**
 * This class represents a fold (a training and a test dataset) in
 * 10-fold cross validation.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Fold
{
    /** Instances used for training */
    private ArrayList<Instance> train;
    /** Instances used for testing */
    private ArrayList<Instance> test;

    /**
     * Constructor.
     * 
     * @param train Training dataset
     * @param test Test dataset
     */
    public Fold(ArrayList<Instance> train, ArrayList<Instance> test)
    {
        this.train = train;
        this.test = test;
    }
    
    /**
     * Returns the test dataset.
     * 
     * @return Test dataset
     */
    public ArrayList<Instance> getTestSet()
    {
        return test;
    }
    
    /**
     * Returns the training dataset.
     * 
     * @return Training dataset.
     */
    public Dataset getTrainingSet()
    {
        return new Dataset(train);
    }
}