package microservices.book.gamification.Repositories;

import microservices.book.gamification.Domain.BadgeCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository  extends CrudRepository<BadgeCard, Long> {
    @Query(value = "select * from badge_card U where U.user_id = ?1 order by U.time_earned Desc",nativeQuery = true)
    List<BadgeCard> allBadgeBelongingToAUserDesc(String userId);
}
