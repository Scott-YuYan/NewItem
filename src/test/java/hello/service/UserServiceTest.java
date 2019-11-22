package hello.service;

import hello.dao.UserMapper;
import hello.entity.User;
<<<<<<< HEAD
import org.junit.Before;
=======
>>>>>>> master
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< HEAD
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

=======

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
>>>>>>> master


    @Mock
    UserMapper userMapperMock;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoderMock;
    @InjectMocks
    UserService userService;

    @Test

    public void testInsertIntoUser() {
        when(bCryptPasswordEncoderMock.encode("password")).thenReturn("encoderPassword");
        userService.insertUserIntoDatabase("zhangsan", "password");
        verify(userMapperMock).insertIntoUser("zhangsan", "encoderPassword");
    }

    @Test
    public void testGetUserByUsername() {
        userService.getUserByUsername("zhangsan");
        verify(userMapperMock).getUserByName("zhangsan");
    }

    @Test
    public void testLoadUserByUsernameFail() {
        when(userMapperMock.getUserByName("zhangsan")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("zhangsan"));
    }

    @Test
<<<<<<< HEAD

    public void testInsertIntoUser() {
        when(bCryptPasswordEncoderMock.encode("password")).thenReturn("encoderPassword");
        userService.insertUserIntoDatabase("zhangsan", "password");
        verify(userMapperMock).insertIntoUser("zhangsan", "encoderPassword");
    }

    @Test
    public void testGetUserByUsername(){
        userService.getUserByUsername("zhangsan");
        verify(userMapperMock).getUserByName("zhangsan");
    }

    @Test
    public void testLoadUserByUsernameFail(){
        when(userMapperMock.getUserByName("zhangsan")).thenReturn(null);
                Assertions.assertThrows(UsernameNotFoundException.class,
                        () ->userService.loadUserByUsername("zhangsan"));
    }

    @Test
    public void testLoadUserByUsernameSuccess(){
        when(userMapperMock.getUserByName("zhangsan"))
                .thenReturn(new User(1,"zhangsan","password","null", Instant.now(),Instant.now()));
        UserDetails details = userService.loadUserByUsername("zhangsan");
        Assertions.assertEquals("zhangsan",details.getUsername());
        Assertions.assertEquals("password",details.getPassword());


=======
    public void testLoadUserByUsernameSuccess() {
        when(userMapperMock.getUserByName("zhangsan"))
                .thenReturn(new User("zhangsan", "password"));
        UserDetails details = userService.loadUserByUsername("zhangsan");
        Assertions.assertEquals("zhangsan", details.getUsername());
        Assertions.assertEquals("password", details.getPassword());
>>>>>>> master
    }
}