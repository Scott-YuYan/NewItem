package hello.service;

import hello.dao.BlogMapper;
import hello.entity.Blog;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogService {
    @Inject
    private BlogMapper blogMapper;

    public List<Blog> getBlog(int page, int pageSize, int userId) {
        return blogMapper.getBlogFromDB(page, pageSize, userId);
    }

}
