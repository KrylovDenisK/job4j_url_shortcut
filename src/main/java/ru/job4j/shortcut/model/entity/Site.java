package ru.job4j.shortcut.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "registration")
    private boolean registration;

    private Site() {
    }

    public static Site of(String name, String login, String password, boolean registration) {
        Site site = new Site();
        site.name = name;
        site.login = login;
        site.password = password;
        site.registration = registration;
        return site;
    }

    @JsonCreator
    public static Site of(@JsonProperty("site") String name) {
        Site site = new Site();
        site.name = name;
        return site;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        return registration == site.registration
                && Objects.equals(id, site.id)
                && Objects.equals(name, site.name)
                && Objects.equals(login, site.login)
                && Objects.equals(password, site.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, registration);
    }
}
