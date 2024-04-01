package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Numbers 관련 테스트")
class NumbersTest {
    @Test
    void list_메서드는_Numbers가_보유하고_있는_리스트를_반환() {
        assertThat(Numbers.of(List.of(1, 2, 3)).values()).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void 동일한_list를_보유하고_있는_Numbers의_동치를_확인() {
        assertThat(Numbers.of(List.of(1, 2, 3))).isEqualTo(Numbers.of(List.of(1, 2, 3)));
    }

    @Test
    void 외부로부터_주입_받은_list에_종속되지_않음을_확인() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        Numbers numbers = Numbers.of(list);
        list.set(0, 100);
        list.add(1000);
        assertThat(numbers.values()).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void Numbers의_list_자체의_방어적_복사를_확인() {
        Numbers numbers = Numbers.of(new ArrayList<>(List.of(1, 2, 3)));
        assertThatCode(() -> numbers.values().set(0, 100)).doesNotThrowAnyException();
        assertThatCode(() -> numbers.values().add(1000)).doesNotThrowAnyException();
    }
}