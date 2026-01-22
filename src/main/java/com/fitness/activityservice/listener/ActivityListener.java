package com.fitness.activityservice.listener;

import com.fitness.activityservice.model.Activity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ActivityListener extends AbstractMongoEventListener<Activity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Activity> event) {
        Activity activity = event.getSource();
        activity.calculateDuration();
    }
}