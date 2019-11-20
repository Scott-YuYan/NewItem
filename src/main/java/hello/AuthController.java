package hello;

import hello.entity.Result;
import hello.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Map;

@RestController
public class AuthController {
    @Inject
    UserDetailsService userDetailsService;
    @Inject
    AuthenticationManager authenticationManager;


    public AuthController() {

    }

    @GetMapping("/auth")
    public User getUser() {
        return new User(1, "zhangsan", " ", Instant.now(), Instant.now());
    }

    @PostMapping("/auth/login")
    public Result login(@RequestBody Map<String, String> usernameAndPassword) {
        String name = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        UserDetails details;

        //获取用户真正的密码
        try {
            details = userDetailsService.loadUserByUsername(name);
        } catch (UsernameNotFoundException e) {
            return new Result("fail", "用户名不存在", null);
        }
        //对两份密码进行比对
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());

        try {
            //验证密码是否正确，错误则抛出异常
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            User user = new User(1, "hunger", "头像 url", Instant.now(), Instant.now());
            return new Result("ok", "登录成功", user);
        } catch (BadCredentialsException e) {
            return new Result("fail", "密码错误", null);
        }
    }

}
