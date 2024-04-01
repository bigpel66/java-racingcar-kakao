package racinggame.controller;

import racinggame.model.Cars;
import racinggame.strategy.WinnerStrategy;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public final class RacingGame {
    private final Cars cars;
    private final int trial;
    private final Consumer<Cars> turnCallback;
    private final WinnerStrategy winnerStrategy;

    public static RacingGame of(Cars cars, int trial, Consumer<Cars> turnCallback, WinnerStrategy winnerStrategy) {
        return new RacingGame(cars, trial, turnCallback, winnerStrategy);
    }

    private RacingGame(Cars cars, int trial, Consumer<Cars> turnCallback, WinnerStrategy winnerStrategy) {
        validateRequiredArguments(cars, turnCallback, winnerStrategy);
        this.cars = cars;
        this.trial = trial;
        this.turnCallback = turnCallback;
        this.winnerStrategy = winnerStrategy;
    }

    public List<String> start() {
        race();
        return winner();
    }

    private void race() {
        for (int i = 0; i < trial; i++) {
            cars.moveAll();
            turnCallback.accept(cars);
        }
    }

    private List<String> winner() {
        return winnerStrategy.perform(cars);
    }

    private void validateRequiredArguments(Cars cars, Consumer<Cars> turnCallback, WinnerStrategy winnerStrategy) {
        Objects.requireNonNull(cars);
        Objects.requireNonNull(turnCallback);
        Objects.requireNonNull(winnerStrategy);
    }
}
