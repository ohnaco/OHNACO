package com.prossafy101.ohnaco.entity.statistics;

import lombok.Data;

import java.sql.Time;

@Data
public class StatisticsTotalDto {
    private Time totalcompletetime;
    private Time totalgoaltime;
}
