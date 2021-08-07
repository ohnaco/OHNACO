package com.prossafy101.ohnaco.entity.statistics;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
public class StatisticsCategoryDto {
    private String categoryname;
    private Long completetime;
    private Long goaltime;

    public StatisticsCategoryDto(String categoryname)  {
        this.categoryname = categoryname;
    }

    public StatisticsCategoryDto(String categoryname, Long completetime) {
        this.categoryname = categoryname;
        this.completetime = completetime;
    }

    public StatisticsCategoryDto(String categoryname, Long completetime, Long goaltime) {
        this.categoryname = categoryname;
        this.completetime = completetime;
        this.goaltime = goaltime;
    }
}
