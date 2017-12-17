
package core;

/**
 * Interface for classifiers.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public interface Classifier 
{
    /**
     * Trains the classifier.
     * 
     * @param train Data set used for training
     */
    public void train(Dataset train);
    
    /**
     * Classifiers an instance.
     * 
     * @param inst The instance
     * @return Predicted class value for the instance
     */
    public Result classify(Instance inst);
}
