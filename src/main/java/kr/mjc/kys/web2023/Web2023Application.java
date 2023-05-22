package kr.mjc.kys.web2023;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(basePackages = "kr.mjc.kys.web2023")
@Slf4j
public class Web2023Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Web2023Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(Web2023Application.class);
    }
}
