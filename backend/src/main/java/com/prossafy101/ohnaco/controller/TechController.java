package com.prossafy101.ohnaco.controller;

import com.google.api.Http;
import com.prossafy101.ohnaco.entity.tech.Article;
import com.prossafy101.ohnaco.entity.tech.ArticleDto;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.RedisUtil;
import com.prossafy101.ohnaco.service.TechService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tech")
public class TechController {

    @Autowired
    private TechService techService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    String scrapKey = "tech:scrap:";

    @GetMapping("/blog/list")
    @ApiOperation("블로그 목록 전체 불러오기")
    public Object getBlogList(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        result.put("list", techService.getBlogList(userid));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/blog/subscribe")
    public Object getSubscribedBlogList(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        result.put("list", techService.getSubscribeBlogList(userid));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/blog/subscribe")
    public Object subscribeBlog(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        try {
            techService.subscribeBlog(userid, Long.parseLong(map.get("blogid")));
            result.put("status", "success");
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/blog/subscribe")
    public Object cancelSubscribe(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);


        try {
            techService.cancelSubscribe(userid, Long.parseLong(map.get("blogid")));
            result.put("status", "success");
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/article/list")
    public Object getAllArticles(@RequestParam(required = false, defaultValue = "1") int pageno,
                                 @RequestParam(required = false, defaultValue = "") String keyword,
                                 HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        Pageable page = PageRequest.of(pageno-1, 10, Sort.by("publisheddate").descending());
        Page<Article> temp = techService.getAllArticles(page, keyword);
        Page<ArticleDto> list = temp.map(article -> new ArticleDto(article, redisUtil.getScrapUseridData(scrapKey + userid,
                article.getArticleid().toString()),
                techService.isSubscribe(userid, article.getBlogid())));
        result.put("list", list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/article/subscribe")
    public Object getSubscribeArticles(@RequestParam(required = false, defaultValue = "1") int pageno,
                                       @RequestParam(required = false, defaultValue = "") String keyword,
                                       HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        Pageable page = PageRequest.of(pageno-1, 10, Sort.by("publisheddate").descending());
        
        Object list = null;
        try {
            Page<Article> temp = techService.getSubscribeArticles(page, userid);
            list = temp.map(article -> new ArticleDto(article,
                    redisUtil.getScrapUseridData(scrapKey + userid, article.getArticleid().toString()),
                    techService.isSubscribe(userid, article.getBlogid())));
        } catch (Exception e) {
            e.printStackTrace();
            list = new HashMap<String, Object>();
            ((HashMap<String, Object>) list).put("content", new ArrayList<>());
            
        }
        result.put("list", list);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/scrap")
    public Object setScrap(@RequestParam String articleid, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        if(redisUtil.getScrapUseridData(scrapKey + userid, articleid))
            redisUtil.removeScrapData(scrapKey + userid, articleid);
        else
            redisUtil.setScrapData(scrapKey + userid, articleid);

        result.put("status", "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




    @GetMapping("/test")
    public void test() {
        techService.getRSSArticles();
    }
}
