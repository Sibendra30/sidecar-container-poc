package com.wp.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@Configuration
// @Profile("consumer")
public class TokenWatchConfiguration {
    private byte [] tokenBytes;
    public String getAuthToken() {
        return new String(tokenBytes);
    }

    public TokenWatchConfiguration(@Value("${token.file.path}") String dir) throws InterruptedException, IOException {
        Path path = Paths.get(dir+ "/token.txt");
        while(!Files.exists(path)) {
            Thread.sleep(5000l);
        }
        tokenBytes = Files.readAllLines(path).get(0).getBytes();
        Executors.newSingleThreadExecutor().execute(() -> watchTokenStore(dir));

    }

    public void watchTokenStore(String dir) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(dir);
            path.register(watchService, ENTRY_MODIFY);

            boolean poll = true;
            String token = null;
            while (poll) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path path1 = Paths.get(dir + "/" + event.context());
                    try {
                        token = Files.readAllLines(path1).get(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tokenBytes = token.getBytes();
                    System.out.println("Event kind : " + event.kind() + " - File : " + event.context() + "Content : " + token);
                }
                poll = key.reset();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
