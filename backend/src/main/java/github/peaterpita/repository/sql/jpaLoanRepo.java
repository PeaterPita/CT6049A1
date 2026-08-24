
package github.peaterpita.repository.sql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface jpaLoanRepo extends JpaRepository<sqlLoanEntity, String> {

    List<sqlLoanEntity> findByUserId(String userId);

    @Query("SELECT loan FROM sqlLoanEntity loan WHERE loan.user.id = :userId "
            + "AND loan.loanDate BETWEEN :beginDate AND :endDate")
    List<sqlLoanEntity> findHistoryBetween(
            @Param("userId") String userId,
            @Param("beginDate") LocalDate beginDate,
            @Param("endDate") LocalDate endDate);

    boolean existsByUserIdAndBookIdAndReturnDateIsNull(String userId, String bookId);
}
