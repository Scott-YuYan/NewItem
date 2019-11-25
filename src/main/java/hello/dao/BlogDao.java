package hello.dao;

import hello.entity.Blog;
import hello.entity.BlogResult;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogDao {

    private BlogMapper blogMapper;

    @Inject
    public BlogDao(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public List<Blog> getBlog(int page, int pageSize, int userId) {
        return blogMapper.getBlogFromDB(page, pageSize, userId);
    }

    public int getCountOfMatchCondition(int userId) {
        return 100;
    }
}
