package racinggame.strategy;

import racinggame.model.Cars;

import java.util.List;

public interface WinnerStrategy {
    List<String> perform(Cars cars);
}
