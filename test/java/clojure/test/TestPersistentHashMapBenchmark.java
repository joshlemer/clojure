package clojure.test;

import clojure.lang.AFn;
import clojure.lang.IFn;
import clojure.lang.PersistentHashMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@Fork(1)

public class TestPersistentHashMapBenchmark {
    private static PersistentHashMap template =
        PersistentHashMap.create("1", 0, "2", 1, "3", 2, "4", 3, "5", 4, "6", 5, "7", 6, "8", 7, "9", 8, "10", 9);

    @Benchmark
    public static Object create10() {
        return PersistentHashMap.create("1", "a", "2", "b", "3", "c", "4", "d", "5", "e", "6", "f", "7", "g", "8", "h", "9", "i", "10", "j");
    }

    @Benchmark
    public static Object updateVals10() {
        return template.updateVals(new AFn() {
            private final Object[] args = new Object[]{"a","b","c","d","e","f","g","h","i","j"};
            @Override
            public Object invoke(Object arg1) {
                return args[(int)arg1];
            }
        });
    }


    public static void main (String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(TestPersistentHashMapBenchmark.class.getName() + ".*")
                .build();

        new Runner(options).run();
    }
}
