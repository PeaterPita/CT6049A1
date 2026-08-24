package github.peaterpita.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import github.peaterpita.dto.BookDto;
import github.peaterpita.dto.LoanDto;
import github.peaterpita.model.Book;
import github.peaterpita.model.Loan;
import github.peaterpita.repository.BookRepository;
import github.peaterpita.repository.LoanRepository;
import github.peaterpita.repository.UserRepository;

@Service
public class LoanService {
    private final LoanRepository loanRepo;
    private final BookRepository bookRepo;
    private static final double FINE_ADDITION = 1.50;

    public LoanService(LoanRepository loanRepo,
            BookRepository bookRepo,
            UserRepository userRepo) {

        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
    }

    private LoanDto toDto(Loan loan) {
        LoanDto dto = new LoanDto();

        dto.id = loan.getId();
        dto.loanDate = loan.getLoanDate();
        dto.returnDate = loan.getReturnDate();
        dto.dueDate = loan.getDueDate();

        if (loan.getStatus().equals("BORROWED")
                && LocalDate.now().isAfter(loan.getDueDate())) {
            dto.status = "OVERDUE";

            long lateAmount = ChronoUnit.DAYS.between(
                    loan.getDueDate(),
                    LocalDate.now());
            dto.fine = lateAmount * FINE_ADDITION;
        } else {
            dto.status = loan.getStatus();
            dto.fine = loan.getFineAmount();
        }

        if (loan.getBookId() != null) {
            bookRepo.findById(loan.getBookId()).ifPresent(book -> {
                dto.book = BookDto.toDto(book);
            });
        }
        if (dto.book == null) {
            dto.book = new BookDto();
            dto.book.title = "ERROR";
            dto.book.author = "ERROR";
        }
        return dto;
    }

    public List<LoanDto> findLoansByUser(String userId) {
        List<Loan> loans = loanRepo.findByUserId(userId);
        return loans.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<LoanDto> getLoanHistory(String userId,
            LocalDate beginDate,
            LocalDate endDate) {
        List<Loan> loans = loanRepo.loanHistoryBetweenDates(userId, beginDate, endDate);
        return loans.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Loan borrowBook(String userId, String bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("No Book"));

        if (book.getCopiesLeft() <= 0) {
            throw new RuntimeException("none left");
        }

        if (loanRepo.doesUserHaveActiveLoanForBookId(userId, bookId)) {
            throw new RuntimeException("You already have this book out on loan");
        }

        Loan loan = new Loan();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setStatus("BORROWED"); // TODO: USE ENUM
        loan.setFineAmount(0.0);

        book.setCopiesLeft(book.getCopiesLeft() - 1);
        bookRepo.save(book);

        return loanRepo.save(loan);
    }

    public Loan returnBook(String userId, String loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("No Loan"));

        if (!(loan.getStatus().equals("BORROWED"))) {
            throw new RuntimeException("Already paid or returned or lost");
        }

        // ###########################################################
        // # Ensure DueDate constraints on trusted backend server.
        // # Prevents untrusted users from bypassing payment
        // #requirements by returning book as if it was a normal book.
        // ###########################################################
        if (LocalDate.now().isAfter(loan.getDueDate())) {
            throw new RuntimeException("Book cannot be returned");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        Book book = bookRepo.findById(loan.getBookId())
                .orElseThrow(() -> new RuntimeException("Bad error"));
        book.setCopiesLeft(
                (book.getCopiesLeft() == null ? 0 : book.getCopiesLeft()) + 1);

        bookRepo.save(book);
        return loanRepo.save(loan);

    }

    public Loan payFine(String userId, String loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan was not found"));

        if (!loan.getUserId()
                .equals(userId))
            throw new RuntimeException(
                    "Cannot pay off another users loan");

        // ###########################################################
        // # Although present on the loan, recalculate the fine
        // # amount needed. Between user clicking pay and
        // # them actually getting here, time could have ticked
        // # over to a new day. Should really compare
        // # new price vs the one stored on the loan.
        // ###########################################################
        long lateAmount = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
        double fine = lateAmount * FINE_ADDITION;

        loan.setFineAmount(fine);
        loan.setReturnDate(LocalDate.now());
        loan.setStatus("PAID");

        // TODO: Change to method DRY
        Book book = bookRepo.findById(loan.getBookId())
                .orElseThrow(() -> new RuntimeException("Bad error"));
        book.setCopiesLeft(
                (book.getCopiesLeft() == null ? 0 : book.getCopiesLeft()) + 1);

        bookRepo.save(book);
        return loanRepo.save(loan);

    }

}
