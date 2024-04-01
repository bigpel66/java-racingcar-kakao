package racinggame.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.model.Car;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CarDto 관련 테스트")
class CarDtoTest {
    @Test
    void 자동차_Dto의_상태는_이름과_offset만큼의_bar로_구성() {
        Car car = Car.of("jseo", () -> 4);
        car.move();
        assertThat(CarDto.from(car).status()).isEqualTo("jseo : -");
    }
}