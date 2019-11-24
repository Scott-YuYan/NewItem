package hello.entity;

import java.util.List;

public class BlogResult extends Result<List<Blog>> {
    private int total;
    private int page;
    private int totalPage;

    public static BlogResult successBlogResultBuilder(List<Blog> blogs, int total, int page, int totalPage){
        return new BlogResult(blogs, total, page, totalPage);
    }

    private BlogResult(List<Blog> blogs, int total, int page, int totalPage) {
        super("ok", "获取成功", blogs);
        this.total = total;
        this.page = page;
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
