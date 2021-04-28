package com.wp.tokenconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TokenconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenconsumerApplication.class, args);
		
		ScheduledExecutorService scheduled =
				Executors.newSingleThreadScheduledExecutor();
		scheduled.schedule(() -> {
			ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SpringApplication.class)
					.web(WebApplicationType.NONE).run();

			int exitCode = SpringApplication.exit(ctx, () -> {
				// return the error code
				return 0;
			});
			System.exit(exitCode);
		}, 180, TimeUnit.SECONDS);
	}

}
