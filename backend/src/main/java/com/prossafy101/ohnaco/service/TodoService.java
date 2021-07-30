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
                    .date(LocalDate.parse(dto.getDate()))
                    .goaltime(Time.valueOf(dto.getGoaltime()))
                    .issuccess(false)
                    .build()).getTodoid();
    }

    public List<Todo> getTodos(String userid, String date) {
        List<Todo> list = todoRepository.getAllByDateAndUser(LocalDate.parse(date), userRepository.findByUserid(userid));
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

}
