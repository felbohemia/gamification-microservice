package microservices.book.gamification.Services;

import microservices.book.gamification.Domain.BadgeCard;
import microservices.book.gamification.Domain.BadgeType;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.ScoreCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirstWinGenerator implements BadgeGeneratorService{

    @Override
    public BadgeType getBadgeType() {
        return BadgeType.FIRSTWIN;
    }

    @Override
    public Optional<BadgeType> assignBadgeToUser(Long total, List<ScoreCard> badgeCards, ChallengeAttemptDTO attemptDTO) {
        return  badgeCards.size() == 1 ? Optional.of(BadgeType.FIRSTWIN): Optional.empty();
    }
}
