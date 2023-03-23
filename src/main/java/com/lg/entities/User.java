package com.lg.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", indexes = @Index(columnList = "login"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<UsersGroup> groups = new ArrayList<>();

    @Column(columnDefinition="BLOB")
    private byte[] image;

    public void addRole(Role newRole){
        roles.add(newRole);
    }

    public User(Long id, String login, String password, String firstName, String lastName, Sex sex) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.roles = new ArrayList<>();
        this.groups = new ArrayList<>();
        File file = new File("./src/main/java/com/lg/img/troll.jpg");
        try {
            this.image = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("ERRROR ERROR ERROR");
            throw new RuntimeException(e);
        }
    }
}
