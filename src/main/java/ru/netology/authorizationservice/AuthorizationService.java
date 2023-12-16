package ru.netology.authorizationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
@Service
public class AuthorizationService {
    UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> iclHandler(InvalidCredentials e) {
        return new ResponseEntity<>("Exception in AuthorizationService", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unuHandler(UnauthorizedUser e) {
        return new ResponseEntity<>("Exception in AuthorizationService", HttpStatus.UNAUTHORIZED);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}