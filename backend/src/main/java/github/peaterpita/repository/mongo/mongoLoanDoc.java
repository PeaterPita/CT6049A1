package github.peaterpita.repository.mongo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import github.peaterpita.model.Loan;

@Document(collection = "loans")
public class mongoLoanDoc {
    @Id
    private String id;
    private String userId;
    private String bookId;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    private Double fineAmount;

    public mongoLoanDoc() {

    }

    public mongoLoanDoc(Loan loan) {
        this.id = loan.getId();
        this.userId = loan.getUserId();
        this.bookId = loan.getBookId();

        this.loanDate = loan.getLoanDate();
        this.dueDate = loan.getDueDate();
        this.returnDate = loan.getReturnDate();
        this.status = loan.getStatus();
        this.fineAmount = loan.getFineAmount();
    }

    public Loan toLoan() {
        Loan loan = new Loan();

        loan.setId(this.id);
        loan.setBookId(this.bookId);
        loan.setUserId(this.userId);
        loan.setDueDate(this.dueDate);
        loan.setLoanDate(this.loanDate);
        loan.setReturnDate(this.returnDate);

        loan.setStatus(this.status);
        loan.setFineAmount(this.fineAmount);

        return loan;
    }

}
