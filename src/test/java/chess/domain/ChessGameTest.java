package chess.domain;

import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameTest {

    @Nested
    @DisplayName("게임이 끝났는지 판단하는 isGameEnd 메서드 테스트")
    class isGameEndTest {

        @Test
        @DisplayName("두 팀의 왕이 모두 죽지 않았으면 false 를 반환한다.")
        void isKingDeadTest1() {
            ChessGame game = ChessGame.create();
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isFalse();
        }

        @Test
        @DisplayName("백 팀의 왕이 죽었으면 true 를 반환한다.")
        void isGameEndTest2() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(0, 1), new Position(0, 2));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 4), new Position(4, 3));
            game.movePiece(new Position(0, 2), new Position(0, 3));
            game.movePiece(new Position(4, 3), new Position(4, 2));
            game.movePiece(new Position(0, 3), new Position(0, 4));
            game.movePiece(new Position(4, 2), new Position(4, 1));
            game.movePiece(new Position(0, 4), new Position(0, 5));
            game.movePiece(new Position(4, 1), new Position(4, 0));
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isTrue();
        }

        @Test
        @DisplayName("흑 팀의 왕이 죽었으면 true 를 반환한다.")
        void isGameEndTest3() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 3), new Position(4, 4));
            game.movePiece(new Position(1, 6), new Position(1, 5));
            game.movePiece(new Position(4, 4), new Position(4, 5));
            game.movePiece(new Position(1, 5), new Position(1, 4));
            game.movePiece(new Position(4, 5), new Position(4, 6));
            game.movePiece(new Position(1, 4), new Position(1, 3));
            game.movePiece(new Position(4, 6), new Position(4, 7));
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isTrue();
        }
    }

    @Nested
    @DisplayName("한 팀의 점수를 계산하는 getTotalScore 메서드 테스트")
    class getTotalScoreTest {

        @Test
        @DisplayName("한 팀의 초기 점수의 합을 구한다.")
        void getTotalScoreTest1() {
            ChessGame game = ChessGame.create();
            double score = game.getTotalScore(Team.WHITE);

            assertThat(score).isEqualTo(38.0);
        }

        @Test
        @DisplayName("같은 세로줄에 같은팀의 폰이 있을 경우 점수의 합을 구한다.")
        void getTotalScoreTest2() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(0, 1), new Position(0, 3));
            game.movePiece(new Position(1, 6), new Position(1, 4));
            game.movePiece(new Position(0, 3), new Position(1, 4));

            double score = game.getTotalScore(Team.WHITE);
            assertThat(score).isEqualTo(38.0 - 1.0);
        }
    }

    @Nested
    @DisplayName("이긴 팀을 반환하는 getWinTeam 메서드 테스트")
    class getWinTeamTest {

        @Test
        @DisplayName("흑 팀 왕이 죽었을 때 백 팀을 반환한다.")
        void getWinTeamTest1() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 3), new Position(4, 4));
            game.movePiece(new Position(1, 6), new Position(1, 5));
            game.movePiece(new Position(4, 4), new Position(4, 5));
            game.movePiece(new Position(1, 5), new Position(1, 4));
            game.movePiece(new Position(4, 5), new Position(4, 6));
            game.movePiece(new Position(1, 4), new Position(1, 3));
            game.movePiece(new Position(4, 6), new Position(4, 7));
            Team winTeam = game.getWinTeam();

            assertThat(winTeam).isEqualTo(Team.WHITE);
        }

        @Test
        @DisplayName("백 팀 왕이 죽었을 때 흑 팀을 반환한다.")
        void getWinTeamTest2() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(0, 1), new Position(0, 2));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 4), new Position(4, 3));
            game.movePiece(new Position(0, 2), new Position(0, 3));
            game.movePiece(new Position(4, 3), new Position(4, 2));
            game.movePiece(new Position(0, 3), new Position(0, 4));
            game.movePiece(new Position(4, 2), new Position(4, 1));
            game.movePiece(new Position(0, 4), new Position(0, 5));
            game.movePiece(new Position(4, 1), new Position(4, 0));
            Team winTeam = game.getWinTeam();

            assertThat(winTeam).isEqualTo(Team.BLACK);
        }

        @Test
        @DisplayName("두 팀의 왕이 모두 살아있는 경우 점수가 백팀이 높다면 백 팀을 반환한다.")
        void getWinTeamTest3() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 3), new Position(4, 4));
            Team winTeam = game.getWinTeam();

            assertThat(winTeam).isEqualTo(Team.WHITE);
        }

        @Test
        @DisplayName("두 팀의 왕이 모두 살아있는 경우 점수가 흑 팀이 높다면 흑 팀을 반환한다.")
        void getWinTeamTest5() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(0, 1), new Position(0, 2));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 4), new Position(4, 3));
            Team winTeam = game.getWinTeam();

            assertThat(winTeam).isEqualTo(Team.BLACK);
        }

        @Test
        @DisplayName("두 팀의 왕이 모두 살아있는 경우 두 팀의 점수가 같으면 무승부다.")
        void getWinTeamTest6() {
            ChessGame game = ChessGame.create();

            Team winTeam = game.getWinTeam();

            assertThat(winTeam).isEqualTo(Team.EMPTY);
        }
    }

    @Nested
    @DisplayName("양 팀을 번갈아 말을 이동시키는 movePiece 메서드 테스트")
    class movePieceTest {

        @Test
        @DisplayName("게임이 시작하면 백 팀의 차례이다.")
        void movePieceTest1() {
            ChessGame game = ChessGame.create();

            assertDoesNotThrow(() -> game.movePiece(new Position(0, 1), new Position(0, 2)));
        }

        @Test
        @DisplayName("게임이 시작하면 흑 팀은 말을 움직일 수 없다.")
        void movePieceTest2() {
            ChessGame game = ChessGame.create();

            assertThatThrownBy(() -> game.movePiece(new Position(0, 6), new Position(0, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("하얀색 기물을 움직여 주세요.");
        }

        @Test
        @DisplayName("백 팀이 말을 움직이면, 다음 차례는 흑 팀이 말을 움직일 수 있다.")
        void movePieceTest3() {
            ChessGame game = ChessGame.create();
            game.movePiece(new Position(0, 1), new Position(0, 2));

            assertDoesNotThrow(() -> game.movePiece(new Position(0, 6), new Position(0, 5)));
        }

        @Test
        @DisplayName("흑 팀이 말을 움직이면, 다음 차례는 흑 팀이 말을 움직일 수 없다.")
        void movePieceTest4() {
            ChessGame game = ChessGame.create();
            game.movePiece(new Position(0, 1), new Position(0, 2));

            assertThatThrownBy(() -> game.movePiece(new Position(0, 2), new Position(0, 3)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("검정색 기물을 움직여 주세요.");
        }
    }
}
