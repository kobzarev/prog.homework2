package task3;

import annotation.Save;

import java.util.Objects;

public class User {
    @Save
    private String firstName;

    @Save
    private int age;

    @Save
    private String email;

    private String comment;

    public User(String firstName, int age, String email, String comment) {
        this.firstName = firstName;
        this.age = age;
        this.email = email;
        this.comment = comment;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && firstName.equals(user.firstName) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, age, email);
    }
}
