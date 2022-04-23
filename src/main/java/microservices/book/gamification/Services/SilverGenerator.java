package microservices.book.gamification.Services;

import microservices.book.gamification.Domain.BadgeCard;
import microservices.book.gamification.Domain.BadgeType;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.ScoreCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SilverGenerator implements BadgeGeneratorService{

    @Override
    public BadgeType getBadgeType() {
        return BadgeType.SILVER;
    }

    @Override
    public Optional<BadgeType> assignBadgeToUser(Long total, List<ScoreCard> badgeCards, ChallengeAttemptDTO attemptDTO) {
        return total > 100 ? Optional.of(BadgeType.SILVER): Optional.empty();
    }
}
