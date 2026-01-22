package com.fitness.activityservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String userId;
    private ActivityType activityType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer duration;
    private Integer caloriesBurned;
    private Map<String,Object> metrics;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void calculateDuration() {
        if (startTime != null && endTime != null) {
            this.duration = (int) Duration.between(startTime, endTime).toMinutes();
        }
    }
}
