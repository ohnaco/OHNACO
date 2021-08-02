package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.todo.TodoDto;
import com.prossafy101.ohnaco.repository.CategoryRepository;
import com.prossafy101.ohnaco.repository.TodoRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class TodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TodoRepository todoRepository;

    public String createTodo(TodoDto dto) {
            return todoRepository.save(Todo.builder()
                    .todoid(createTodoid())
                    .user(userRepository.findByUserid(dto.getUserid()))
                    .title(dto.getTitle())
                    .category(categoryRepository.findByCategoryid(dto.getCategoryid()))
                    .date(LocalDateTime.now())
                    .goaltime(Time.valueOf(dto.getGoaltime()))
                    .issuccess(false)
                    .build()).getTodoid();
    }

    public String addTodo(String userid, String todoid) {
        Todo todo = todoRepository.findByTodoid(todoid);
        String newTodo = todoRepository.save(Todo.builder()
                .todoid(createTodoid())
                .user(userRepository.findByUserid(userid))
                .title(todo.getTitle())
                .category(todo.getCategory())
                .date(LocalDateTime.now())
                .goaltime(todo.getGoaltime())
                .issuccess(false)
                .build()).getTodoid();

        return newTodo;
    }

    public List<Todo> getTodos(String userid, String date) {

        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(23,59,59));

        List<Todo> list = todoRepository.getAllByDateBetweenAndUserOrderByDate(startDatetime, endDatetime, userRepository.findByUserid(userid));
        return list;
    }

    public String createTodoid() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        do {
            for (int i = 0; i < 8; i++) {
                int randNum = rand.nextInt(2);
                if (randNum == 0) {
                    sb.append((char) ((int) (rand.nextInt(26) + 'A')));
                } else {
                    sb.append(rand.nextInt(10));
                }
            }
        } while(todoRepository.findByTodoid(sb.toString()) != null);
        return sb.toString();
    }


    public Todo modifyTodo(TodoDto dto) {
        Todo todo = todoRepository.findByTodoid(dto.getTodoid());

        todo.setTitle(dto.getTitle());
        todo.setGoaltime(Time.valueOf(dto.getGoaltime()));
        todo.setCategory(categoryRepository.findByCategoryid(dto.getCategoryid()));
        todoRepository.save(todo);
        return todo;
    }

    public void deleteTodo(String userid, String todoid) {
        todoRepository.deleteTodoByTodoidAndUser(todoid, userRepository.findByUserid(userid));
    }

    public Todo completeTodo(String userid, String todoid) {
        Todo todo = todoRepository.findByTodoid(todoid);

        todo.setIssuccess(true);
        todoRepository.save(todo);
        return todo;
    }
}
