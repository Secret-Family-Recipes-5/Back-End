package com.lambdaschool.secretrecipes.services;

import com.lambdaschool.secretrecipes.SecretrecipesApplication;
import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.models.Role;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.models.UserRoles;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretrecipesApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        List<User> myList = userService.findAll();
        for (User u : myList) {
            System.out.println(u.getUserid() + " " + u.getUsername());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findUserById() {
        assertEquals("spookyGhost", userService.findUserById(11).getUsername());
    }

    @Test
    public void findByNameContaining() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void save() {

        List<UserRoles> thisrole = new ArrayList<>();
        User newUser = new User("TestingUser", "fakepassword", "myemail@email.com", thisrole);
        User addUser = userService.save(newUser);

        assertNotNull(addUser);
        User foundUser   = userService.findUserById(addUser.getUserid());
        assertEquals(addUser.getUsername(), foundUser.getUsername());
    }

    @Test(expected = NullPointerException.class)
    public void update() {
        List<UserRoles> thisrole = new ArrayList<>();
        thisrole.add(new UserRoles());
        thisrole.get(0).setRole(new Role());
        thisrole.get(0).getRole().setRoleid(2);
        thisrole.get(0).setUser(new User());
        User newUser = new User("TestingUser", "fakepassword1", "myemail@email.com", thisrole);

        User updateUser = userService.update(newUser);
        assertEquals("fakepassword1", updateUser.getPassword());
    }

    @Test
    public void deleteUserRole() {
    }

    @Test
    public void addUserRole() {
    }
}