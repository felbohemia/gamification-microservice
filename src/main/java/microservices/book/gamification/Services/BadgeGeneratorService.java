package microservices.book.gamification.Services;

import microservices.book.gamification.Domain.BadgeCard;
import microservices.book.gamification.Domain.BadgeType;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface BadgeGeneratorService {

    BadgeType getBadgeType();
    Optional<BadgeType> assignBadgeToUser(Long total, List<ScoreCard> badgeCards, ChallengeAttemptDTO attemptDTO);
}
