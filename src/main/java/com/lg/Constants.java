package com.lg;

import com.lg.entities.Role;
import com.lg.entities.Sex;
import com.lg.entities.User;

import java.util.List;

public class Constants {

    public static final List<User> users = List.of(
            new User(null, "test_1", "test_1", "Andrzej", "Kowalski", Sex.MALE),
            new User(null, "test_2", "test_2", "Adam", "Nowak", Sex.MALE),
            new User(null, "test_3", "test_3", "Wojciech", "Kowalski", Sex.MALE),
            new User(null, "test_4", "test_4", "Judyta", "Janusz", Sex.FEMALE),
            new User(null, "test_5", "test_5", "Aneta", "Brzoza", Sex.FEMALE)
    );

    public static final List<Role> roles = List.of(
            new Role(null, "ADMIN"),
            new Role(null, "USER"),
            new Role(null, "VISITOR"),
            new Role(null, "MODERATOR"),
            new Role(null, "BANNED")
    );
}
