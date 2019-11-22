package hello.service;

import hello.dao.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserMapper userMapper;

    public hello.entity.User getUserByUsername(String username) {
        return userMapper.getUserByName(username);
    }

    public void insertUserIntoDatabase(String name, String password) {
        userMapper.insertIntoUser(name, bCryptPasswordEncoder.encode(password));
    }

    //告诉项目在启动时运行该方法
//    @PostConstruct
//    public void init() {
//        userMapper.insertIntoUser("zhangsan", bCryptPasswordEncoder.encode("123"));
//        userMapper.insertIntoUser("lisi", bCryptPasswordEncoder.encode("123"));
//        userMapper.insertIntoUser("zhaowu", bCryptPasswordEncoder.encode("123"));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        hello.entity.User user = userMapper.getUserByName(username);
        if (user != null) {
            return new User(username, user.getBcrPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException(username + "用户名不存在");
        }
    }
}

