package io.starter.service.model.dto;

import io.starter.service.model.dao.User;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 14.02.2018
 */
public class UserTO {

    private String id;
    private String name;
    private String surname;

    private UserTO(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    private static UserTO of(User user) {
        return (user == null)
                ? null
                : new UserTO(user.getId(), user.getName(), user.getSurname());
    }

    public String getId() {
        return id;
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
        if (!(o instanceof UserTO)) return false;

        UserTO userTO = (UserTO) o;

        if (id != null ? !id.equals(userTO.id) : userTO.id != null) return false;
        if (name != null ? !name.equals(userTO.name) : userTO.name != null) return false;
        return surname != null ? surname.equals(userTO.surname) : userTO.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
