import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTest {

    private final UserDaoImpl userDao = UserDaoImpl.getInstance();


    @Test
    void testGetUserByEmailFound() {
        User user = userDao.getUserByEmail("ivan@example.com");

        assertNotNull(user);
        assertEquals("Іван", user.getName());
        assertEquals("Іваненко", user.getLastname());
        assertEquals("Київ", user.getAddress());
        assertEquals("12345", user.getIndex());
        assertEquals("ivan@example.com", user.getEmail());
        assertEquals("pass123", user.getPassword());
    }

    @Test
    void testGetUserByEmailNotFound() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userDao.getUserByEmail("notfound@example.com"));
        assertTrue(thrown.getMessage().contains("Помилка при пошуку користувача"));
    }

    @Test
    void testSaveUser() {
        User user = new User("Іван", "Іваненко", "Київ", "123", "ivan@example.com", "pass123");
        userDao.saveUser(user);
        User testUser = userDao.getUserByEmail("ivan@example.com");
        assertNotNull(testUser);
        assertEquals("Іван", testUser.getName());
        assertEquals("Іваненко", testUser.getLastname());
        assertEquals("Київ", testUser.getAddress());
        assertEquals("123", testUser.getIndex());
        assertEquals("ivan@example.com", testUser.getEmail());
        assertEquals("pass123", testUser.getPassword());

        userDao.deleteUser(user);
    }
}
