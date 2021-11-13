package com.lidaxian.mapper;

import com.lidaxian.pojo.Blog;
import com.lidaxian.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogMapper {
    public Blog queryBlogById(int id);
    public int insertBlog(Blog blog);
    public int deleteBlogById(int id);
}
