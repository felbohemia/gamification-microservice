package microservices.book.gamification.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.cardID;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "ScoreCard")
//@AllArgsConstructor
@NoArgsConstructor
@Data

public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long cardId;
   private  String userId;
   private int score;
   @EqualsAndHashCode.Exclude
   private LocalDateTime timeCreated;
   
   public ScoreCard(String ucardID, int mscore){
       this(null, ucardID, mscore, LocalDateTime.now());
   }
   public ScoreCard(Long cardID, String usercardID, int score, LocalDateTime time){
      this.cardId = cardID;
      this.userId = usercardID;
      this.score = score;
      this.timeCreated = time;
   }
   
}
