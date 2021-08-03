package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.statistics.StatisticsCategoryDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsTotalDto;
import com.prossafy101.ohnaco.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    //해당 유저의 주간/월간 시간과 목표시간 총합
    public StatisticsTotalDto getToatalTime(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);
        return statisticsRepository.getTotalTime(map);
    }

    //해당 유저의 카테고리별 시간과 목표시간 총합
    public List<StatisticsCategoryDto> getCategoryTime(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);
        return statisticsRepository.getCategoryTime(map);
    }

}
