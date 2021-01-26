import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;

import java.util.List;

/**
 * The TTest class runs a T-test on the pattern (or sample) loaded by LoadPattern, with additional necessary values
 * provided as CLA (command-line arguments).
 */
public class TTest {
    private final double m0;
    private final double significanceLevel;
    private final List<Double> pattern;

    public TTest(double m0, double significanceLevel, List<Double> pattern) {
        this.m0 = m0;
        this.significanceLevel = significanceLevel / 100;
        this.pattern = pattern;

        RunTest();
    }

    public void PrintPattern() {
        System.out.print("Pattern:\t");
        for(Double i : pattern) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    private void RunTest() {
        int n = pattern.size();
        double criticalValue = new TDistribution(n - 1).inverseCumulativeProbability(significanceLevel);
        double criticalValue2 = new TDistribution(n - 1).inverseCumulativeProbability(significanceLevel / 2);
        double sVariance = Math.sqrt(StatUtils.variance(pattern.stream()
                .mapToDouble(Double::doubleValue)
                .toArray()));
        double mean = StatUtils.mean(pattern.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
        double tValue = (mean - m0) / (sVariance / Math.sqrt(n));

        PrintPattern();
        System.out.print("###\n" +
                "m0 = " + m0 + "\n" +
                "s = " + sVariance + "\n" +
                "significance = " + significanceLevel + "\n" +
                "n = " + n + "\n" +
                "mean = " + mean + "\n" +
                "t = (" + mean + " - " + m0 + ") / (" + sVariance + " / sqrt(" + n + ")) = " + tValue + "\n" +
                "c (1 - " + significanceLevel + ") = " + criticalValue + "\n" +
                "c2 (1 - " + significanceLevel + " / 2) = " + criticalValue2 + "\n" +
                "###\n\n");

        System.out.print("Left-tailed test:\n" +
                " H0: m <= m0\tH1: m > m0\n\n" +
                " =============|---\n");
        if (tValue < criticalValue) {
            System.out.print("       t      c\n" +
                    "  Null hypothesis accepted.\n");
        } else {
            System.out.print("              c z\n" +
                    "  Null hypothesis rejected.\n");
        }

        System.out.print("\nRight-tailed test:\n" +
                " H0: m >= m0\tH1: m < m0\n\n" +
                " ---|=============\n");
        if (tValue > -criticalValue) {
            System.out.print("   c      t\n" +
                    "  Null hypothesis accepted.\n");
        } else {
            System.out.print("  t c\n" +
                    "  Null hypothesis rejected.\n");
        }

        System.out.print("\nTwo-tailed test:\n" +
                " H0: m = m0\tH1: m =/= m0\n\n" +
                " ---|=========|---\n");
        if (tValue < -criticalValue2) {
            System.out.print("  t c2        c2\n" +
                    "  Null hypothesis rejected.\n");
        } else if (tValue > criticalValue2) {
            System.out.print("    c2        c2 t\n" +
                    "  Null hypothesis rejected.\n");
        } else {
            System.out.print("    c2   t    c2\n" +
                    "  Null hypothesis accepted.\n");
        }
    }
}
