package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Parser 관련 테스트")
class ParserTest {
    @ParameterizedTest
    @NullAndEmptySource
    void parser는_null_혹은_빈_문자열을_이용하여_객체_생성_불가(String input) {
        assertThatThrownBy(() -> Parser.of(input)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void of는_input_문자열을_이용하여_Parser_객체를_생성() {
        assertThatCode(() -> Parser.of("1,2,3")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("getParserToNumbersInput")
    void Parser의_parse_메서드는_정수_타입의_list_일급_콜렉션을_반환(String input, List<Integer> list) {
        assertThat(Parser.of(input).parse()).isEqualTo(list);
    }

    private static Stream<Arguments> getParserToNumbersInput() {
        return Stream.of(
                Arguments.of("1,2,3", Arrays.asList(1, 2, 3)),
                Arguments.of("1", List.of(1))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {".", "+", "?", "*", "^", "|", "$"})
    void Parser는_정규표현식_예약어를_입력_값으로_사용_불가(String input) {
        assertThatThrownBy(() -> Parser.of(input)).isInstanceOf(RuntimeException.class);
    }
}