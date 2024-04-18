import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;

public final class MultiThreadLetterCounter extends LetterCounter {

    public MultiThreadLetterCounter(final int subtaskCount) {
        super(subtaskCount);
    }

    @Override
    protected Map<Character, Integer> createAccumulator() {
        return new ConcurrentHashMap<>();
    }

    @Override
    protected void execute(final Stream<Subtask> subtasks) {
        final List<Thread> threads = run(subtasks);
        threads.forEach(this::waitUntilFinish);
    }

    private List<Thread> run(final Stream<Subtask> subtasks) {
        return subtasks.map(this::run).toList();
    }

    private Thread run(final Subtask subtask) {
        final Thread thread = new Thread(subtask::execute);
        thread.start();
        return thread;
    }

    private void waitUntilFinish(final Thread thread) {
        try {
            thread.join();
        } catch (final InterruptedException exception) {
            currentThread().interrupt();
        }
    }
}
