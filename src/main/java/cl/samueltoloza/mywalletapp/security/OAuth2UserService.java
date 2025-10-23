package cl.samueltoloza.mywalletapp.security;

import cl.samueltoloza.mywalletapp.model.Currency;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.CurrencyRepository;
import cl.samueltoloza.mywalletapp.repository.UserRepository;
import cl.samueltoloza.mywalletapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        User user = userService.registerUserIfNotExists(oAuth2User, userRequest.getClientRegistration().getRegistrationId());

        return new UserPrincipal(user, oAuth2User.getAttributes());

    }
}
