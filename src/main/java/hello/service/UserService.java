package hello.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private Map<String, String> usernameAndPassword = new HashMap<>();

    public UserService() {
        usernameAndPassword.put("zhangsan", "123");
        usernameAndPassword.put("lisi", "321");
        usernameAndPassword.put("zhaowu", "1234");
    }

    public void saveUserIfo(String username, String password) {
        usernameAndPassword.put(username, password);
    }

    public String getPassword(String username) {
        return usernameAndPassword.get("username");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = usernameAndPassword.get(username);
        if (usernameAndPassword.containsKey(username)) {
            return new User(username, password, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username + "用户名不存在");
        }
    }
}
