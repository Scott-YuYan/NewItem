package hello;

import hello.entity.Result;
import hello.entity.User;
import hello.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class AuthController {
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private UserService userService;


    public AuthController() {

    }

    @GetMapping("/auth")
    public Result getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User loginUser = userService.getUserByUsername(name);
        if (loginUser == null) {
            return new Result("ok", false);
        } else {
            User user = userService.getUserByUsername(name);
            return new Result("ok", "登录成功", true, user);
        }
    }

    @PostMapping("/auth/login")
    public Result login(@RequestBody Map<String, String> usernameAndPassword) {
        String name = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        UserDetails details;

        //获取用户真正的密码
        try {
            details = userService.loadUserByUsername(name);
        } catch (UsernameNotFoundException e) {
            return new Result("fail", "用户名不存在");
        }
        //对两份密码进行比对
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());

        try {
            //验证密码是否正确，错误则抛出异常
            authenticationManager.authenticate(token);
            //将用户信息保存在SecurityContextHolder,其实猜名字就能猜出来，就是设置Cookies
            SecurityContextHolder.getContext().setAuthentication(token);
            User user = userService.getUserByUsername(name);
            return new Result("ok", "登录成功",  user);
        } catch (BadCredentialsException e) {
            return new Result("fail", "密码不正确");
        }
    }

}
