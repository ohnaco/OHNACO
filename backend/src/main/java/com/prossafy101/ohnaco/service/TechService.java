package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.tech.Blog;
import com.prossafy101.ohnaco.entity.tech.BlogDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.BlogRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogDto> getBlogList(String userid) {

        List<Blog> list = blogRepository.findAll();
        List<BlogDto> dtoList = new ArrayList<>();
            for (Blog b : list) {
                dtoList.add(BlogDto.builder()
                        .blogid(b.getBlogid())
                        .blogname(b.getBlogname())
                        .description(b.getDescription())
                        .link(b.getLink())
                        .issubscribe(b.getUsers().contains(userRepository.findByUserid(userid))).build());
            }

        return dtoList;
    }

    public List<BlogDto> getSubscribeBlogList(String userid) {

        List<Blog> list = blogRepository.findAll();
        List<BlogDto> dtoList = new ArrayList<>();
        for (Blog b : list) {
            if(b.getUsers().contains(userRepository.findByUserid(userid))) {
                dtoList.add(BlogDto.builder()
                        .blogid(b.getBlogid())
                        .blogname(b.getBlogname())
                        .description(b.getDescription())
                        .link(b.getLink())
                        .issubscribe(true).build());
            }
        }

        return dtoList;
    }

    public void subscribeBlog(String userid, Long blogid) {

        Blog blog = blogRepository.findById(blogid).get();
        List<User> users = blog.getUsers();
        users.add(userRepository.findByUserid(userid));

        blogRepository.save(Blog.builder()
                .blogid(blog.getBlogid())
                .rss(blog.getRss())
                .users(users)
                .blogname(blog.getBlogname())
                .description(blog.getDescription())
                .link(blog.getLink())
                .build());
    }

    public void cancelSubscribe(String userid, Long blogid) {
        Blog blog = blogRepository.findById(blogid).get();
        List<User> users = blog.getUsers();
        users.remove(userRepository.findByUserid(userid));

        blogRepository.save(Blog.builder()
                .blogid(blog.getBlogid())
                .rss(blog.getRss())
                .users(users)
                .blogname(blog.getBlogname())
                .description(blog.getDescription())
                .link(blog.getLink())
                .build());
    }
}
