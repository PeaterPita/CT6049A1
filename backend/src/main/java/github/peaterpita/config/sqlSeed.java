package github.peaterpita.config;

import java.time.LocalDate;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import github.peaterpita.model.*;
import github.peaterpita.repository.*;

@Configuration
@Profile("sql")
public class sqlSeed {

    @Bean
    CommandLineRunner initSqlData(UserRepository userRepo, BookRepository bookRepo, LoanRepository loanRepo) {
        return args -> {

            if (bookRepo.findAll().isEmpty()) {
                // #########
                // # Users #
                // #########
                User USER = new User();
                USER.setUsername("user");
                USER.setPassword("password");
                USER.setCreatedAt(LocalDateTime.now());

                User SQL = new User();
                SQL.setUsername("sql");
                SQL.setPassword("sql");
                SQL.setCreatedAt(LocalDateTime.now());

                USER = userRepo.save(USER);
                userRepo.save(SQL);

                // #########
                // # Books #
                // #########
                Book LOTR = new Book();
                LOTR.setIsbn("9780261103573");
                LOTR.setTitle("The Lord of the Rings");
                LOTR.setAuthor("J.R.R Tolkien");
                LOTR.setCopiesLeft(10);

                Book EJ = new Book();
                EJ.setIsbn("9780134685991");
                EJ.setTitle("Effective Java");
                EJ.setAuthor("Joshua Bloch");
                EJ.setCopiesLeft(3);

                Book ITENDS = new Book();
                ITENDS.setIsbn("9781501110368");
                ITENDS.setTitle("It Ends With Us");
                ITENDS.setAuthor("Colleen Hoover");
                ITENDS.setCopiesLeft(0);

                Book TWISTED = new Book();
                TWISTED.setIsbn("9781087939278");
                TWISTED.setTitle("Twisted Love");
                TWISTED.setAuthor("Ana Huang");
                TWISTED.setCopiesLeft(2);

                Book IT = new Book();
                IT.setIsbn("9780451159274");
                IT.setTitle("It");
                IT.setAuthor("Stephen King");
                IT.setCopiesLeft(11);

                Book GOT = new Book();
                GOT.setIsbn("9780007548231");
                GOT.setTitle("A Game of Thrones");
                GOT.setAuthor("George R. R. Martin");
                GOT.setCopiesLeft(3);

                Book HUNGER = new Book();
                HUNGER.setIsbn("9781407135397");
                HUNGER.setTitle("The Hunger Games");
                HUNGER.setAuthor("Suzanne Collins");
                HUNGER.setCopiesLeft(7);

                LOTR = bookRepo.save(LOTR);
                EJ = bookRepo.save(EJ);
                ITENDS = bookRepo.save(ITENDS);
                TWISTED = bookRepo.save(TWISTED);
                IT = bookRepo.save(IT);
                GOT = bookRepo.save(GOT);
                HUNGER = bookRepo.save(HUNGER);

                // #########
                // # Loans #
                // #########
                String userId = USER.getId();

                Loan loanA = new Loan();
                loanA.setUserId(userId);
                loanA.setBookId(ITENDS.getId());
                loanA.setLoanDate(LocalDate.now().minusDays(21));
                loanA.setDueDate(LocalDate.now().minusDays(7));
                loanA.setReturnDate(LocalDate.now().minusDays(3));
                loanA.setFineAmount(4 * 1.50);
                loanA.setStatus("PAID");

                Loan loanB = new Loan();
                loanB.setUserId(userId);
                loanB.setBookId(IT.getId());
                loanB.setLoanDate(LocalDate.now().minusDays(67));
                loanB.setDueDate(LocalDate.now().minusDays(53));
                loanB.setReturnDate(LocalDate.now().minusDays(53));
                loanB.setStatus("RETURNED");

                Loan loanC = new Loan();
                loanC.setUserId(userId);
                loanC.setBookId(IT.getId());
                loanC.setLoanDate(LocalDate.now().minusDays(53));
                loanC.setDueDate(LocalDate.now().minusDays(39));
                loanC.setReturnDate(LocalDate.now().minusDays(33));
                loanC.setFineAmount(6 * 1.50);
                loanC.setStatus("PAID");

                Loan loanD = new Loan();
                loanD.setUserId(userId);
                loanD.setBookId(GOT.getId());
                loanD.setLoanDate(LocalDate.now().minusDays(3));
                loanD.setDueDate(LocalDate.now().plusDays(11));
                loanD.setStatus("BORROWED");

                Loan loanE = new Loan();
                loanE.setUserId(userId);
                loanE.setBookId(EJ.getId());
                loanE.setLoanDate(LocalDate.now());
                loanE.setDueDate(LocalDate.now().plusDays(14));
                loanE.setStatus("BORROWED");

                Loan loanF = new Loan();
                loanF.setUserId(userId);
                loanF.setBookId(LOTR.getId());
                loanF.setLoanDate(LocalDate.now().minusDays(17));
                loanF.setDueDate(LocalDate.now().minusDays(3));
                loanF.setStatus("BORROWED");
                loanRepo.save(loanA);
                loanRepo.save(loanB);
                loanRepo.save(loanC);
                loanRepo.save(loanD);
                loanRepo.save(loanE);
                loanRepo.save(loanF);

            }
            ;
        };
    }
}
