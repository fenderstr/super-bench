package collections.list.add;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ANosov
 *         Date: 16.10.14 11:58
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@Fork(2)
@State(Scope.Benchmark)
@Threads(1)
public class AddToEndIntegerArrayVsLinkedBenchmark {

    private static int ITERATION_SIZE = 10_000_000;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private static Random rnd = new Random();


    @Setup(Level.Trial)
    public void setup() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < ITERATION_SIZE; i++) {
            arrayList.add(rnd.nextInt());
        }

        linkedList = new LinkedList<>();
        for (int i = 0; i < ITERATION_SIZE; i++) {
            linkedList.add(rnd.nextInt());
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void arrayList(Blackhole bh) {
        final ListIterator<Integer> iterator = arrayList.listIterator();
        iterator.add(rnd.nextInt());
        bh.consume(arrayList);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void linkedList(Blackhole bh) {
        final ListIterator<Integer> iterator = linkedList.listIterator();
        iterator.add(rnd.nextInt());
        bh.consume(linkedList);
    }

}
