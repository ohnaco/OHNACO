package com.prossafy101.ohnaco.entity.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("commit")
public class CommitDto {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String updatedate;
    private int day1;
    private int day2;
    private int day3;
    private int day4;
    private int day5;
    private int day6;
    private int day7;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TimeToLive(unit = TimeUnit.HOURS)
    private int timeToLive;
}