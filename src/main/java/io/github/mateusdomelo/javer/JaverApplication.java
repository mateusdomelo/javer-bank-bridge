package io.github.mateusdomelo.javer;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@Generated
public class JaverApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaverApplication.class, args);
    }
}
