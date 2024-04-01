package racinggame.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racinggame.model.Car;
import racinggame.model.Cars;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DefaultWinnerStrategy 관련 테스트")
class DefaultWinnerStrategyTest {
    @ParameterizedTest
    @MethodSource("getStrategyArguments")
    void 최종_우승자의_유효성을_확인(List<Car> cars, List<String> winners) {
        DefaultWinnerStrategy strategy = DefaultWinnerStrategy.newInstance();
        assertThat(strategy.perform(Cars.of(cars))).isEqualTo(winners);
    }

    private static Stream<Arguments> getStrategyArguments() {
        return Stream.of(
                Arguments.of(uniqueWinner(), List.of("jseo")),
                Arguments.of(duplicatedWinner(), List.of("jseo", "sage"))
        );
    }

    private static List<Car> uniqueWinner() {
        return List.of(
                Car.defaultStrategyOf("jseo", 4),
                Car.defaultStrategyOf("sage", 3),
                Car.defaultStrategyOf("glen", 2)
        );
    }

    private static List<Car> duplicatedWinner() {
        return List.of(
                Car.defaultStrategyOf("jseo", 4),
                Car.defaultStrategyOf("sage", 4),
                Car.defaultStrategyOf("glen", 3)
        );
    }
}