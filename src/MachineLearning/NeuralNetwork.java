package MachineLearning;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import java.util.Random;

class NeuralNetwork {
    private String filename;
    private Instances data;
    private Classifier cl;

    NeuralNetwork(String filename) throws Exception {
        this.filename = filename;
        this.readData();
    }

    private void readData() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filename);
        data = source.getDataSet();
        data.setClassIndex(8);
    }

    void train() throws Exception {
        cl = new MultilayerPerceptron();
        cl.buildClassifier(data);
    }

    void test() throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cl, data, 10, new Random(1));
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }
}
