public class Main {

    public static void main(String[] args) {
        if (args.length == 3) {
            TTest tTest = new TTest(Double.parseDouble(args[0]),  // m0
                    Double.parseDouble(args[1]),                  // significance level
                    new LoadPattern(args[2]).getValues());        // pattern file path
        }
    }
}
