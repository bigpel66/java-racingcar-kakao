package racinggame;

import racinggame.controller.RacingGame;
import racinggame.model.Car;
import racinggame.model.Cars;
import racinggame.strategy.DefaultRandomStrategy;
import racinggame.strategy.DefaultWinnerStrategy;
import racinggame.view.ConsoleView;

import java.util.stream.Collectors;

public final class Application {
    public static void main(String[] args) {
        ConsoleView view = ConsoleView.newInstance();
        RacingGame game = RacingGame.of(
                Cars.of(view.getCarNames().stream()
                        .map(e -> Car.of(e, DefaultRandomStrategy.newInstance()))
                        .collect(Collectors.toList())),
                view.getTrial(),
                view::recordStatus,
                DefaultWinnerStrategy.newInstance()
        );
        view.printResult(game.start());
    }
}