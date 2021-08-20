package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, Long> {


    Todo findByTitle(String title);

    Todo findByTodoid(String todoid);

    //    List<Todo> getAllByDateAndUser(LocalDate date, User user, Sort sort);
    List<Todo> findByDateAndUser(LocalDate date, User user, Sort sort);

    List<Todo> getAllByDateBetweenAndUser(LocalDate startDate, LocalDate endDate, User user, Sort sort);

    @Transactional
    void deleteTodoByTodoidAndUser(String todoid, User user);
}
