package chess.controller.dto;

public class StatusDto {

    private final String team;
    private final String score;

    public StatusDto(String team, String score) {
        this.team = team;
        this.score = score;
    }

    public String getTeam() {
        return team;
    }

    public String getScore() {
        return score;
    }
}