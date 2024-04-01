package racinggame.strategy;

import racinggame.model.Car;
import racinggame.model.Cars;

import java.util.List;
import java.util.stream.Collectors;

public final class DefaultWinnerStrategy implements WinnerStrategy {
    public static DefaultWinnerStrategy newInstance() {
        return new DefaultWinnerStrategy();
    }

    private DefaultWinnerStrategy() {
    }

    @Override
    public List<String> perform(Cars cars) {
        int maxOffset = cars.registeredCars().stream()
                .mapToInt(Car::offset)
                .max()
                .orElse(0);
        return cars.registeredCars().stream()
                .filter(e -> e.offset() == maxOffset)
                .map(Car::name)
                .collect(Collectors.toUnmodifiableList());
    }
}
