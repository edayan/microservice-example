package com.in2minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Abel", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(final int userId) {
        return users.stream()
                .filter(user -> userId == user.getId())
                .findFirst()
                .orElse(null);
    }

    public User deleteById(final int id) {
        Iterator<User> iterator = this.users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (id == user.getId()) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
