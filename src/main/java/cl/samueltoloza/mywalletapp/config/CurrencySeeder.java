package cl.samueltoloza.mywalletapp.config;


import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencySeeder implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;


    @Override
    public void run(String... args) throws Exception {
        if(currencyRepository.count() == 0){
            List<Currency> currencies = List.of(
                    Currency.builder().code("USD").name("United States Dollar").symbol("$").precision(2).build(),
                    Currency.builder().code("EUR").name("Euro").symbol("€").precision(2).build(),
                    Currency.builder().code("CLP").name("Chilean Peso").symbol("$").precision(0).build(),
                    Currency.builder().code("ARS").name("Argentine Peso").symbol("$").precision(2).build(),
                    Currency.builder().code("BRL").name("Brazilian Real").symbol("R$").precision(2).build(),
                    Currency.builder().code("GBP").name("British Pound").symbol("£").precision(2).build(),
                    Currency.builder().code("JPY").name("Japanese Yen").symbol("¥").precision(0).build(),
                    Currency.builder().code("CNY").name("Chinese Yuan").symbol("¥").precision(2).build(),
                    Currency.builder().code("CAD").name("Canadian Dollar").symbol("$").precision(2).build(),
                    Currency.builder().code("AUD").name("Australian Dollar").symbol("$").precision(2).build(),
                    Currency.builder().code("MXN").name("Mexican Peso").symbol("$").precision(2).build(),
                    Currency.builder().code("COP").name("Colombian Peso").symbol("$").precision(0).build(),
                    Currency.builder().code("PEN").name("Peruvian Sol").symbol("S/.").precision(2).build()
            );
            currencyRepository.saveAll(currencies);
            log.info("Currencies seeded: {}", currencies.size());
        }else{
            log.info("Currencies already exist, seeding skipped.");
        }
    }
}
