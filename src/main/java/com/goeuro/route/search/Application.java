package com.goeuro.route.search;

import com.goeuro.route.search.boot.BootDataService;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.goeuro.route.search.Constants.LOG_APP_TAG;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        //String dataFilePath = "/Users/jayasagar/Dev/gl/assignments/ge-bus-route-search-service/sample-data.txt";
        // Load data and represent to serve the request
        try {
            if (args != null && args.length > 0) {
                String dataFilePath = args[0];
                Stream<String> dataStream = Files.lines(Paths.get(dataFilePath));

                // delegate to data loader service
                BootDataService bootDataService = applicationContext.getBean(BootDataService.class);
                bootDataService.loadData(dataStream);
            } else {
                Logger.getLogger(LOG_APP_TAG).severe(() -> "Please check your data input file path");
                throw new DataLoadStateException("Please check your data input file path");
            }
        } catch (Exception e) {
            Logger.getLogger(LOG_APP_TAG).severe(() -> e.getMessage());
            System.exit(0);
        }
    }
}
