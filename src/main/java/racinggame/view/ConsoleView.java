package racinggame.view;

import racinggame.dto.CarDto;
import racinggame.model.Cars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class ConsoleView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final List<String> roundStatus;

    public static ConsoleView newInstance() {
        return new ConsoleView();
    }

    private ConsoleView() {
        roundStatus = new ArrayList<>();
    }

    public List<String> getCarNames() {
        printCarNamesMessage();
        String names = SCANNER.nextLine();
        return Arrays.stream(names.split(","))
                .collect(Collectors.toList());
    }

    private void printCarNamesMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public int getTrial() {
        printTrialMessage();
        return Integer.parseInt(SCANNER.nextLine());
    }

    private void printTrialMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void recordStatus(Cars cars) {
        StringBuilder sb = new StringBuilder();
        cars.registeredCars().stream()
                .map(CarDto::from)
                .forEach(e -> sb.append(e.status()).append('\n'));
        roundStatus.add(sb.toString());
    }

    public void printResult(List<String> winners) {
        printRoundMessage();
        printWinnerMessage(winners);
    }

    private void printRoundMessage() {
        System.out.println("\n실행 결과");
        roundStatus.forEach(System.out::println);
    }

    private void printWinnerMessage(List<String> winners) {
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }
}