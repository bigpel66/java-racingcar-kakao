package racinggame.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("DefaultRandomStrategy 관련 테스트")
class DefaultRandomStrategyTest {
    @Test
    void iteration_100_000_000회_테스트() {
        DefaultRandomStrategy strategy = DefaultRandomStrategy.newInstance();
        assertThatCode(() ->
                IntStream.range(0, 100_000_000)
                        .map(e -> strategy.perform())
                        .filter(e -> e > 9)
                        .findFirst()
                        .ifPresent(e -> {
                            throw new IllegalStateException(e + " 값이 검출되었습니다.");
                        })
        ).doesNotThrowAnyException();
    }
}