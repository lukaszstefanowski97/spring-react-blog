package com.example.blog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationEvent implements ApplicationListener<ApplicationReadyEvent> {

    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Application is running...");
        log.info("Listening on http://localhost:8080...");
    }
}
