package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.model.Transaction;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private User user;
    private Currency currency;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        currency = new Currency();
        currency.setId(1L);
        currency.setCode("USD");
        currency.setPrecision(2);
    }

    @Test
    void testGetTransactionsForUser() {
        Transaction tx = new Transaction();
        tx.setId(100L);
        tx.setUser(user);
        tx.setAmount(BigDecimal.valueOf(10.5));
        tx.setCurrency(currency);
        tx.setDate(LocalDateTime.now());

        when(transactionRepository.findByUser(any(User.class))).thenReturn(List.of(tx));

        List<Transaction> result = transactionService.getTransactionsForUser(user);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getId());
        verify(transactionRepository, times(1)).findByUser(any(User.class));
    }

    @Test
    void testCreateTransactionForUser_RoundsAmount() {
        Transaction tx = new Transaction();
        tx.setAmount(new BigDecimal("10.4567"));
        tx.setCurrency(currency);

        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction savedTx = transactionService.createTransactionForUser(tx, user);

        assertNotNull(savedTx);
        assertEquals(user, savedTx.getUser());
        assertEquals(new BigDecimal("10.46"), savedTx.getAmount());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

}
