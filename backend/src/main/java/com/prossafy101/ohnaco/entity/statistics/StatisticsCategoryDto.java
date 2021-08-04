package com.prossafy101.ohnaco.entity.statistics;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
public class StatisticsCategoryDto {
    private String categoryname;
    private Time completetime;
    private Time goaltime;

    public StatisticsCategoryDto(String categoryname)  {
        this.categoryname = categoryname;
    }
}
