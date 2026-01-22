package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    public ActivityResponse createActivity(ActivityRequest requestActivity) {
        boolean userExist = userValidationService.validateUser(requestActivity.getUserId());
        if(!userExist){
            throw new RuntimeException("User does not exist");
        }
        Activity activity = Activity.builder()
                .userId(requestActivity.getUserId())
                .activityType(requestActivity.getActivityType())
                .startTime(requestActivity.getStartTime())
                .endTime(requestActivity.getEndTime())
                .metrics(requestActivity.getMetrics())
                .caloriesBurned(requestActivity.getCaloriesBurned())
                .build();
        System.out.println(activity);
        Activity savedActivity = activityRepository.save(activity);
        System.out.println(savedActivity);
        return mapToResponse(savedActivity);
    }

    public ActivityResponse mapToResponse(Activity activity){
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setActivityType(activity.getActivityType());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setEndTime(activity.getEndTime());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setMetrics(activity.getMetrics());
        return activityResponse;
    }

    public @Nullable List<ActivityResponse> getUserActivities(String userId) {
//        boolean userExist = userValidationService.validateUser(userId);
//        if(!userExist){
//            throw new RuntimeException("User does not exist");
//        }
        List<Activity> activities = activityRepository.findByUserId(userId);
//        List<Activity> activities = activityRepository.findAll(Example.of(Activity.builder().userId(userId).build()));
        return activities.stream().map(this::mapToResponse).toList();
    }

    public @Nullable ActivityResponse getActivityById(String id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        return mapToResponse(activity);
    }
}
