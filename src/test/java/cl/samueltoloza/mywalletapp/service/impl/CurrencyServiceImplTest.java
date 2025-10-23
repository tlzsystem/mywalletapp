package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceImplTest {


    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Currency usd = new Currency();
        usd.setId(1L);
        usd.setCode("USD");
        usd.setName("US Dollar");
        usd.setPrecision(2);

        when(currencyRepository.findAll()).thenReturn(List.of(usd));

        List<Currency> result = currencyService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("USD", result.get(0).getCode());
        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    void testFindByCode_Found() {
        Currency eur = new Currency();
        eur.setId(2L);
        eur.setCode("EUR");
        eur.setName("Euro");
        eur.setPrecision(2);

        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.of(eur));

        Optional<Currency> result = currencyService.findByCode("EUR");

        assertTrue(result.isPresent());
        assertEquals("EUR", result.get().getCode());
        assertEquals("Euro", result.get().getName());
        verify(currencyRepository, times(1)).findByCode("EUR");
    }

    @Test
    void testFindByCode_NotFound() {
        when(currencyRepository.findByCode("JPY")).thenReturn(Optional.empty());

        Optional<Currency> result = currencyService.findByCode("JPY");

        assertFalse(result.isPresent());
        verify(currencyRepository, times(1)).findByCode("JPY");
    }

}
