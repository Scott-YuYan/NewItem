package hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.AuthController;
import hello.dao.UserMapper;
import hello.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AuthControllerTest {
    private MockMvc mvc;
    @Mock
    UserMapper userMapperMock;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(
                new AuthController(authenticationManager, userService)).build();
    }

    @Test
    public void returnNotLoginBeforeLogin() throws Exception {
        mvc.perform(get("/auth")).andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResponse().getContentAsString().contains("用户没有登录")));
    }

    @Test
    public void testLogin() throws Exception {

        mvc.perform(get("/auth")).andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResponse().getContentAsString().contains("用户没有登录")));

        Map<String, String> nameAndPassword = new HashMap<>();
        nameAndPassword.put("username", "zhangsan");
        nameAndPassword.put("password", "password");

        when(userService.loadUserByUsername("zhangsan"))
                .thenReturn(new User("zhangsan", "password", Collections.emptyList()));
        when(userMapperMock.getUserByName("zhangsan"))
                .thenReturn(new hello.entity.User(1, "zhangsan", "password", "null", Instant.now(), Instant.now()));
        String JSON = new ObjectMapper().writeValueAsString(nameAndPassword);
        MvcResult mvcResult = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}