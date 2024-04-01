package racinggame.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Cars {
    private final List<Car> registeredCars;

    public static Cars of(List<Car> cars) {
        return new Cars(cars);
    }

    private Cars(List<Car> cars) {
        validateCars(cars);
        registeredCars = cars.stream().collect(Collectors.toUnmodifiableList());
    }

    private void validateCars(List<Car> cars) {
        Set<Car> setForDuplicationCheck = new HashSet<>(cars);
        if (cars.size() != setForDuplicationCheck.size()) {
            throw new IllegalStateException("중복 참여된 자동차가 있습니다.");
        }
    }

    public List<Car> registeredCars() {
        return registeredCars;
    }

    public void moveAll() {
        registeredCars.forEach(Car::move);
    }
}
