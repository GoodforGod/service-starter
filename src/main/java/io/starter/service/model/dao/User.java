package io.starter.service.model.dao;

import io.starter.service.model.dto.UserTO;

import static io.starter.service.util.BasicStringUtils.genUniqueId;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 14.02.2018
 */
public class User extends BasicMongoAuditModel<String> {

    private String name;
    private String surname;

    public User(String name, String surname) {
       this(genUniqueId(), name, surname);
    }

    private User(String id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public static User of(UserTO user) {
        return (user == null)
                ? null
                : new User(user.getId(), user.getName(), user.getSurname());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User userMongo = (User) o;

        if (name != null ? !name.equals(userMongo.name) : userMongo.name != null) return false;
        return surname != null ? surname.equals(userMongo.surname) : userMongo.surname == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
