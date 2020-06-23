package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.models.User;

import java.util.List;

/**
 * The Service that works with User Model.
 * <p>
 * Note: Emails are added through the add user process
 * Roles are added through the add user process
 * No way to delete an assigned role
 */
public interface UserService
{
    List<User> findAll();

    List<User> findByNameContaining(String username);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(
            User user,
            long id);

    void deleteUserRole(
            long userid,
            long roleid);

    void addUserRole(
            long userid,
            long roleid);
}