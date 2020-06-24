package com.lambdaschool.secretrecipes.services;

import com.lambdaschool.secretrecipes.exceptions.ResourceFoundException;
import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.models.*;
import com.lambdaschool.secretrecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Userservice Interface
 */

@Transactional
@Service(value = "userService")
public class UserServiceImpl
        implements UserService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAuditing userAuditing;


    public User findUserById(long id) throws ResourceNotFoundException {
        return userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username) {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            User oldUser = userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));

            for (UserRoles ur : oldUser.getRoles())
            {
                deleteUserRole(ur.getUser().getUserid(), ur.getRole().getRoleid());
            }

            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());


        if (user.getUserid() == 0)
        {
            for (UserRoles ur : user.getRoles())
            {
                Role newRole = roleService.findRoleById(ur.getRole().getRoleid());

                newUser.addRole(newRole);
            }
        } else
        {
            for (UserRoles ur : user.getRoles())
            {
                addUserRole(newUser.getUserid(),
                        ur.getRole()
                                .getRoleid());
            }
        }

        newUser.getRecipes().clear();
        for (Recipe i : user.getRecipes()){
            newUser.getRecipes().add(new Recipe(i.getTitle(), newUser));
        }


        return userrepos.save(newUser);
    }



    @Transactional
    @Override
    public User update(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (user.getUsername() != null) {
            currentUser.setUsername(user.getUsername().toLowerCase());
        }

        if (user.getPassword() != null) {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getPrimaryemail() != null) {
            currentUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());
        }

        return userrepos.save(currentUser);

    }

    @Transactional
    @Override
    public void deleteUserRole(long userid, long roleid) {
        userrepos.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        roleService.findRoleById(roleid);

        if (userrepos.checkUserRolesCombo(userid, roleid).getCount() > 0) {
            userrepos.deleteUserRoles(userid, roleid);
        } else {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }


    @Transactional
    @Override
    public void addUserRole(long userid, long roleid)
    {
        userrepos.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        roleService.findRoleById(roleid);

        if (userrepos.checkUserRolesCombo(userid, roleid).getCount() <= 0) {
            userrepos.insertUserRoles(userAuditing.getCurrentAuditor().get(), userid, roleid);
        } else {
            throw new ResourceFoundException("Role and User Combination Already Exists");
        }
    }
}