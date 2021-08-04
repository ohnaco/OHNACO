package com.prossafy101.ohnaco.entity.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
public class StatisticsPositionDto {
    private String categoryname;
    private Time time;

    public StatisticsPositionDto(String categoryname)  {
        this.categoryname = categoryname;
    }
}
