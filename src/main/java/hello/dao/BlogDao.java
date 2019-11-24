package hello.dao;

import hello.entity.Blog;
import hello.entity.BlogResult;

import javax.inject.Inject;
import java.util.List;

public class BlogDao {
    @Inject
    private BlogMapper blogMapper;

    public List<Blog> getBlog(int page, int pageSize, int userId){
        return blogMapper.getBlogFromDB(page,pageSize,userId);
    }

    public int getCountOfMatchCondition(int userId){
        return 100;
    }
}
