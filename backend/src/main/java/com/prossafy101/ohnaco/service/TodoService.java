package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.todo.CommitDto;
import com.prossafy101.ohnaco.entity.todo.Todo;
import com.prossafy101.ohnaco.entity.todo.TodoDto;
import com.prossafy101.ohnaco.entity.user.User;
import com.prossafy101.ohnaco.repository.CategoryRepository;
import com.prossafy101.ohnaco.repository.CommitRepository;
import com.prossafy101.ohnaco.repository.TodoRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class TodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private GitHubAPI gitHubAPI;

    @Autowired
    private CommitRepository commitRepo;

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

    public List<Todo> getMonthTodos(String userid, String startDate, String endDate) {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59));

        List<Todo> list = todoRepository.getAllByDateBetweenAndUserOrderByDate(startDatetime, endDatetime, userRepository.findByUserid(userid));
        return list;
    }

    public void commitSave(CommitDto commitDto) {
        commitRepo.save(commitDto);
    }

    // userid에 커밋 기록을 반환한다
    public Optional<CommitDto> getCommit(String userid) {
        return commitRepo.findById(userid);
    }

    //commit기록 업데이트 해서 redis에 저장
    public void CommitUpdate(String userid, String githubid, String date) {
        Optional<CommitDto> commitOpt = getCommit(userid);
        CommitDto commitDto;
        if(!commitOpt.isPresent()) {    // 처음 commit정보를 가져올 경우
            commitDto = CommitDto.builder().userid(userid).build();
        } else {
            commitDto = commitOpt.get();
            //최신 update날짜가 같은경우 오늘 날짜만 커밋 업데이트한다.

            if (!commitDto.getUpdatedate().equals(date)) {    //다른경우 하루씩 미뤄주면서 업데이트 해준다.
                Calendar cal = Calendar.getInstance();
                cal.setTime(new java.util.Date());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                commitDto.setDay7(commitDto.getDay6());
                commitDto.setDay6(commitDto.getDay5());
                commitDto.setDay5(commitDto.getDay4());
                commitDto.setDay4(commitDto.getDay3());
                commitDto.setDay3(commitDto.getDay2());
                commitDto.setDay2(commitDto.getDay1());
            }
        }
        commitDto.setUpdatedate(date);
        commitDto.setDay1(gitHubAPI.isCommit(githubid, date));
        commitDto.setTimeToLive(2);
        commitSave(commitDto);
    }

    //매1시간마다 DB에서 githubid를 입력한 전체 유저 커밋기록 업데이트
    @Scheduled(cron = "0 0 0/1 * * *")
    public void totalCommitUpdate() {
        List<User> users = userRepository.findAll();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(User user: users) {
            if(user.getGithubid() != null) {
                CommitUpdate(user.getUserid(), user.getGithubid(),df.format(cal.getTime()));
            }
        }
    }
}
