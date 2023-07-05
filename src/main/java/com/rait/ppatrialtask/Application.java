package com.rait.ppatrialtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * A simple Java app that enables adding integers and querying previously done additions using two
 * REST services. The integer sum objects are stored using a static list as a stand-in for a database.
 * The solution had to be thread-safe but simple.
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
