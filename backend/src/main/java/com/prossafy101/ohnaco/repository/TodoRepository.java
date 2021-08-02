package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, Long> {


    Todo findByTitle(String title);

    Todo findByTodoid(String todoid);

    List<Todo> getAllByDateBetweenAndUserOrderByDate(LocalDateTime startDatetime, LocalDateTime endDatetime, User user);

    void deleteTodoByTodoidAndUser(String todoid, User user);
}
