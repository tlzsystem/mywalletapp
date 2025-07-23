package cl.samueltoloza.mywalletapp.service;

import cl.samueltoloza.mywalletapp.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId);

    User save(User user);
}
