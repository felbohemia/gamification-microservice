package microservices.book.gamification.Repositories;

import microservices.book.gamification.Domain.LeaderBoardRow;
import microservices.book.gamification.Domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends CrudRepository<ScoreCard, Long> {
    /*
       JPQL(Java persistence Query language)
       the @Query annotation makes it possible for us to run
       sql in the place of a method
     */
    @Query( value = "select sum(u.score) from score_card u where u.user_id = ?1", nativeQuery = true)
    Optional<Long> totalScoreOfAUser(String userId);

      // Query method... its implemented by JPA itself
    List<ScoreCard> findByUserId(String userId);

    /*
   JPQL(Java persistence Query language)
   the @Query annotation makes it possible for us to run
   sql in the place of a method
 */
    /*@Query(value = "select sc.user_id, sum(sc.score) from score_card sc" +
                    " group by sc.user_id order by sum(sc.score) Desc limit 10",
            nativeQuery = true)*/
    @Query("SELECT NEW microservices.book.gamification.Domain.LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM ScoreCard s " +
            "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();
}
