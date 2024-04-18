import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public final class CounterTestUtil {

    public static void test(final LetterCounter counter) {
        provideArguments().forEach(argument -> test(counter, argument));
    }

    private static void test(final LetterCounter counter, final Argument argument) {
        final Map<Character, Integer> actual = counter.count(argument.givenText);
        final boolean success = Objects.equals(argument.expected, actual);
        if (!success) {
            throw new RuntimeException("Test failed");
        }
    }

    private static Stream<Argument> provideArguments() {
        return Stream.of(
                new Argument("", emptyMap()),
                new Argument(" 123!4,  32 !! ", emptyMap()),
                new Argument(
                        "Hello world!!!",
                        Map.of('r', 1, 'd', 1, 'e', 1, 'w', 1, 'h', 1, 'l', 3, 'o', 2)
                ),
                new Argument(
                        """
                                A hash table supporting full concurrency of retrievals and high expected concurrency for updates.
                                This class obeys the same functional specification as Hashtable, and includes versions of methods
                                corresponding to each method of Hashtable. However, even though all operations are thread-safe,
                                retrieval operations do not entail locking, and there is not any support for locking the entire table in a way that prevents all access.
                                This class is fully interoperable with Hashtable in programs that rely on its thread safety but not on its synchronization details.
                                Retrieval operations (including get) generally do not block, so may overlap with update operations (including put and remove).
                                Retrievals reflect the results of the most recently completed update operations holding upon their onset.
                                (More formally, an update operation for a given key bears a happens-before relation with any (non-null)
                                retrieval for that key reporting the updated value.)
                                For aggregate operations such as putAll and clear, concurrent retrievals may reflect insertion or removal
                                of only some entries. Similarly, Iterators, Spliterators and Enumerations return elements
                                reflecting the state of the hash table at some point at or since the creation of the iterator/enumeration.
                                They do not throw ConcurrentModificationException.
                                However, iterators are designed to be used by only one thread at a time.
                                Bear in mind that the results of aggregate status methods including size, isEmpty,
                                and containsValue are typically useful only when a map is not undergoing concurrent updates in other threads.
                                Otherwise the results of these methods reflect transient states that may be adequate
                                for monitoring or estimation purposes, but not for program control.""",
                        ofEntries(
                                entry('a', 119),
                                entry('b', 17),
                                entry('c', 45),
                                entry('d', 41),
                                entry('e', 177),
                                entry('f', 30),
                                entry('g', 27),
                                entry('h', 58),
                                entry('i', 88),
                                entry('k', 5),
                                entry('l', 69),
                                entry('m', 29),
                                entry('n', 108),
                                entry('o', 119),
                                entry('p', 40),
                                entry('q', 1),
                                entry('r', 112),
                                entry('s', 91),
                                entry('t', 151),
                                entry('u', 43),
                                entry('v', 17),
                                entry('w', 9),
                                entry('x', 2),
                                entry('y', 27),
                                entry('z', 2)
                        )
                )
        );
    }

    private static final class Argument {
        private final String givenText;
        private final Map<Character, Integer> expected;

        public Argument(final String givenText, final Map<Character, Integer> expected) {
            this.givenText = givenText;
            this.expected = expected;
        }
    }
}