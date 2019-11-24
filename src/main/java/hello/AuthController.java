package hello;

import hello.entity.LoginResult;
import hello.entity.Result;
import hello.entity.User;

import hello.service.UserService;
import org.springframework.dao.DuplicateKeyException;
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

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Inject
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Result getUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return LoginResult.getIsNotLoginStatus();
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User loginUser = userService.getUserByUsername(name);
        if (loginUser == null) {
            return LoginResult.getIsNotLoginStatus();
        } else {
            User user = userService.getUserByUsername(name);
            return LoginResult.getIsLoginStatus(user);
        }
    }

    @PostMapping("/auth/register")
    public Result register(@RequestBody Map<String, String> usernameAndPassword) {
        String name = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");

        if (name == null || password == null) {
            return LoginResult.getFailResult("用户名密码不能为空");
        }

        if (0 < name.length() && name.length() < 17) {
            if (5 < password.length() & password.length() < 17) {
                try {
                    userService.insertUserIntoDatabase(name, password);
                } catch (DuplicateKeyException e) {
                    e.printStackTrace();
                    return LoginResult.getFailResult("该用户名已经被注册");
                }
                User user = userService.getUserByUsername(name);
                return LoginResult.getSuccessResult("注册成功",user);
            } else {
                return LoginResult.getFailResult("密码长度只能是6-16个字符");
            }
        } else {
            return LoginResult.getFailResult("用户名: 长度1到15个字符，只能是字母数字下划线中文");
        }
    }

    @PostMapping("/auth/logout")
    public Result logout(@RequestBody Map<String, String> usernameAndPassword) {
        String name = usernameAndPassword.get("username");
        if (userService.getUserByUsername(name) != null) {
            return LoginResult.getFailResult("用户还未登陆");
        } else {
            SecurityContextHolder.clearContext();
            return LoginResult.getFailResult("注销成功");
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
            return LoginResult.getFailResult("用户名不存在");
        }
        //对两份密码进行比对
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());

        try {
            //验证密码是否正确，错误则抛出异常
            authenticationManager.authenticate(token);
            //将用户信息保存在SecurityContextHolder,其实猜名字就能猜出来，就是设置Cookies
            SecurityContextHolder.getContext().setAuthentication(token);
            User user = userService.getUserByUsername(name);
            return LoginResult.getSuccessResult("登录成功", user);
        } catch (BadCredentialsException e) {
            return LoginResult.getFailResult("密码不正确");
        }
    }


}
