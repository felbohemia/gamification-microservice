package microservices.book.gamification.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.ChallengeAttemptedEvent;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeAttemptSub {
    private final  ScoreCardGeneratorService scoreCardGeneratorService;

    @RabbitListener(queues = "${amqp.gamification.queue}")
    public void getMessageFromQueue(ChallengeAttemptDTO event){
        try{
             scoreCardGeneratorService.generateScoreAndBadgeCards(event);
             log.info("Attempt with id {} Arrives", event.getUserId());
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
