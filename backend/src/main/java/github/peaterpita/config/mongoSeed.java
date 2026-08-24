package github.peaterpita.config;

import github.peaterpita.model.*;
import github.peaterpita.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Profile("mongo")
public class mongoSeed {

    // ###########################################################
    // # Data population function #
    // # for MongoDB #
    // # Using the same Service and Repository methods used in #
    // # real Use #
    // ###########################################################
    @Bean
    CommandLineRunner initMongoData(UserRepository userRepo, BookRepository bookRepo, LoanRepository loanRepo) {
        return args -> {
            if (bookRepo.findAll().isEmpty()) {

                User USER = new User();
                USER.setUsername("user");
                USER.setPassword("password");
                USER.setCreatedAt(LocalDateTime.now());

                User MONGO = new User();
                MONGO.setUsername("mongo");
                MONGO.setPassword("mongo");
                MONGO.setCreatedAt(LocalDateTime.now());

                USER = userRepo.save(USER);
                userRepo.save(MONGO);

                // #########################################
                // # Populate Books Document with 10 books #
                // #########################################
                Book MDB = new Book();
                MDB.setIsbn("978-1491954461");
                MDB.setTitle("MongoDB: The Definitive Guide");
                MDB.setAuthor("Shannon Bradshaw");
                MDB.setCopiesLeft(10);

                Book CCNA = new Book();
                CCNA.setIsbn("978-0135792735");
                CCNA.setTitle("CCNA 200-301 Offical Cert Guide, Volume 1");
                CCNA.setAuthor("Wendell Odom");
                CCNA.setCopiesLeft(3);

                Book FAH = new Book();
                FAH.setIsbn("978-0006546061");
                FAH.setTitle("Fahrenheit 451");
                FAH.setAuthor("Ray Bradbury");
                FAH.setCopiesLeft(451);

                Book IT = new Book();
                IT.setIsbn("9780451159274");
                IT.setTitle("It");
                IT.setAuthor("Stephen King");
                IT.setCopiesLeft(11);

                Book DUNE = new Book();
                DUNE.setIsbn("978-0450011849");
                DUNE.setTitle("Dune");
                DUNE.setAuthor("Frank Herbert");
                DUNE.setCopiesLeft(2);

                Book E101 = new Book();
                E101.setIsbn("9781945796067");
                E101.setTitle("101 Essays That Will Change The Way You Think");
                E101.setAuthor("Brianna Wiest");
                E101.setCopiesLeft(101);

                Book BRIEF = new Book();
                BRIEF.setIsbn("0553052438");
                BRIEF.setTitle("A brief history of time");
                BRIEF.setAuthor("Stephen Hawking");
                BRIEF.setCopiesLeft(23);

                Book PET = new Book();
                PET.setIsbn("9780451150240");
                PET.setTitle("Pet Sematary");
                PET.setAuthor("Stephen King");
                PET.setCopiesLeft(5);

                Book MAH = new Book();
                MAH.setIsbn("9781449474256");
                MAH.setTitle("Milk and Honey");
                MAH.setAuthor("Rupi Kaur");
                MAH.setCopiesLeft(9);

                Book GUIDE = new Book();
                GUIDE.setIsbn("978-0345391803");
                GUIDE.setTitle("The Hitch Hiker's Guide to the Galaxy");
                GUIDE.setAuthor("Douglas Adams");
                GUIDE.setCopiesLeft(42);

                Book IDIOTS = new Book();
                IDIOTS.setIsbn("9781250398956");
                IDIOTS.setTitle("Surrounded by Idiots");
                IDIOTS.setAuthor("Thomas Erikson");
                IDIOTS.setCopiesLeft(0);

                MDB = bookRepo.save(MDB);
                CCNA = bookRepo.save(CCNA);
                E101 = bookRepo.save(E101);
                FAH = bookRepo.save(FAH);
                IT = bookRepo.save(IT);
                DUNE = bookRepo.save(DUNE);
                BRIEF = bookRepo.save(BRIEF);
                MAH = bookRepo.save(MAH);
                PET = bookRepo.save(PET);
                GUIDE = bookRepo.save(GUIDE);
                IDIOTS = bookRepo.save(IDIOTS);

                // #########
                // # Loans #
                // #########
                String userId = USER.getId();

                Loan loanA = new Loan();
                loanA.setUserId(userId);
                loanA.setBookId(GUIDE.getId());
                loanA.setLoanDate(LocalDate.now().minusDays(23));
                loanA.setDueDate(LocalDate.now().minusDays(9));
                loanA.setReturnDate(LocalDate.now().minusDays(2));
                loanA.setFineAmount(7 * 1.50);
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
                loanC.setBookId(CCNA.getId());
                loanC.setLoanDate(LocalDate.now().minusDays(53));
                loanC.setDueDate(LocalDate.now().minusDays(39));
                loanC.setReturnDate(LocalDate.now().minusDays(33));
                loanC.setFineAmount(6 * 1.50);
                loanC.setStatus("PAID");

                Loan loanD = new Loan();
                loanD.setUserId(userId);
                loanD.setBookId(FAH.getId());
                loanD.setLoanDate(LocalDate.now().minusDays(3));
                loanD.setDueDate(LocalDate.now().plusDays(11));
                loanD.setStatus("BORROWED");

                Loan loanE = new Loan();
                loanE.setUserId(userId);
                loanE.setBookId(IT.getId());
                loanE.setLoanDate(LocalDate.now());
                loanE.setDueDate(LocalDate.now().plusDays(14));
                loanE.setStatus("BORROWED");

                Loan loanF = new Loan();
                loanF.setUserId(userId);
                loanF.setBookId(MDB.getId());
                loanF.setLoanDate(LocalDate.now().minusDays(17));
                loanF.setDueDate(LocalDate.now().minusDays(3));
                loanF.setStatus("BORROWED");

                Loan loanG = new Loan();
                loanG.setUserId(userId);
                loanG.setBookId(GUIDE.getId());
                loanG.setLoanDate(LocalDate.now().minusDays(44));
                loanG.setDueDate(LocalDate.now().minusDays(30));
                loanG.setReturnDate(LocalDate.now().minusDays(33));
                loanG.setStatus("RETURNED");

                Loan loanH = new Loan();
                loanH.setUserId(userId);
                loanH.setBookId(GUIDE.getId());
                loanH.setLoanDate(LocalDate.now().minusDays(4));
                loanH.setDueDate(LocalDate.now().plusDays(10));
                loanH.setStatus("BORROWED");

                loanRepo.save(loanA);
                loanRepo.save(loanB);
                loanRepo.save(loanC);
                loanRepo.save(loanD);
                loanRepo.save(loanE);
                loanRepo.save(loanF);
                loanRepo.save(loanG);
                loanRepo.save(loanH);
            }
        };
    }
}
