package calculator;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Parser {
    private static final Pattern FORBIDDEN_DELIMITER_PATTERN = Pattern.compile("\\.|\\?|\\+|\\*|\\^|\\$|\\|");
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final int DELIMITER_CAPTURE_POSITION = 1;
    private static final int TARGET_CAPTURE_POSITION = 2;

    private final String input;
    private final String target;
    private final String delimiter;

    public static Parser of(String input) {
        return new Parser(input);
    }

    private Parser(String input) {
        validateInput(input);
        this.input = input;
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            this.delimiter = DEFAULT_DELIMITER;
            target = input;
            return;
        }
        Matcher m = getCustomMatcher();
        this.delimiter = m.group(DELIMITER_CAPTURE_POSITION);
        this.target = m.group(TARGET_CAPTURE_POSITION);
    }

    private void validateInput(String input) {
        checkInputBlank(input);
        checkInputContainsRegex(input);
    }

    private void checkInputBlank(String input) {
        Objects.requireNonNull(input);
        if (input.isBlank()) {
            throw new IllegalArgumentException("파싱 대상 문자열이 비어있습니다.");
        }
    }

    private void checkInputContainsRegex(String input) {
        Matcher matcher = FORBIDDEN_DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            throw new IllegalArgumentException("입력 값에는 정규표현식 예약어를 사용할 수 없습니다.");
        }
    }

    private Matcher getCustomMatcher() {
        Matcher m = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (!m.find()) {
            throw new InputMismatchException("커스텀 구분자로 입력 값을 판별할 수 없습니다.");
        }
        return m;
    }

    public List<Integer> parse() {
        try {
            return Arrays.stream(target.split(delimiter))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            throw new InputMismatchException("파싱 대상 문자열은 숫자가 아닙니다.");
        }
    }
}
