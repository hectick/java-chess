package chess.view;

import chess.controller.dto.BoardDto;
import chess.controller.dto.ResultDto;
import chess.controller.dto.StatusDto;

import java.util.List;

public class OutputView {

    public static void printInitialMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다.\n"
                        + "> 게임 시작 : start\n"
                        + "> 게임 종료 : end\n"
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(BoardDto boardDto) {
        boardDto.getBoard().forEach(line -> System.out.println(printLine(line)));
        System.out.println();
    }

    private static String printLine(List<String> line) {
        return String.join("", line);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printStatus(StatusDto statusDto) {
        String team = statusDto.getTeam();
        String score = statusDto.getScore();
        System.out.println("> " + team + ": " + score + "점");
    }

    public static void printWinTeam(ResultDto resultDto) {
        String winTeam = resultDto.getWinTeam();
        if (winTeam.isBlank()) {
            System.out.println("> 무승부 입니다!");
            return;
        }
        System.out.println("> " + winTeam + "이 이겼습니다!");
    }

    public static void printFinishMessage() {
        System.out.println("> 게임이 종료되었습니다.");
    }
}
