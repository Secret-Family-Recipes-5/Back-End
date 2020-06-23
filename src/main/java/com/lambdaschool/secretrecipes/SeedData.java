//package com.lambdaschool.secretrecipes;
//
//import com.lambdaschool.secretrecipes.models.Recipe;
//import com.lambdaschool.secretrecipes.models.Role;
//import com.lambdaschool.secretrecipes.models.User;
//import com.lambdaschool.secretrecipes.models.UserRoles;
//import com.lambdaschool.secretrecipes.services.RoleService;
//import com.lambdaschool.secretrecipes.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Override
//    public void run(String... args) throws Exception
//    {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        r1 = roleService.save(r1);
//        r2 = roleService.save(r2);
//        r3 = roleService.save(r3);
//
//        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//        admins.add(new UserRoles(new User(),
//                r1));
//        admins.add(new UserRoles(new User(),
//                r2));
//        admins.add(new UserRoles(new User(),
//                r3));
//        User u1 = new User("lanakane",
//                "password",
//                "lana.kane@figgisagency.local",
//                admins);
//
////        String title, String source, String ingredients, String instructions, int category, @NotNull User user
//        u1.getRecipes()
//                .add(new Recipe(
//                        "Mac n Cheese",
//                        "My Nana",
//                        "Apples, Cheese, Pie",
//                        "Toast in Oven",
//                        "2",
//                        u1));
//        u1.getRecipes()
//                .add(new Recipe(
//                        "Soup",
//                        "My Own Creation",
//                        "Water, Lettuce",
//                        "Toast in Oven",
//                        "1",
//                        u1));
//
//        userService.save(u1);
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(),
//                r3));
//        datas.add(new UserRoles(new User(),
//                r2));
//        User u2 = new User("sterlingarcher",
//                "1234567",
//                "sterling.archer@figgisagency.com",
//                datas);
//
//        u2.getRecipes()
//                .add(new Recipe(
//                        "Mac n Cheese TEST",
//                        "My Nana TEST",
//                        "Apples, Cheese, Pie TEST",
//                        "Toast in Oven TEST",
//                        "1",
//                        u2));
//
//        userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                r2));
//        User u3 = new User("cyrilfiggis",
//                "password",
//                "cyril.figgis@figgisagency.com",
//                users);
//
//        u3.getRecipes()
//                .add(new Recipe(
//                        "Mac n Cheese",
//                        "My Nana",
//                        "Apples, Cheese, Pie",
//                        "Toast in Oven",
//                        "2",
//                        u3));
//        userService.save(u3);
//
//        User u4 = new User("admin",
//                "password",
//                "admin@figgisagency.local",
//                admins);
//
//        u4.getRecipes()
//                .add(new Recipe(
//                        "Cobbler",
//                        "My Mom",
//                        "Apples, Pie",
//                        "Toast in Microwave",
//                        "3",
//                        u4));
//        u4.getRecipes()
//                .add(new Recipe(
//                        "Mac n Cheese",
//                        "My Nana",
//                        "Apples, Cheese, Pie",
//                        "Toast in Oven",
//                        "2",
//                        u4));
//
//        userService.save(u4);
//    }
//}