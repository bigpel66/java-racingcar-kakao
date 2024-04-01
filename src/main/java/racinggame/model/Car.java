package racinggame.model;

import racinggame.strategy.DefaultRandomStrategy;
import racinggame.strategy.RandomStrategy;

import java.util.Objects;

public final class Car {
    private static final int FORWARDABLE = 4;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int offset;
    private final RandomStrategy randomStrategy;

    public static Car defaultStrategyOf(String name) {
        return new Car(name, DefaultRandomStrategy.newInstance());
    }

    public static Car defaultStrategyOf(String name, int offset) {
        return new Car(name, offset, DefaultRandomStrategy.newInstance());
    }

    public static Car of(String name, RandomStrategy randomStrategy) {
        return new Car(name, randomStrategy);
    }

    private Car(String name, RandomStrategy randomStrategy) {
        this(name, 0, randomStrategy);
    }

    private Car(String name, int offset, RandomStrategy randomStrategy) {
        validateRequiredArguments(name, randomStrategy);
        this.name = name;
        this.offset = offset;
        this.randomStrategy = randomStrategy;
    }

    private void validateRequiredArguments(String name, RandomStrategy randomStrategy) {
        checkName(name);
        checkStrategy(randomStrategy);
    }

    private void checkName(String name) {
        checkNullOrBlank(name);
        checkLength(name);
    }

    private void checkNullOrBlank(String name) {
        Objects.requireNonNull(name);
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 빈 문자열일 수 없습니다.");
        }
    }

    private void checkLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 " + MAX_NAME_LENGTH + "글자 이하여야 합니다.");
        }
    }

    private void checkStrategy(RandomStrategy randomStrategy) {
        Objects.requireNonNull(randomStrategy);
    }

    public String name() {
        return name;
    }

    public int offset() {
        return offset;
    }

    public void move() {
        if (randomStrategy.perform() >= FORWARDABLE) {
            offset++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
