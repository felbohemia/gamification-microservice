package microservices.book.gamification.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import lombok.With;

import java.util.List;

@AllArgsConstructor
/*
Makes class LeaderBoardRow final as well fields in it
and also makes the fields private by default
 */

@Getter
public class LeaderBoardRow {
    private String  userId;
    private Long totalScore;
    private List<String> badges;
    public LeaderBoardRow(String id, Long score){
        this(id, score , List.of());
    }

    public String getUserId() {
        return userId;
    }

    public void setBadges(List<String> badgeCards) {
        this.badges = badgeCards;
    }
}
