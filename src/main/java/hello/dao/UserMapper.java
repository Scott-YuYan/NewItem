package hello.dao;

import hello.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Select("select * from user where username = #{name}")
    User getUserByName(@Param("name") String name);

    @Select("select password from user where username=#{name}")
    String getPasswordByName(@Param("name") String name);

    @Insert("insert into user(username,password,create_time,modify_time)" +
            "values (#{username},#{password},now(),now())")
    void insertIntoUser(@Param("username")String username,@Param("password")String password);
}
