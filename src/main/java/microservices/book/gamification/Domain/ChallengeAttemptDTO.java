package microservices.book.gamification.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ChallengeAttemptDTO {
    private int factorA;
    private int factorB;
    private String userId;
    private int guess;
    //private boolean correct;

    public int getGuess() {
        return guess;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getFactorA() {
        return factorA;
    }

    public String getUserId() {
        return userId;
    }
}
