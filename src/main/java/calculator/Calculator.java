package calculator;

import java.util.InputMismatchException;
import java.util.Objects;

public final class Calculator {
    private Calculator() {
        throw new AssertionError("Calculator는 상태를 갖지 않는 유틸 객체입니다.");
    }

    public static int sum(String input) {
        try {
            validate(input);
            new Numbers(new Parser(input));

            Numbers numbers = Numbers.of(Parser.of(input).parse());
            validate(numbers);
            return numbers.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        } catch (NullPointerException | IllegalArgumentException ex) {
            return 0;
        }
    }

    private static void validate(String input) {
        checkNonBlank(Objects.requireNonNull(input));
    }

    private static void validate(Numbers numbers) {
        checkNonNegative(numbers);
    }

    private static void checkNonBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력 값이 존재하지 않습니다.");
        }
    }

    private static void checkNonNegative(Numbers numbers) {
        numbers.values().stream()
                .filter(e -> e < 0)
                .findFirst()
                .ifPresent(e -> {
                    throw new InputMismatchException("입력 값에 음수가 존재합니다.");
                });
    }
}
