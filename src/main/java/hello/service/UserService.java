package hello.service;

import hello.dao.UserMapper;
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

    @Inject
    private
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    UserMapper userMapper;


    public UserService() {

    }

    public hello.entity.User getUserByUsername(String username) {
        return userMapper.getUserByName(username);
    }

    //告诉项目在启动时运行该方法
    @PostConstruct
    public void init() {
        userMapper.insertIntoUser("zhangsan",bCryptPasswordEncoder.encode( "123"));
        userMapper.insertIntoUser("lisi",bCryptPasswordEncoder.encode( "123"));
        userMapper.insertIntoUser("zhaowu",bCryptPasswordEncoder.encode( "123"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userMapper.getUserByName(username)!=null) {
            String password = userMapper.getPasswordByName(username);
            return new User(username, password, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username + "用户名不存在");
        }
    }
}

