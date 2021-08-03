package com.prossafy101.ohnaco.entity.statistics;

import lombok.Data;

import java.sql.Time;

@Data
public class StatisticsCategoryDto {
    private String categoryname;
    private Time completetime;
    private Time goaltime;
}
