package github.peaterpita.repository.mongo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface mongoLoanRepo extends MongoRepository<mongoLoanDoc, String> {

    List<mongoLoanDoc> findByUserId(String userId);

    @Query("{ 'userId': ?0, 'loanDate': {$gte: ?1, $lt: ?2} }")
    List<mongoLoanDoc> findHistory(String userId, LocalDate beginDate, LocalDate endDate);

    boolean existsByUserIdAndBookIdAndReturnDateIsNull(String userId, String bookId);
}
