package hello.dao;

import hello.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> getBlogFromDB(int page,int pageSize,int userId);
}
