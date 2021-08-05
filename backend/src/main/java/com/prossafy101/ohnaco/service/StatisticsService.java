package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.statistics.StatisticsCategoryDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsPositionDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsTotalDto;
import com.prossafy101.ohnaco.repository.StatisticsRepository;
import com.prossafy101.ohnaco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private UserRepository userRepository;

    String[] category = {"CS", "알고리즘", "프레임워크", "자격증", "기타"};

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
        boolean[] isCategory = new boolean[5];
        long total = 0;
        List<StatisticsCategoryDto> list = statisticsRepository.getCategoryTime(map);
        for(StatisticsCategoryDto dto : list) {
            if(dto.getCompletetime() != null)
                total += dto.getCompletetime();
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i])) {
                    isCategory[i] = true;
                }
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsCategoryDto(category[i], 0L, 0L));
            }
        }
        Collections.sort(list, new Comparator<StatisticsCategoryDto>() {
            @Override
            public int compare(StatisticsCategoryDto o1, StatisticsCategoryDto o2) {
                return o1.getCategoryname().compareTo(o2.getCategoryname());
            }
        });
        StatisticsCategoryDto entire = new StatisticsCategoryDto("entire", total, 0L);
        list.add(entire);
        return list;
    }

    public List<StatisticsPositionDto> getPositionTime(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        int positionid = userRepository.findByUserid(userid).getPositions().getPositionid();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("positionid", positionid);
        boolean[] isCategory = new boolean[5];
        List<StatisticsPositionDto> list = statisticsRepository.getPositionTime(map);
        for(StatisticsPositionDto dto : list) {
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i]))
                    isCategory[i] = true;
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsPositionDto(category[i], 0L));
            }
        }
        Collections.sort(list, new Comparator<StatisticsPositionDto>() {
            @Override
            public int compare(StatisticsPositionDto o1, StatisticsPositionDto o2) {
                return o1.getCategoryname().compareTo(o2.getCategoryname());
            }
        });
        return list;
    }

    public List<StatisticsPositionDto> getEntireCategoryTime(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        boolean[] isCategory = new boolean[5];
        List<StatisticsPositionDto> list = statisticsRepository.getEntireCategoryTime(map);
        for(StatisticsPositionDto dto : list) {
            for(int i = 0 ; i < 5;  i++) {
                if(dto.getCategoryname().equals(category[i]))
                    isCategory[i] = true;
            }
        }

        for(int i = 0 ; i < 5;  i++) {
            if(!isCategory[i]) {
                list.add(new StatisticsPositionDto(category[i], 0L));
            }
        }
        Collections.sort(list, new Comparator<StatisticsPositionDto>() {
            @Override
            public int compare(StatisticsPositionDto o1, StatisticsPositionDto o2) {
                return o1.getCategoryname().compareTo(o2.getCategoryname());
            }
        });
        return list;
    }

    public List<Map<String, Object>> getTotalTimeForDays(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        List<Map<String, Object>> list = statisticsRepository.getTotalTimeForDays(map);
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = startDate.split("-");
        cal.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, Integer.parseInt(split[2]));
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("date").toString().equals(df.format(cal.getTime()))) {
                map = new HashMap<>();
                map.put("date", df.format(cal.getTime()));
                map.put("time", 0L);
                list.add(i, map);
            }
            cal.add(Calendar.DATE, 1);
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("date", df.format(cal.getTime()));
            map.put("time", 0L);
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }

        return list;
    }

    public List<Map<String, Object>> getPositionTimeForDays(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        int positionid = userRepository.findByUserid(userid).getPositions().getPositionid();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("positionid", positionid);
        List<Map<String, Object>> list = statisticsRepository.getPositionTimeForDays(map);
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = startDate.split("-");
        cal.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, Integer.parseInt(split[2]));
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("date").toString().equals(df.format(cal.getTime()))) {
                map = new HashMap<>();
                map.put("date", df.format(cal.getTime()));
                map.put("time", 0L);
                list.add(i, map);
            }
            cal.add(Calendar.DATE, 1);
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("date", df.format(cal.getTime()));
            map.put("time", 0L);
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }

        return list;
    }

    public List<Map<String, Object>> getMyTimeForDays(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);
        List<Map<String, Object>> list = statisticsRepository.getMyTimeForDays(map);
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = startDate.split("-");
        cal.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, Integer.parseInt(split[2]));
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("date").toString().equals(df.format(cal.getTime()))) {
                map = new HashMap<>();
                map.put("date", df.format(cal.getTime()));
                map.put("time", 0L);
                list.add(i, map);
            }
            cal.add(Calendar.DATE, 1);
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("date", df.format(cal.getTime()));
            map.put("time", 0L);
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }

        return list;
    }

    public List<Map<String, Object>> getMyTimeForWeeks(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);
        List<Map<String, Object>> list = statisticsRepository.getMyTimeForWeeks(map);
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("day").toString().equals(Integer.toString(i+1))) {
                map = new HashMap<>();
                map.put("day", i+1);
                map.put("time", 0L);
                list.add(i, map);
            }
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("day", i+1);
            map.put("time", 0L);
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getPositionTimeForWeeks(String userid, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));
        map.put("userid", userid);

        List<Map<String, Object>> list = statisticsRepository.getPositionTimeForWeeks(map);
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("day").toString().equals(Integer.toString(i+1))) {
                map = new HashMap<>();
                map.put("day", i+1);
                map.put("time", 0L);
                list.add(i, map);
            }
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("day", i+1);
            map.put("time", 0L);
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getTotalTimeForWeeks(String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0,0,0)));
        map.put("endDate", LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23,59,59)));

        List<Map<String, Object>> list = statisticsRepository.getTotalTimeForWeeks(map);
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).get("day").toString().equals(Integer.toString(i+1))) {
                map = new HashMap<>();
                map.put("day", i+1);
                map.put("time", 0L);
                list.add(i, map);
            }
        }
        for(int i=list.size(); i<7; i++) {
            map = new HashMap<>();
            map.put("day", i+1);
            map.put("time", 0L);
            list.add(map);
        }
        return list;
    }

}
