package com.github.dayagz.springboot.events.enhancedconsumer;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
@Timed
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventGenerator eventGenerator;

    @Autowired
    private EventReplayer eventReplayer;

    @PostMapping("generate")
    public ResponseEntity<String> generateEvents() {
        try {
            eventGenerator.produceEvent();
            return ResponseEntity.ok("Scheduled");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PostMapping("replay")
    public ResponseEntity<String> replayEvents() {
        try {
            eventReplayer.replayEvents();
            return ResponseEntity.ok("Scheduled");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }
}
