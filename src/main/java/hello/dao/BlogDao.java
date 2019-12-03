package hello.dao;

import hello.entity.Blog;
import hello.entity.BlogResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BlogDao {

    private final SqlSession sqlSession;

    @Inject
    public BlogDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Blog> getBlog(int page, int pageSize, int userId) {
        int offset = (page - 1) * pageSize;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_Id", userId);
        parameters.put("offset", offset);
        parameters.put("limit", pageSize);
        return this.sqlSession.selectList("getBlogFromDatabase", parameters);
    }

    public int getCount(Integer userId){
        return this.sqlSession.selectOne("getCountOfUserId",userId);
    }

}
