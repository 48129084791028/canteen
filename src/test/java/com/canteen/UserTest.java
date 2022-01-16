package com.canteen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    
    String fio = "ss";
    String date_of_birth = "01.01.22";
    String login = "!@#$%^&";
    String password = "!@#$%^&";
    int role = 1;
    
    @Test
    public void testAddUser() throws Exception {       
        User.addUser(fio, date_of_birth, login, password, role);
        if (User.findUser(login)){
            return;
        }
        else{
            fail("User not added!");
        }
    }

    @Test
    public void testRemoveUser() throws Exception {
        User.removeUser(login);
        if (User.findUser(login)){
            fail("User not removed!");
        }
        else{
            return;
        }
    }
    
}
