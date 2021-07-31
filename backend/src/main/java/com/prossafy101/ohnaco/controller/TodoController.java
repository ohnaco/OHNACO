package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.todo.TodoDto;
import com.prossafy101.ohnaco.repository.CategoryRepository;
import com.prossafy101.ohnaco.repository.TodoRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import com.prossafy101.ohnaco.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/item")
    @ApiOperation(value = "Todo 불러오기 => userid, date 전달")
    public Object getTodos(@RequestParam String userid, @RequestParam String date) {
        Map<String, Object> result = new HashMap<>();
        List<Todo> list = null;

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
    public Object deleteTodo(@RequestParam String userid, @RequestParam String todoid) {
        Map<String, String> result = new HashMap<>();
        try {
            todoService.deleteTodo(todoid, userid);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/complete")
    public Object completeTodo(@RequestParam String userid, @RequestParam String todoid) {
        Map<String, Object> result = new HashMap<>();

        try {
            result.put("todo", todoService.completeTodo(userid, todoid));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
