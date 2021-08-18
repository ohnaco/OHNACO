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

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/item")
    @ApiOperation(value = "Todo 생성하기 => title, categoryid, goaltime, date 전달")
    public Object createTodo(@RequestBody TodoDto dto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            Todo todo = todoService.createTodo(dto, userid);

            result.put("status", "success");
            result.put("todo", todo);
        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping("/item")
    @ApiOperation(value = "Todo 수정하기 => todoid, title, categoryid, goaltime, date 전달")
    public Object modifyTodo(@RequestBody TodoDto dto) {
        Map<String, Object> result = new HashMap<>();

        try {
            Todo newTodo = todoService.modifyTodo(dto);

            result.put("todo", newTodo);
            result.put("status", "success");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/item")
    @ApiOperation(value = "Todo 삭제하기 => todoid 전달")
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
    @ApiOperation(value = "Todo 완료 => todoid, completetime 전달")
    public Object completeTodo(@RequestBody TodoDto dto) {
        Map<String, Object> result = new HashMap<>();

        try {
            result.put("todo", todoService.completeTodo(dto));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/past")
    @ApiOperation(value = "Todo 과거로부터 복사 => todoid, date 전달")
    public Object addTodo(@RequestBody Map<String, String> map, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            result.put("todo", todoService.addTodo(userid, map.get("todoid"), map.get("date")));
            result.put("status", "success");
        } catch(Exception e) {
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/month")
    @ApiOperation(value = "해당 년월 Todo 불러오기 => date 전달")
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
        Calendar cal = Calendar.getInstance();
        try {
            cal.set(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]) - 1, 1);
        } catch (Exception e) {
            result.put("status", "fail");
            result.put("message", "잘못된 정보입니다.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        endDate.append("-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        try {
            list = todoService.getMonthTodos(userid, startDate.toString(), endDate.toString());
            result.put("list", list);
            if(list.size() == 0)
                result.put("status", "success");
            else
                result.put("status", "fail");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "fail");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
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
        if("".equals(user.getGithubid())) {
            result.put("status", false);
            result.put("message", "GitHubID 없음");
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, -9);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                todoService.commitUpdate(userid, user.getGithubid(), df.format(cal.getTime()));
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

    @PutMapping("/statechange")
    @ApiOperation(value = "todoid값 필요 ongoing값 반대로 만들어주기기")
    public Object updateOngoing(@RequestBody TodoDto todoDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            todoService.updateCompleteTime(todoDto.getTodoid(), null, userid, 0);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/timeupdate")
    @ApiOperation(value = "todoid, completetime 필요 업데이트해주기")
    public Object updateCompeletTime(@RequestBody TodoDto todoDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            todoService.updateCompleteTime(todoDto.getTodoid(), todoDto.getCompletetime(), userid, 1);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/forcequit")
    @ApiOperation(value = "todoid, completetime 필요 업데이트해주기")
    public Object updateForceQuit(@RequestBody TodoDto todoDto, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        try {
            todoService.updateCompleteTime(todoDto.getTodoid(), todoDto.getCompletetime(), userid, 2);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/testcommitupdateall")
    public void updateCommit(HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        User user = userService.findByUserid(userid);
        todoService.commitUpdateWeekend(userid, user.getGithubid());
    }
}
