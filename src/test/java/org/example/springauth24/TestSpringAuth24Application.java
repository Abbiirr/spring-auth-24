package org.example.springauth24;

import org.springframework.boot.SpringApplication;

public class TestSpringAuth24Application {

    public static void main(String[] args) {
        SpringApplication.from(SpringAuth24Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
