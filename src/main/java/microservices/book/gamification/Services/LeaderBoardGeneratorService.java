package microservices.book.gamification.Services;

import microservices.book.gamification.Domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardGeneratorService {
    List<LeaderBoardRow> getLeaderBoard();
}
