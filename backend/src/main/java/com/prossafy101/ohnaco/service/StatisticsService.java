package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.statistics.StatisticsCategoryDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsPositionDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsTotalDto;
import com.prossafy101.ohnaco.repository.StatisticsRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    String[] category = {"CS", "알고리즘", "프레임워크", "자격증", "기타"};
    boolean[] isCategory = new boolean[5];

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
        List<StatisticsCategoryDto> list = statisticsRepository.getCategoryTime(map);
        for(StatisticsCategoryDto dto : list) {
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i]))
                    isCategory[i] = true;
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsCategoryDto(category[i]));
            }
        }

        return list;
    }

    public List<StatisticsPositionDto> getPositionTime(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        int positionid = userRepository.findByUserid(userid).getPositions().getPositionid();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("positionid", positionid);

        List<StatisticsPositionDto> list = statisticsRepository.getPositionTime(map);
        for(StatisticsPositionDto dto : list) {
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i]))
                    isCategory[i] = true;
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsPositionDto(category[i]));
            }
        }

        return list;
    }

    public List<StatisticsPositionDto> getEntireCategoryTime(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));

        List<StatisticsPositionDto> list = statisticsRepository.getEntireCategoryTime(map);
        for(StatisticsPositionDto dto : list) {
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i]))
                    isCategory[i] = true;
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsPositionDto(category[i]));
            }
        }

        return list;
    }

    public List<Map<String, Object>> getTotalTimeForDays(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        return statisticsRepository.getTotalTimeForDays(map);
    }

    public List<Map<String, Object>> getPositionTimeForDays(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        int positionid = userRepository.findByUserid(userid).getPositions().getPositionid();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("positionid", positionid);
        return statisticsRepository.getPositionTimeForDays(map);
    }

    public List<Map<String, Object>> getMyTimeForDays(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);
        return statisticsRepository.getMyTimeForDays(map);
    }

}
