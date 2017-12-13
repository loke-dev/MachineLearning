package MachineLearning;

public class Main {

    public static void main(String[] args) throws Exception {
//        NaiveBayes nb = new NaiveBayes("./resources/wikipedia_70.arff");
//        nb.train();
//        nb.test();

//        DecisionTrees dt = new DecisionTrees("./resources/FIFA_skill.arff");
//        dt.train();
//        dt.test();

        Libsvm ls = new Libsvm("./resources/matchmaker_fixed.arff");
        ls.train();
        ls.test();
    }
}
