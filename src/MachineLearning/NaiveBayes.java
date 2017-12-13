package MachineLearning;

import weka.classifiers.Classifier;
import weka.core.*;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.*;

class NaiveBayes {
    private String filename;
    private  Instances data;
    private Classifier cl;

    NaiveBayes(String filename) throws Exception {
        this.filename = filename;
        this.readData();
    }

    private void readData() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filename);
        Instances raw = source.getDataSet();

        StringToWordVector stw = new StringToWordVector(10000);
        stw.setLowerCaseTokens(true);
        stw.setInputFormat(raw);

        data = Filter.useFilter(raw, stw);
        data.setClassIndex(0);
    }

    void train() throws Exception {
        cl = new NaiveBayesMultinomial();
        cl.buildClassifier(data);
    }

    void test() throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cl, data, 10, new java.util.Random(1));
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }

}
