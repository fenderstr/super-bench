package collections.list.access;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ANosov
 *         Date: 16.10.14 13:47
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@Fork(2)
@State(Scope.Benchmark)
@Threads(1)
public class AccessRandomArrayVsLinkedBenchmark {

    private static int ITERATION_SIZE = 10_000_000;
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
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void arrayList(Blackhole bh) {
        bh.consume(arrayList.get(rnd.nextInt(ITERATION_SIZE)));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void linkedList(Blackhole bh) {
        bh.consume(linkedList.get(rnd.nextInt(ITERATION_SIZE)));
    }

}
