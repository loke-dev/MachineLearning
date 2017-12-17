
package core;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class generates the folds (training and test sets) used
 * in 10-fold cross validation. The class only separates the data
 * set into folds, it does not run the actual training and evaluation
 * of the folds.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class CrossValidation 
{
    /** The full dataset */
    private Dataset data;
    /** Randomizer */
    private Random rnd;
    /** List of folds */
    private ArrayList<Fold> folds;
    
    /** Internal: flags the instances that have been used in test sets */
    private boolean[] used;
    /** Internal: flags the instances in the current test set */
    private boolean[] usedInFold;
    
    /**
     * Builds a new cross validation model with 10 folds from the
     * specified dataset.
     * 
     * @param data The full dataset
     */
    public CrossValidation(Dataset data)
    {
        this.data = data;
        folds = new ArrayList<>();
        rnd = new Random(1);
        buildModel();
    }
    
    /**
     * Builds the cross validation folds.
     */
    private void buildModel()
    {
        //Keeps track of which instances that have been
        //used in test sets so far
        used = new boolean[data.noInstances()];
        
        //Step 1: Calculate sizes of test sets
        int base = data.noInstances() / 10;
        int rest = data.noInstances() % 10;
        
        //Step 2: Create the folds
        for (int f = 0; f < 10; f++)
        {
            //Base size
            int no = base;
            //Spread out the rest
            if (rest > 0)
            {
                no++;
                rest--;
            }
            
            //Create test set
            ArrayList<Instance> test = new ArrayList<>();
            //Keep track of which instances used in the test set
            usedInFold = new boolean[data.noInstances()];
            //Fill the test set
            for (int i = 0; i < no; i++)
            {
                test.add(findTestInstance());
            }
            
            //Create and fill train set with the rest
            //of the instances
            ArrayList<Instance> train = new ArrayList<>();
            for (int i = 0; i < data.noInstances(); i++)
            {
                if (!usedInFold[i])
                {
                    train.add(data.getInstance(i));
                }
            }
            
            folds.add(new Fold(train, test));
        }
    }
    
    /**
     * Finds an instance to be used in the current test set.
     * 
     * @return The instance
     */
    private Instance findTestInstance()
    {
        Instance inst = null;
        boolean found = false;
        
        //Iterate until we find an instance that has not already
        //been used in a test set
        while (!found)
        {
            int index = rnd.nextInt(data.noInstances());
            if (!used[index])
            {
                //Instance found
                inst = data.getInstance(index);
                //Update flags
                used[index] = true;
                usedInFold[index] = true;
                found = true;
            }
        }
        
        return inst;
    }
    
    /**
     * Returns a fold with the specified index. Each fold holds a training
     * and a test dataset.
     * 
     * @param index Index of the fold (0-9)
     * @return The fold
     */
    public Fold getFold(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > 9)
        {
            throw new IndexOutOfBoundsException("Fold index must be between 0 and 9");
        }
        return folds.get(index);
    }
}
