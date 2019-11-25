package hello.controller;

import hello.dao.BlogDao;
import hello.entity.BlogResult;
import hello.service.BlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class BlogController {
    private BlogService blogService;

    @Inject
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public BlogResult getBlog(@RequestParam("page") Integer userId, @RequestParam("userId") Integer page) {
        if (page == null | page < 0) {
            page = 1;
        }
        return blogService.getBlog(page,10,userId);
    }
}
