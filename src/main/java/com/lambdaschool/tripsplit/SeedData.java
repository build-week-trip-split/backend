package com.lambdaschool.tripsplit;

import com.lambdaschool.tripsplit.models.*;
import com.lambdaschool.tripsplit.services.BillService;
import com.lambdaschool.tripsplit.services.RoleService;
import com.lambdaschool.tripsplit.services.TripService;
import com.lambdaschool.tripsplit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    BillService billService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
//        u1.getQuotes().add(new Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1));
//        u1.getQuotes().add(new Quote("The question isn't who is going to let me; it's who is going to stop me.", u1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
//        u3.getQuotes().add(new Quote("Live long and prosper", u3));
//        u3.getQuotes().add(new Quote("The enemy of my enemy is the enemy I kill last", u3));
//        u3.getQuotes().add(new Quote("Beam me up", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

        ////////////////////////////////////////////////////////////////////

        Trip t1 = new Trip("Chicago", "03-13-2019", "03-25-2019");
////
////       Bill b1 = new Bill("Lunch", 50.0);
//////        t1.getBills().add(new Bill("Dinner", 140.0, t1, u4));
//////
////        billService.save(b1,9,7);
//        t1.getUsers().add(u4);
//        t1.getUsers().add(u5);
//////
        tripService.save(t1, "Bob");
//
        Trip t2 = new Trip("Arizona", "03-13-2018", "03-25-2018");
//        t2.getBills().add(new Bill("Lunch", 150.0,  t2, u4));
//        t2.getBills().add(new Bill("Dinner", 140.0, t2, u4));
//
        tripService.save(t2, "Bob");
    }
}