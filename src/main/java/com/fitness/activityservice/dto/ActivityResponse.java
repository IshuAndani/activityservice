package com.fitness.activityservice.dto;

import com.fitness.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType activityType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;
    private Integer caloriesBurned;
    private Map<String,Object> metrics;
}
