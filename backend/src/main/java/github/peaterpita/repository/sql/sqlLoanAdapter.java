package github.peaterpita.repository.sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.Loan;
import github.peaterpita.repository.LoanRepository;

@Repository
@Profile("sql")
public class sqlLoanAdapter implements LoanRepository {

    private final jpaLoanRepo jpaRepo;

    private final jpaUserRepo userRepo;
    private final jpaBookRepo bookRepo;

    public sqlLoanAdapter(jpaLoanRepo loanRepo,
            jpaUserRepo userRepo,
            jpaBookRepo bookRepo) {
        this.jpaRepo = loanRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Loan save(Loan loan) {
        sqlLoanEntity entity = new sqlLoanEntity(loan);

        sqlUserEntity userRef = userRepo.getReferenceById(loan.getUserId());
        sqlBookEntity bookRef = bookRepo.getReferenceById(loan.getBookId());

        entity.setUser(userRef);
        entity.setBook(bookRef);

        return jpaRepo.save(entity).toLoan();
    }

    @Override
    public List<Loan> findByUserId(String userId) {
        return jpaRepo.findByUserId(userId).stream()
                .map(sqlLoanEntity::toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> loanHistoryBetweenDates(String userId,
            LocalDate beginDate,
            LocalDate endDate) {
        return jpaRepo.findHistoryBetween(userId, beginDate, endDate)
                .stream()
                .map(sqlLoanEntity::toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findById(String loanId) {
        return jpaRepo.findById(loanId).map(sqlLoanEntity::toLoan);
    }

    @Override
    public boolean doesUserHaveActiveLoanForBookId(String userId, String bookId) {
        return jpaRepo.existsByUserIdAndBookIdAndReturnDateIsNull(userId, bookId);
    }

}
