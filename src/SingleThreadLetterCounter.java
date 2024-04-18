import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class SingleThreadLetterCounter extends LetterCounter {

    public SingleThreadLetterCounter() {
        super(1);
    }

    @Override
    protected Map<Character, Integer> createAccumulator() {
        return new HashMap<>();
    }

    @Override
    protected void execute(final Stream<Subtask> subtasks) {
        subtasks.forEach(Subtask::execute);
    }
}
