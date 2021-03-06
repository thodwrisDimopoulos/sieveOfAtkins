import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dj_di_000 on 14/10/2017.
 */
public class Main {
    public static void main(String[] args) {
        start(args);
    }

    private static void start(String[] args) {
        int N = -1;
        //argument and integrity constraints check
        try {
            if (args.length < 1 || (N = Integer.parseInt(args[0])) < 1 || N > 59971288) {
                printUsage();
                System.exit(0);
            }

            Long start = System.currentTimeMillis();
            List<Integer> primes = PrimeFactory.findPrimes(N);
            Long elapsed = System.currentTimeMillis() - start;
            System.out.println("Generated "+primes.size() + " primes in " + elapsed + " ms.");
            
            printPrimeMultiplicationTable(primes);

        } catch (NumberFormatException e) {
            printUsage();
            System.exit(-1);

        } catch (OutOfMemoryError e) {
            int MB = 1024 * 1024;
            Runtime runtime = Runtime.getRuntime();
            System.err.println("Ooops! You need more memory to get the " + N + " first primes.");
            System.err.println("You can allocate more memory by executing with option e.g: -Xmx256m ");
            System.err.println("Currently you allocate " + runtime.totalMemory() / MB + " MB. Try to increase this" +
                    "number to " + 2 * runtime.maxMemory() / MB + " MB.");
            System.exit(-2);
        }
    }

    private static void printPrimeMultiplicationTable(List<Integer> primes) {
        int format = String.valueOf(Math.pow(primes.get(primes.size() - 1), 2)).length() - 1;

        System.out.format("%s%" + format + "s%s", "|", " ", "|");
        IntStream.range(0, primes.size()).forEach(i -> System.out.format("%" + format + "d%s", primes.get(i), "|"));
        System.out.format("\n");
        IntStream.range(0, primes.size()).forEach(i -> {
            System.out.format("%s%" + format + "d", "|", primes.get(i));
            primes.forEach(p -> System.out.format("%s%" + format + "d", "|", primes.get(i) * p));
            System.out.format("%s", "|\n");
        });
    }

    private static void printUsage() {
        System.err.println("Usage: program parameter1. \n" +
                "Parameter1 should be a positive integer.\n" +
                "Application can generate up to 59971288 primes.");
    }


}
