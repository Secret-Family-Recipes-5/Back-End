package com.lambdaschool.secretrecipes.services;



import com.lambdaschool.secretrecipes.models.Role;

import java.util.List;

/**
 * The service that works with the Role Model.
 */
public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    Role findByName(String name);

    Role save(Role role);

}