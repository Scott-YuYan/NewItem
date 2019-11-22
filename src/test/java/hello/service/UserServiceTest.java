package hello.service;

import hello.dao.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    UserMapper userMapperMock;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoderMock;
    @InjectMocks
    UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertSaveIntoMapper() {
        userService.insertUserIntoDatabase("zhangsan", "123123");
        Mockito.verify(userMapperMock).insertIntoUser("zhangsan", "123123");
    }
}