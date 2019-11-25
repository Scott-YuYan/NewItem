package hello.controller;

import hello.entity.BlogResult;
import hello.service.BlogService;
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
    public BlogResult getBlog(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "userId", required = false) Integer userId) {
        if (page == null || page < 0) {
            page = 1;
        }
        if (userId == null || userId <0){
            userId = 0;
        }
        return blogService.getBlog(page,10,userId);
    }
}
