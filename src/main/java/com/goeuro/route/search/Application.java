package com.goeuro.route.search;

import com.goeuro.route.search.boot.DataLoaderService;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        //String dataFilePath = "/Users/jayasagar/Dev/gl/assignments/ge-bus-route-search-service/sample-data.txt";
        // Load data and represent to serve the request
        if (args != null && args.length>0) {
            String dataFilePath = args[0];

            try (Stream<String> dataStream = Files.lines(Paths.get(dataFilePath))) {
                DataLoaderService dataLoaderService = applicationContext.getBean(DataLoaderService.class);
                dataLoaderService.loadData(dataStream);
            } catch (IOException e) {
                Logger.getLogger("Direct Route Search").severe(() -> "Please check your data input file path");
            }
        } else {
            throw new DataLoadStateException();
        }


    }
}
