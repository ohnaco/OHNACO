package com.prossafy101.ohnaco.controller;

import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.StatisticsService;
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
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/info")
    @ApiOperation(value = "통계정보 반환환 =>email, password값 전달")
    public Object tempJoin(@RequestParam String option, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String token = req.getHeader("Authorization").substring(7);
        String userid = jwtUtil.getUserid(token);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        result.put("todayTime", statisticsService.getCategoryTime(userid, df.format(cal.getTime()), df.format(cal.getTime())));
        cal.add(Calendar.DATE, -1);
        result.put("yesterdayTime", statisticsService.getCategoryTime(userid, df.format(cal.getTime()), df.format(cal.getTime())));
        cal.setTime(new Date());
        if(option.equals("week")) {
            cal.add(Calendar.DATE, 0);
            String endDate = df.format(cal.getTime());
            cal.add(Calendar.DATE, -6);
            String startDate = df.format(cal.getTime());
            Long totalDto = statisticsService.getToatalTime(userid, startDate, endDate);
            result.put("totalTime", totalDto!=null?totalDto:0);
            result.put("categoryTime", statisticsService.getCategoryTime(userid, startDate, endDate));
            result.put("positionTime", statisticsService.getPositionTime(userid, startDate, endDate));
            result.put("entireCategoryTime", statisticsService.getEntireCategoryTime(startDate, endDate));
            result.put("entireMemberTime", statisticsService.getTotalTimeForDays(startDate, endDate));
            result.put("positionMemberTime", statisticsService.getPositionTimeForDays(userid, startDate, endDate));
            result.put("myTime", statisticsService.getMyTimeForDays(userid, startDate, endDate));

        } else {
            cal.add(Calendar.DATE, 0);
            String endDate = df.format(cal.getTime());
            cal.add(Calendar.DATE, -30);
            String startDate = df.format(cal.getTime());
            Long totalDto = statisticsService.getToatalTime(userid, startDate, endDate);
            result.put("totalTime", totalDto!=null?totalDto:0);
            result.put("categoryTime", statisticsService.getCategoryTime(userid, startDate, endDate));
            result.put("positionTime", statisticsService.getPositionTime(userid, startDate, endDate));
            result.put("entireCategoryTime", statisticsService.getEntireCategoryTime(startDate, endDate));
            result.put("myTime", statisticsService.getMyTimeForWeeks(userid, startDate, endDate));
            result.put("positionMemberTime", statisticsService.getPositionTimeForWeeks(userid, startDate, endDate));
            result.put("entireMemberTime", statisticsService.getTotalTimeForWeeks(startDate, endDate));

        }

        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
