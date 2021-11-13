package com.lidaxian.controller;

import com.lidaxian.pojo.Blog;
import com.lidaxian.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,vueblog");
        return "index";
    }

    @RequestMapping("/blog/toSerch")
    public String toSerch(){
        return "blog/serch";
    }
    @ResponseBody
    @RequestMapping("/blog/serch")
    public Blog blogSerch(int id){
        Blog blog=blogService.queryBlogById(id);
        return blog;
    }

    @RequestMapping("/blog/toAdd")
    public String toEdit(){
        return "blog/add";
    }
    @RequestMapping("/blog/add")
    public String edit(String title, String description, String content,Model model) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setContent(content);
        blogService.insertBlog(blog);
        model.addAttribute("msg","您的博客已经成功提交;");
        return "index";
    }

    @RequestMapping("/blog/toDelete")
    public String toDelete(){
        return "blog/delete";
    }
    @RequestMapping("/blog/delete")
    public String blogSerch(int id,Model model){
        blogService.deleteBlogById(id);
        model.addAttribute("msg","已成功删除此博客;");
        return "index";
    }


}
