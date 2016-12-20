package com.goeuro.route.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // Load data and represent to serve the request
        if (args != null && args.length>0) {
            String dataFilePath = args[0];

            try (Stream<String> dataStream = Files.lines(Paths.get(dataFilePath))) {
                //dataStream.
            } catch (IOException e) {
                Logger.getLogger("Direct Route Search").severe(() -> "Please check your data input file path");
            }
        }

    }
}
