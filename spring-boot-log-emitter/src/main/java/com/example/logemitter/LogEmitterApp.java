package com.example.logemitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class LogEmitterApp {

    private static final Logger logger = LoggerFactory.getLogger(LogEmitterApp.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String[] regions = {"us-east-1", "eu-west-1", "ap-south-1"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(LogEmitterApp.class, args);
    }

    @PostConstruct
    public void onStart() {
        System.out.println("🔥 Spring Boot is alive and logging...");
        run();

    }


    public void run() {
        System.out.println("🔥 Spring Boot is alive and started logging...");
        while (true) {
            try {
                int status = random.nextInt(100) < 10 ? 500 : 200;
                int responseTime = 50 + random.nextInt(300);
                String region = regions[random.nextInt(regions.length)];
                String userId = "user_" + random.nextInt(1000);

                Map<String, Object> log = new HashMap<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String ts = ZonedDateTime.now(ZoneOffset.UTC).format(formatter);
                log.put("timestamp", ts);
                log.put("service", "orders-api");
                log.put("status", status);
                log.put("region", region);
                log.put("response_time_ms", responseTime);
                log.put("user_id", userId);

                logger.info(mapper.writeValueAsString(log));

                Thread.sleep(5000);
            } catch (Exception e) {
                logger.error("Error generating log", e);
            }
        }
    }
}

