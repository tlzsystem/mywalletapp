package cl.samueltoloza.mywalletapp.service;

import cl.samueltoloza.mywalletapp.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId);

    User registerUserIfNotExists(OAuth2User oAuth2User, String provider);

    User save(User user);
}
