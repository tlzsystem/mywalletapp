package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.model.Transaction;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.TransactionRepository;
import cl.samueltoloza.mywalletapp.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsForUser(User user) {
        return transactionRepository.findByUser(user);
    }

    @Override
    public List<Transaction> getTransactionsForUserInPeriod(User user, LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByUserAndDateBetween(user, start, end);
    }

    @Override
    public Transaction createTransactionForUser(Transaction transaction, User user) {

        if (transaction == null) throw new IllegalArgumentException("Transaction must not be null");
        if (user == null) throw new IllegalArgumentException("User must not be null");
        if (transaction.getCurrency() == null) throw new IllegalArgumentException("Currency must not be null");

        transaction.setUser(user);

        Currency currency = transaction.getCurrency();
        int precision = currency.getPrecision();

        BigDecimal roundedAmount = transaction.getAmount()
                .setScale(precision, RoundingMode.HALF_UP);

        transaction.setAmount(roundedAmount);

        return transactionRepository.save(transaction);
    }
}
