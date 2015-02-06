package collections.list.traverse;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ANosov
 *         Date: 16.10.14 11:58
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(3)
@State(Scope.Benchmark)
@Threads(1)
public class IntegerArrayVsLinkedBenchmark {

    private static int ITERATION_SIZE = 1_000_000;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private static Random rnd = new Random();


    @Setup(Level.Trial)
    public void setup() {
        arrayList = new ArrayList<>(ITERATION_SIZE);
        for (int i = 0; i < ITERATION_SIZE; i++) {
            arrayList.add(new Integer(rnd.nextInt()));
        }

        linkedList = new LinkedList<>();
        for (int i = 0; i < ITERATION_SIZE; i++) {
            linkedList.add(new Integer(rnd.nextInt()));
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void arrayList(Blackhole bh) {
        for (Integer s : arrayList) {
            bh.consume(s);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void linkedList(Blackhole bh) {
        for (Integer s : linkedList) {
            bh.consume(s);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + IntegerArrayVsLinkedBenchmark.class.getSimpleName() + ".*")
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
