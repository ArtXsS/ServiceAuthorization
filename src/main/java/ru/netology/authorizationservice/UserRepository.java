package ru.netology.authorizationservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        String name = "admin";
        String pass = "1212";
        if(user.contains(name) && password.contains(pass)) {
            List<Authorities> rights = Arrays.asList(Authorities.DELETE, Authorities.READ, Authorities.WRITE);
            return rights;
        }
        List<Authorities> nothing = new ArrayList<>();
        return nothing;
    }
}