package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.noti.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {

    @Autowired
    private GitHubAPI gitHubAPI;

    @Autowired
    private UserService userService;

    @Autowired
    private FCMService fcmService;

    private final Map<String, String> tokenMap = new HashMap<>();

    public void register(final String userid, final String token) {
        tokenMap.put(userid, token);
    }

    public void nofify() throws ExecutionException, InterruptedException {
        for(String userid : tokenMap.keySet()) {
            if(gitHubAPI.isCommit(userService.findByUserid(userid).getGithubid(), LocalDate.now().toString()) == 0){
                fcmService.send(new NotificationRequest(
                        "1일 1커밋 알림"
                        ,"오늘 커밋을 아직 하지 않았습니다!"
                        ,tokenMap.get(userid)));
            }
        }
    }

}
