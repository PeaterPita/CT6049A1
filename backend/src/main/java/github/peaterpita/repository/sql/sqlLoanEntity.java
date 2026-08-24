package github.peaterpita.repository.sql;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.JoinColumn;

import github.peaterpita.model.Loan;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class sqlLoanEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private sqlUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private sqlBookEntity book;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private String status;
    private Double fineAmount;

    public sqlLoanEntity() {
    }

    public sqlLoanEntity(Loan loan) {
        if (loan.getId() != null) {
            this.id = loan.getId();
        } else {
            this.id = UUID.randomUUID().toString();
        }

        this.loanDate = loan.getLoanDate();
        this.dueDate = loan.getDueDate();
        this.returnDate = loan.getReturnDate();

        this.status = loan.getStatus();
        this.fineAmount = loan.getFineAmount();

    }

    public Loan toLoan() {
        Loan loan = new Loan();
        loan.setId(this.id);

        if (this.user != null)
            loan.setUserId(this.user.getId());

        if (this.book != null)
            loan.setBookId(this.book.getId());

        loan.setDueDate(this.dueDate);
        loan.setLoanDate(this.loanDate);
        loan.setReturnDate(this.returnDate);

        loan.setStatus(this.status);
        loan.setFineAmount(this.fineAmount);

        return loan;
    }

    public void setUser(sqlUserEntity user) {
        this.user = user;
    }

    public void setBook(sqlBookEntity book) {
        this.book = book;
    }
}
