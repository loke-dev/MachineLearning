
package core;

import java.util.ArrayList;

/**
 * This class trains and evaluates a classifier. Evaluation can be done using
 * the same dataset for training and testing, using a specified test dataset,
 * or using 10-fold cross validation.
 * 
 * @author Johan Hagelbäck (johan.hagelback@gmail.com)
 */
public class Evaluator 
{
    /** The classifier */
    private Classifier classifier;
    /** The dataset */
    private Dataset data;
    
    /**
     * Creates a new evaluator.
     * 
     * @param classifier The classifier to train and test
     * @param filename Filename to the dataset file
     */
    public Evaluator(Classifier classifier, String filename)
    {
        this.classifier = classifier;
        
        //Read the dataset
        DataReader dr = null;
        if (filename.endsWith(".arff")) dr = new ARFFreader();
        if (filename.endsWith(".csv")) dr = new CSVreader();
        if (dr != null)
        {
            dr.readFile(filename);
            data = dr.getData();
        }
        else
        {
            throw new RuntimeException("Unsupported file type for dataset");
        }
    }
    
    /**
     * Evaluates the classifier using the whole dataset for training
     * and the specified dataset for testing.
     * 
     * @param test Test dataset
     */
    public void evaluateSet(ArrayList<Instance> test)
    {
        classifier.train(data);
        double perc = test(test);
        System.out.println("Classifier: " + classifier.toString());
        System.out.println("Evaluation (test set): " + String.format( "%.2f", perc) + "%");
    }
    
    /**
     * Evaluates the classifier using the whole dataset for both
     * training and testing.
     */
    public void evaluateWholeSet()
    {
        classifier.train(data);
        double perc = test(data.toList());
        System.out.println("Classifier: " + classifier.toString());
        System.out.println("Evaluation (whole dataset): " + String.format( "%.2f", perc) + "%");
    }
    
    /**
     * Evaluates the classifier using 10-fold cross validation.
     */
    public void evaluateCV()
    {
        CrossValidation cv = new CrossValidation(data);
        double accPerc = 0;
        for (int f = 0; f < 10; f++)
        {
            Fold fold = cv.getFold(f);
            classifier.train(fold.getTrainingSet());
            accPerc += test(fold.getTestSet());
        }
        double perc = accPerc / 10.0;
        
        System.out.println("Classifier: " + classifier.toString());
        System.out.println("Evaluation (10-fold CV): " + String.format( "%.2f", perc) + "%");
    }

    /**
     * Classifies a set of instances and calculates accuracy (number of correctly
     * classified instances).
     * 
     * @param test The test dataset
     * @return Accuracy (in percent)
     */
    private double test(ArrayList<Instance> test)
    {
        //Calculate correctness by classifying each of the instances
        int n = test.size();
        int no_correct = 0;
        
        for (int r = 0; r < n; r++)
        {
            Instance inst = test.get(r);
            Result res = classifier.classify(inst);
            
            //Check type of class attribute
            if (res.isNominal())
            {
                if (inst.getClassAttribute().nominalValue().equalsIgnoreCase(res.nominalValue()))
                {
                    no_correct++;
                }
            }
            else
            {
                if (inst.getClassAttribute().numericalValue() == res.numericalValue())
                {
                    no_correct++;
                }
            }
        }
        
        //Calculate accuracy
        double perc = (double)no_correct / ((double)n) * 100.0;
        
        return perc;
    }
    
    /**
     * Outputs the dataset to the console.
     */
    public void printDataset()
    {
        System.out.println(data.toString());
    }
}
