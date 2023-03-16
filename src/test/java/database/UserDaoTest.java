package database;

import chess.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        final Connection connection = userDao.getConnection();

        assertThat(connection).isNotNull();
    }

    @Test
    public void insert() {
        final var user = new User("irene", "irene");
        userDao.insert(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("irene");

        assertThat(user).isEqualTo(new User("irene", "irene"));
    }

    @Test
    public void update() {
        final var user = new User("irene", "irene");
        final String newName = "hello";
        userDao.update(user, newName);
    }

    @Test
    public void delete() {
        final var user = new User("irene", "hello");
        userDao.delete(user);
    }
}
