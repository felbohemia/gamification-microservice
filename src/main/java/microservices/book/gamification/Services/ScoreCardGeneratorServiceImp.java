package microservices.book.gamification.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.Domain.BadgeCard;
import microservices.book.gamification.Domain.BadgeType;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.ScoreCard;
import microservices.book.gamification.Repositories.BadgeRepository;
import microservices.book.gamification.Repositories.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
/*
  creates a constructor with all fields as parameters
  each of them must be declared final as per lombok's specification
  Lombok is a code generation library
 */
@RequiredArgsConstructor
@Slf4j

public class ScoreCardGeneratorServiceImp implements  ScoreCardGeneratorService{
    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;
    private final List<BadgeGeneratorService> badgeGenerators;
    private final static int POINT = 10;

    @Override
    public void generateScoreAndBadgeCards(ChallengeAttemptDTO attemptDTO) {
          boolean isCorrect = (attemptDTO.getFactorA() * attemptDTO.getFactorB() == attemptDTO.getGuess());
          Long total = 0l;
          if(isCorrect){
              ScoreCard scoreCard = new ScoreCard(attemptDTO.getUserId(), POINT);
              scoreRepository.save(scoreCard);
              Optional<Long> totalScore = scoreRepository.totalScoreOfAUser(attemptDTO.getUserId());
              if(totalScore.isEmpty())
                  return;

              total = totalScore.get();
              List<ScoreCard> scoreCardList = scoreRepository.findByUserId(attemptDTO.getUserId());
              List<BadgeCard> badgeCardList = getBadgeCards(total,scoreCardList, attemptDTO);
              badgeRepository.saveAll(badgeCardList);

          }else{
              log.info("------------->Wrong Attempt<-------------------");
          }
    }

    private List<BadgeCard> getBadgeCards(Long total, List<ScoreCard> scoreCardList, ChallengeAttemptDTO challenge){
        List<BadgeCard> alreadyObtainedBadges = badgeRepository.allBadgeBelongingToAUserDesc(challenge.getUserId());

        List<String> alreadyBageStrings = alreadyObtainedBadges.stream().map(ad->ad.getBadge()).collect(Collectors.toList());

        log.info("\n\n>>>> alreadyObtainedBages contains {} >>>>>>>>\n\n", alreadyObtainedBadges.toString());
        List<String> generatedBadges = badgeGenerators.stream()
                .map(bG -> bG.assignBadgeToUser(total,scoreCardList,challenge))
                .flatMap(Optional::stream).map(bt->bt.getDescription()).collect(Collectors.toList());



        log.info("\n\n>>>> generated {} >>>>>>>>\n\n", generatedBadges.toString());

        List<BadgeCard> newBadges = generatedBadges.stream().filter(fi-> !alreadyObtainedBadges.stream().map(bd->bd.getBadge()).collect(Collectors.toList()).contains(fi))
                .map(bg-> new BadgeCard(challenge.getUserId(),bg))
                .collect(Collectors.toList());
        log.info("\n\n>>>> newBadges contains {} >>>>>>>>\n\n", newBadges.toString());
        return newBadges;
    }
}
