package nl.mmelsen.triggerservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.CleanupConfig;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class TriggerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(TriggerServiceApplication.class, args);
        if (log.isInfoEnabled()) {
            log.info("TriggerServiceApplication started");
            log.info("With command-line arguments {}", Arrays.toString(args));
        }
    }

    @Bean
    public CleanupConfig cleanupConfig() {
        return new CleanupConfig(false, false);
    }
}
