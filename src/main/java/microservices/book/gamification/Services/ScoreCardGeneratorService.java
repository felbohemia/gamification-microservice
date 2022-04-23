package microservices.book.gamification.Services;

import microservices.book.gamification.Domain.ChallengeAttemptDTO;

public interface ScoreCardGeneratorService {
    void generateScoreAndBadgeCards(ChallengeAttemptDTO attemptDTO);
}
