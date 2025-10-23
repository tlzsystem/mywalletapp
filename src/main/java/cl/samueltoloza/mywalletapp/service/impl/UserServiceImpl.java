package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.CurrencyRepository;
import cl.samueltoloza.mywalletapp.repository.UserRepository;
import cl.samueltoloza.mywalletapp.service.CategoryService;
import cl.samueltoloza.mywalletapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final CategoryService categoryService;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId) {
        return userRepository.findByProviderAndProviderUserId(provider, providerUserId);
    }

    @Override
    public User registerUserIfNotExists(OAuth2User oAuth2User, String provider) {
        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email).orElseGet(() ->{
            Currency defaultCurrency = currencyRepository.findByCode("USD").orElseThrow();

            User newUser = User.builder()
                    .email(email)
                    .fullName(oAuth2User.getAttribute("name"))
                    .provider(provider)
                    .providerUserId(oAuth2User.getAttribute("sub"))
                    .preferredCurrency(defaultCurrency)
                    .build();

            User savedUser = userRepository.save(newUser);
            categoryService.createDefaultCategoriesForUser(savedUser);
            return savedUser;
        });

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
