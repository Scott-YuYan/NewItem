package hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.AuthController;
import hello.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
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
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


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
                .thenReturn(new User("zhangsan", bCryptPasswordEncoder.encode("password"), Collections.emptyList()));
        String JSON = new ObjectMapper().writeValueAsString(nameAndPassword);
        MvcResult mvcResult = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResponse().getContentAsString().contains("登录成功")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        //拿到Http相应的Cookie
        HttpSession session = mvcResult.getRequest().getSession();
        when(userService.getUserByUsername("zhangsan"))
                .thenReturn(new hello.entity.User("zhangsan", bCryptPasswordEncoder.encode("password")));
        //拿到Cookie之后再进行一次登录操作
        mvc.perform(get("/auth").session((MockHttpSession) session)).andExpect(status().isOk())
                .andExpect(result -> Assertions.assertTrue(result.getResponse().getContentAsString().contains("zhangsan")));


    }
}