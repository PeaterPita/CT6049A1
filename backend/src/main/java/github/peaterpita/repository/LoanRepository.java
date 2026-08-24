
package github.peaterpita.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import github.peaterpita.model.Loan;

public interface LoanRepository {
    Loan save(Loan loan);

    Optional<Loan> findById(String loanId);

    List<Loan> findByUserId(String userId);

    List<Loan> loanHistoryBetweenDates(String userId, LocalDate beginDate, LocalDate endDate);

    boolean doesUserHaveActiveLoanForBookId(String userId, String bookId);

}
