package com.wp.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Configuration
@EnableScheduling
public class TokenSchedulerConfig {

    public TokenSchedulerConfig(@Value("${tokenfile.path}") String filename) throws InterruptedException {
        this.path = Paths.get(filename);
        System.out.println("Initializing token on start-up" + filename);
        Thread.sleep(2000l);
        this.generateAndWriteToken(true);
    }

    private Path path;

    @Scheduled(fixedDelayString = "${refresh.interval}")
    public void scheduleFixedDelayTask() {
        System.out.println("Generating new token... and saving at " + path.getFileName());
        this.generateAndWriteToken(false);
    }

    private void generateAndWriteToken(boolean firstAttempt) {
        try {
            String tokenValue = "tokenvalue-" + System.currentTimeMillis() / 1000;
            if (firstAttempt) {
                Files.write(path, tokenValue.getBytes());
            } else {
                ByteBuffer buf = ByteBuffer.wrap(tokenValue.getBytes());
                try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE)) {
                    channel.write(buf);
                }
            }
        } catch(IOException ex) {
            System.out.println("IOException is caught...Not able toi write the token");
        }
    }
}
