package github.peaterpita.repository.mongo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import github.peaterpita.model.Loan;
import github.peaterpita.repository.LoanRepository;

@Repository
@Profile("mongo")
public class mongoLoanAdapter implements LoanRepository {

    private final mongoLoanRepo loanRepo;

    public mongoLoanAdapter(mongoLoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Override
    public Loan save(Loan loan) {
        mongoLoanDoc document = new mongoLoanDoc(loan);
        return loanRepo.save(document).toLoan();
    }

    @Override
    public List<Loan> findByUserId(String userId) {
        return loanRepo.findByUserId(userId).stream()
                .map(mongoLoanDoc::toLoan).collect(Collectors.toList());
    }

    @Override
    public List<Loan> loanHistoryBetweenDates(
            String userId,
            LocalDate beginDate,
            LocalDate endDate) {
        return loanRepo.findHistory(userId, beginDate, endDate).stream()
                .map(mongoLoanDoc::toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findById(String loanId) {
        return loanRepo.findById(loanId).map(mongoLoanDoc::toLoan);
    }

    @Override
    public boolean doesUserHaveActiveLoanForBookId(String userId, String bookId) {
        return loanRepo.existsByUserIdAndBookIdAndReturnDateIsNull(userId, bookId);
    }
}
