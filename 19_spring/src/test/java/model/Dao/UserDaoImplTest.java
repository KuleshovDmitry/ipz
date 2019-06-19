package model.Dao;

import model.MyTestConfiguration;
import model.dao.UserDao;
import entity.Role;
import entity.User;
import service.RoleService;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyTestConfiguration.class)
public class UserDaoImplTest {
    @Autowired
    Server server;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleDao;
    private User user;
    @Before
    public void setUp(){
        roleDao.create(Role.getAdminRole());
        user = new User("test", "test", Role.getAdminRole());
        userDao.delete(userDao.getIdByLogin("test"));
        userDao.insert(user);
        user.setId(userDao.getIdByLogin(user.getLogin()));
    }

    @Test
    public void testShouldContainsInsertedElements() throws Exception {
        List<User> genreList = userDao.selectAll();

        Assert.assertThat(genreList, hasItems(user));
    }

    @Test
    public void testShouldReturnNotNullForExisting() {
        assertTrue(userDao.getId(user) > 0);
    }

    @Test
    public void testShouldReturnFalseForExisting() {
        assertFalse(userDao.insert(user));
    }

    @Test
    public void testShouldReturnFalseInInsertWithoutLogin () {
        User user = new User(null, "1",Role.getAdminRole() );

        assertFalse(userDao.insert(user));
    }


    @Test
    public void testShouldReturnFalseInInsertWithoutPassword () {
        User user = new User("1", null,Role.getAdminRole() );

        assertFalse(userDao.insert(user));
    }

    @Test
    public void testUpdateWithExistIdShouldReturnTrue() {
        userDao.insert(user);
        long id = userDao.getId(user);
        user = userDao.getById(id, User.class);
        user.setLogin("testUpdateWithExistIdShouldReturnTrue");
        user.setId(id);


        assertTrue(userDao.update(user));
        assertThat(id, is(userDao.getId(user)));
    }

    @Test
    public void testUpdateWithNotExistIdShouldReturnFalse() {
        User user = new User("TEST", "TEST", Role.getAdminRole());
        userDao.insert(user);
        user.setLogin("TEST");
        user.setId(-1);
        assertFalse(userDao.update(user));
    }

    @Test
    public void testUpdateWithNullLoginShouldReturnFalse() {
        User user = new User("TEST", "TEST", Role.getAdminRole());
        userDao.insert(user);
        long id = userDao.getIdByLogin(user.getLogin());
        user.setLogin(null);
        user.setId(id);

        assertFalse(userDao.update(user));
    }

    @Test
    public void testObjectShouldBeMissingAfterDelete() {
        assertTrue(userDao.delete(user));
        assertTrue(userDao.getId(user) == 0);
    }

    @Test
    public void testObjectShouldBeMissingAfterDeleteById() {
        assertTrue(userDao.delete(user.getId()));
        assertTrue(userDao.getId(user) == 0);
    }

    @Test
    public void testGetByIdMustReturnNullForExistId() {
        assertEquals(userDao.getById(0, User.class), null);
    }

    @Test
    public void testShouldReturnIdMoreThanOneForExist() {
        assertTrue(userDao.getId(user) >= 0);
    }

    @Test
    public void testShouldReturnZeroForGetIdNotExistUser() {
        userDao.delete(user);
        assertTrue(userDao.getId(user) == 0);
    }

    @Test
    public void testGetByIdShouldReturnCurrentObjectForExistObject() {
        long id = userDao.getId(user);

        assertEquals(userDao.getById(id, User.class), user);
    }

    @Test
    public void testGetByIdShouldReturnNullForExistObject() {
        assertEquals(userDao.getById(0, User.class), null);
    }
}
