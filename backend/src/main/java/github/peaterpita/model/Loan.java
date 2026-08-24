
package github.peaterpita.model;

import java.time.LocalDate;

public class Loan {
    private String id;
    private String userId;
    private String bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    private Double fineAmount;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public Double getFineAmount() {
        return fineAmount;
    }

    // ###########
    // # Setters #
    // ###########

    public void setId(String Id) {
        this.id = Id;
    }

    public void setBookId(String bookId) {

        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFineAmount(Double amount) {
        this.fineAmount = amount;
    }

}
