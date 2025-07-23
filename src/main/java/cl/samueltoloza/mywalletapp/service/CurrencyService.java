package cl.samueltoloza.mywalletapp.service;

import cl.samueltoloza.mywalletapp.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> getAll();

    Optional<Currency> findByCode(String code);

}
