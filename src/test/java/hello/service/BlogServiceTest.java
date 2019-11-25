package hello.service;

import hello.dao.BlogDao;
import hello.entity.Blog;
import hello.entity.BlogResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @Mock
    BlogDao blogDao;
    @InjectMocks
    BlogService blogService = new BlogService(blogDao);

    @Test
    public void textBlog() {
        List<Blog> blogs = Arrays.asList(mock(Blog.class), mock(Blog.class), mock(Blog.class), mock(Blog.class));
        when(blogDao.getBlog(1, 2, 0)).thenReturn(blogs);
        when(blogDao.getCount(0)).thenReturn(4);

        BlogResult blogResult = blogService.getBlog(1, 2, 0);

        verify(blogDao).getBlog(1, 2, 0);
        verify(blogDao).getCount(0);


        Assertions.assertEquals(1, blogResult.getPage());
        Assertions.assertEquals(4, blogResult.getTotal());
        Assertions.assertEquals(2, blogResult.getTotalPage());

    }
}
