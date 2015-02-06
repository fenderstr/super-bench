package collections.list.traverse;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.xml.ws.AsyncHandler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;

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
public class StringArrayVsLinkedBenchmark {

    private static int ITERATION_SIZE = 1_000_000;
    private ArrayList<String> arrayList;
    private LinkedList<String> linkedList;

    @Setup(Level.Trial)
    public void setup() {
        arrayList = new ArrayList<>(ITERATION_SIZE);
        for (int i = 0; i < ITERATION_SIZE; i++) {
            arrayList.add(String.valueOf(i));
        }

        linkedList = new LinkedList<>();
        for (int i = 0; i < ITERATION_SIZE; i++) {
            linkedList.add(String.valueOf(i));
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void arrayList(Blackhole bh) {
        for (String s : arrayList) {
            bh.consume(s);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void linkedList(Blackhole bh) {
        for (String s : linkedList) {
            bh.consume(s);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + StringArrayVsLinkedBenchmark.class.getSimpleName() + ".*")
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
