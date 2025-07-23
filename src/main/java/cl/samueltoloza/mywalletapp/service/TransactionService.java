package cl.samueltoloza.mywalletapp.service;

import cl.samueltoloza.mywalletapp.model.Transaction;
import cl.samueltoloza.mywalletapp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionsForUser(User user);

    List<Transaction> getTransactionsForUserInPeriod(User user, LocalDateTime start, LocalDateTime end);

    Transaction createTransactionForUser(Transaction transaction, User user);

}
