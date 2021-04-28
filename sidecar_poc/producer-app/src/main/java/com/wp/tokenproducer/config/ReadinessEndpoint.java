package com.wp.tokenproducer.config;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ReadinessEndpoint {

    private final TokenWatchConfiguration tokenWatchConfiguration;
    private final ApplicationEventPublisher eventPublisher;

    public ReadinessEndpoint(TokenWatchConfiguration tokenWatchConfiguration, ApplicationEventPublisher eventPublisher) {
        this.tokenWatchConfiguration = tokenWatchConfiguration;
        this.eventPublisher = eventPublisher;
        ScheduledExecutorService scheduled =
                Executors.newSingleThreadScheduledExecutor();
        scheduled.schedule(() -> {
            AvailabilityChangeEvent.publish(eventPublisher,
                    this, ReadinessState.REFUSING_TRAFFIC);
        }, 60, TimeUnit.SECONDS);
    }

}