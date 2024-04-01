package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Numbers {
    private final List<Integer> values;

    public static Numbers of(List<Integer> numbers) {
        return new Numbers(numbers);
    }

    private Numbers(List<Integer> numbers) {
        this.values = numbers.stream().collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> values() {
        return new ArrayList<>(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers = (Numbers) o;
        return Objects.equals(values, numbers.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
