package racinggame.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.strategy.RandomStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Cars 관련 테스트")
public class CarsTest {
    @Test
    void Cars는_참가_신청된_이름을_바탕으로_생성() {
        Cars cars = Cars.of(
                Stream.of("jseo", "glen", "sage")
                        .map(Car::defaultStrategyOf)
                        .collect(Collectors.toList())
        );
        assertThat(cars.registeredCars()).isEqualTo(
                Stream.of("jseo", "glen", "sage")
                        .map(Car::defaultStrategyOf)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void 중복_참여된_자동차들은_RuntimeException을_발생() {
        assertThatThrownBy(() -> Cars.of(
                Stream.of("jseo", "jseo")
                        .map(Car::defaultStrategyOf)
                        .collect(Collectors.toList())
        )).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void moveAll_메서드를_이용하여_자동차들의_전진을_일괄적으로_수행() {
        RandomStrategy randomStrategy = () -> 4;
        Cars cars = Cars.of(
                List.of(
                        Car.of("jseo", randomStrategy),
                        Car.of("glen", randomStrategy),
                        Car.of("sage", randomStrategy)
                )
        );
        cars.moveAll();
        cars.registeredCars().forEach(car -> assertThat(car.offset()).isEqualTo(1));
    }

    @Test
    void 자동차들의_list의_불변성을_확인() {
        Cars cars = Cars.of(
                Stream.of("jseo", "glen", "sage")
                        .map(Car::defaultStrategyOf)
                        .collect(Collectors.toList())
        );
        assertThatThrownBy(() -> cars.registeredCars().add(Car.defaultStrategyOf("fail")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
