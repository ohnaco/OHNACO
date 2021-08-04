package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.statistics.StatisticsCategoryDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsPositionDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsTotalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsRepository {
    StatisticsTotalDto getTotalTime(Map<String, Object> map);
    List<StatisticsCategoryDto> getCategoryTime(Map<String, Object> map);
    List<StatisticsPositionDto> getPositionTime(Map<String, Object> map);
    List<Map<String, Object>> getTotalTimeForDays(Map<String, Object> map);
    List<Map<String, Object>> getPositionTimeForDays(Map<String, Object> map);
    List<Map<String, Object>> getMyTimeForDays(Map<String, Object> map);
}
