package chess.view;

import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCommand() {
        String input = scanner.nextLine();
        checkBlank(input);
        return List.of(input.split(" "));
    }


    private static void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
