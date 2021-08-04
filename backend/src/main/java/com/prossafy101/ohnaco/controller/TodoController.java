package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.todo.TodoDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.CategoryRepository;
import com.prossafy101.ohnaco.repository.TodoRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.TodoService;
import com.prossafy101.ohnaco.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserService userService;

    @GetMapping("/item")
    @ApiOperation(value = "Todo 불러오기 => userid, date 전달")
    public Object getTodos(@RequestParam String date, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        List<Todo> list = null;
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);

        try {
            list = todoService.getTodos(userid, date);
            result.put("list", list);
            if(list.size() == 0)
                result.put("status", "success");
            else
                result.put("status", "empty");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/item")
    public Object createTodo(@RequestBody TodoDto dto) {
        Map<String, String> result = new HashMap<>();
        try {
            String todoid = todoService.createTodo(dto);

            result.put("status", "success");
            result.put("todoid", todoid);
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping("/item")
    public Object modifyTodo(@RequestBody TodoDto dto) {
        Map<String, Object> result = new HashMap<>();

        try {
            Todo newTodo = todoService.modifyTodo(dto);

            result.put("newTodo", newTodo);
            result.put("status", "success");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/item")
    public Object deleteTodo(@RequestParam String todoid, HttpServletRequest req) {
        Map<String, String> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            todoService.deleteTodo(todoid, userid);
            result.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/complete")
    public Object completeTodo(@RequestBody TodoDto dto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            result.put("todo", todoService.completeTodo(userid, dto));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/past")
    public Object addTodo(@RequestParam String todoid, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            result.put("todo", todoService.addTodo(userid, todoid));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/month")
    @ApiOperation(value = "해당 년월 Todo 불러오기 => userid, date 전달")
    public Object getMonthTodos(@RequestParam String date, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        List<Todo> list = null;
        String[] splitDate = date.split("-");
        StringBuffer startDate = new StringBuffer();
        startDate.append(splitDate[0]);
        startDate.append("-");
        startDate.append(splitDate[1]);
        startDate.append("-01");
        StringBuffer endDate = new StringBuffer();
        endDate.append(splitDate[0]);
        endDate.append("-");
        endDate.append(splitDate[1]);
        endDate.append("-31");
        try {
            list = todoService.getMonthTodos(userid, startDate.toString(), endDate.toString());
            result.put("list", list);
            if(list.size() == 0)
                result.put("status", "success");
            else
                result.put("status", "empty");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/commit")
    @ApiOperation(value = "커밋 정보 업데이트 및 반환")
    public Object checkId(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user;
        try {
            user = userService.findByUserid(userid);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "db 유저 아이디 검색 오류");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if(user.getGithubid() == null) {
            result.put("status", false);
            result.put("message", "GitHubID 없음");
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                todoService.CommitUpdate(userid, user.getGithubid(), df.format(cal.getTime()));
                result.put("commit", todoService.getCommit(userid).get());
                result.put("status", true);
                result.put("message", "업데이트 성공");

            } catch (Exception e) {
                result.put("status", false);
                result.put("message", "커밋 업데이트 오류");
            }
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
