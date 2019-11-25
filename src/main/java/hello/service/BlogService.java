package hello.service;

import hello.dao.BlogDao;
import hello.entity.Blog;
import hello.entity.BlogResult;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogService {

    private BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public BlogResult getBlog(int page, int pageSize, int userId) {
        List<Blog> blogs;
        int count, totalPage;

        try {
            blogs = blogDao.getBlog(page, pageSize, userId);
            count = blogDao.getCountOfMatchCondition(userId);
            totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);
        } catch (Exception e) {
            return BlogResult.failBlogResultBuilder(e.getMessage());
        }
        return BlogResult.successBlogResultBuilder(blogs, count, page, totalPage);
    }
}
