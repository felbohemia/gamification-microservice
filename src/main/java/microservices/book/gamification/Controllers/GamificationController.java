package microservices.book.gamification.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.Domain.ChallengeAttemptDTO;
import microservices.book.gamification.Domain.LeaderBoardRow;
import microservices.book.gamification.Repositories.BadgeRepository;
import microservices.book.gamification.Services.LeaderBoardGeneratorService;
import microservices.book.gamification.Services.ScoreCardGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempt")
@Slf4j
public class GamificationController {
    private final ScoreCardGeneratorService scoreCardGeneratorService;
    private final LeaderBoardGeneratorService leaderBoardGeneratorService;

    /*@PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postAttempt( @RequestBody @Valid  ChallengeAttemptDTO challengeAttemptDTO){
        scoreCardGeneratorService.generateScoreAndBadgeCards(challengeAttemptDTO);
    }*/
    @GetMapping("/leaders")
    public List<LeaderBoardRow> getLeaders(){
        return leaderBoardGeneratorService.getLeaderBoard();
    }
}
