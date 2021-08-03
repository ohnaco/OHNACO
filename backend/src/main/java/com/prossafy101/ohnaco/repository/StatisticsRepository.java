package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.statistics.StatisticsCategoryDto;
import com.prossafy101.ohnaco.entity.statistics.StatisticsTotalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsRepository {
    StatisticsTotalDto getTotalTime(Map<String, Object> map);
    List<StatisticsCategoryDto> getCategoryTime(Map<String, Object> map);
}
