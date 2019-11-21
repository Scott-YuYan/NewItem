package hello.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private Map<String, hello.entity.User> usernameAndPassword = new HashMap<>();

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService() {

    }

    public String getPassWordByUsername(String username) {
        return usernameAndPassword.get(username).getBcrPassword();
    }

    public hello.entity.User getUserByUsername(String username) {
        return usernameAndPassword.get(username);
    }

    //告诉项目在启动时运行该方法
    @PostConstruct
    public void init() {
        saveUserIfo("zhangsan", "123");
        saveUserIfo("lisi", "321");
        saveUserIfo("zhaowu", "1234");
    }

    public void saveUserIfo(String username, String password) {
        if (password.length() < 3) {
            throw new BadCredentialsException("密码长度不能小于3");
        } else {
            hello.entity.User user = new hello.entity.User(1, username,
                    bCryptPasswordEncoder.encode(password), "http://avatar.com/1.png", Instant.now(), Instant.now());
            usernameAndPassword.put(username, user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (usernameAndPassword.containsKey(username)) {
            String password = getPassWordByUsername(username);
            return new User(username, password, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username + "用户名不存在");
        }
    }
}
