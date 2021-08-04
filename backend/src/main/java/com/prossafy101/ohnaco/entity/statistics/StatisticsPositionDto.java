package com.prossafy101.ohnaco.entity.statistics;

import lombok.Data;

import java.sql.Time;

@Data
public class StatisticsPositionDto {
    private String categoryname;
    private Time time;
}
