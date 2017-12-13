package MachineLearning;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import libsvm.*;
import weka.core.*;
import weka.core.converters.ConverterUtils;

class Libsvm {
    private String filename;
    private  Instances data;
    private Classifier cl;

    Libsvm(String filename) throws Exception {
        this.filename = filename;
        this.readData();
    }

    private void readData() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filename);
        data = source.getDataSet();
        data.setClassIndex(0);
    }

    void train() throws Exception {
        cl = new Libsvm(filename);
        cl.buildClassifier(data);
    }

    void test() throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cl, data, 10, new java.util.Random(1));
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }

}
