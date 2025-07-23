package cl.samueltoloza.mywalletapp.repository;

import cl.samueltoloza.mywalletapp.model.Transaction;
import cl.samueltoloza.mywalletapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndDateBetween(User user, LocalDateTime start, LocalDateTime end);

}
