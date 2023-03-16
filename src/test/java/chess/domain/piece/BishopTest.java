package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.RelativePosition;
import chess.domain.Team;

class BishopTest {

	@ParameterizedTest
	@DisplayName("비숍은 팀과 관계 없이 대각선으로 거리 제한 없이 이동할 수 있다.")
	@CsvSource({"1,1","1,-1","-1,-1","-1,1","100,100","100,-100","-100,-100","-100,100"})
	void bishopCanMoveDiagonalDirectionTest(int x, int y) {
		Bishop whiteBishop = new Bishop(Team.WHITE);
		Bishop blackBishop = new Bishop(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteBishop.isMobile(relativePosition));
		assertTrue(blackBishop.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("비숍은 팀과 관계 없이 십자가 방향으로 이동할 수 없다.")
	@CsvSource({"0,1","1,0","0,-1","-1,0"})
	void bishopCannotMoveCrossDirectionTest(int x, int y) {
		Bishop whiteBishop = new Bishop(Team.WHITE);
		Bishop blackBishop = new Bishop(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertFalse(whiteBishop.isMobile(relativePosition));
		assertFalse(blackBishop.isMobile(relativePosition));
	}
}