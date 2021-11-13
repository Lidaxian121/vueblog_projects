package com.lidaxian.service;

import com.lidaxian.pojo.Blog;

public interface BlogService {
    public Blog queryBlogById(int id);
    public int insertBlog(Blog blog);
    public int deleteBlogById(int id);
}
