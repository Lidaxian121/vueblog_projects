package com.lidaxian.service;

import com.lidaxian.mapper.BlogMapper;
import com.lidaxian.mapper.UserMapper;
import com.lidaxian.pojo.Blog;
import com.lidaxian.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogMapper blogMapper;
    @Override
    public Blog queryBlogById(int id) {
        return blogMapper.queryBlogById(id);
    }

    @Override
    public int insertBlog(Blog blog){
        return blogMapper.insertBlog(blog);
    }

    @Override
    public int deleteBlogById(int id) {
        return blogMapper.deleteBlogById(id);
    }
}
