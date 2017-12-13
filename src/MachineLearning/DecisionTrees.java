package MachineLearning;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils;

class DecisionTrees {
    private String filename;
    private  Instances data;
    private Classifier cl;

    DecisionTrees(String filename) throws Exception {
        this.filename = filename;
        this.readData();
    }

    private void readData() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filename);
        data = source.getDataSet();
        data.setClassIndex(0);
    }

    void train() throws Exception {
        cl = new J48();
        cl.buildClassifier(data);
    }

    void test() throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cl, data, 10, new java.util.Random(1));
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }

}
