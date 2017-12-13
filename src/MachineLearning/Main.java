package MachineLearning;

public class Main {

    public static void main(String[] args) throws Exception {
        NaiveBayes nb = new NaiveBayes("./resources/wikipedia_70.arff");
        nb.train();
        nb.test();
    }
}
