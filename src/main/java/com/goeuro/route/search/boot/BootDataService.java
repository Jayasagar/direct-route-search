package com.goeuro.route.search.boot;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import com.goeuro.route.search.model.Route;
import com.goeuro.route.search.model.Stop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.goeuro.route.search.Constants.LOG_APP_TAG;

@Service
public class BootDataService {
    public void loadData(Stream<String> dataStream) {

        List<String> dataAsCollection = dataStream.collect(Collectors.toList());

        Optional<String> numberOfRoutes = dataAsCollection.stream().findFirst();

        int totalRoutes = -1;
        try {
            totalRoutes = Integer.parseInt(numberOfRoutes.orElseThrow(() -> new DataLoadStateException("Provide valid total number of routes")).trim());
        } catch (Exception e) {
            Logger.getLogger(LOG_APP_TAG).severe(() -> "Problem with first line in data file. verify and re-run");
            throw new DataLoadStateException("Problem with first line in data file. verify and re-run");
        }

        // FIXME: Can we fix it without foreach!!
        dataAsCollection
                .stream()
                .skip(1)
                // process each Route
                .forEach(new RouteParseConsumer());

        // Validate list of routes match
        if (ApplicationData.allRoutes.size() != totalRoutes) {
            Logger.getLogger(LOG_APP_TAG).severe(() -> String.format("Data mismatch: total routes found %d", ApplicationData.allRoutes.size()));
            throw new DataLoadStateException(String.format("Data mismatch: total routes found %d", ApplicationData.allRoutes.size()));
        }

        Logger.getLogger("Direct Search Route").info(() -> "data:" + ApplicationData.allRoutes);
    }

    class RouteParseConsumer implements Consumer<String> {
        @Override
        public void accept(String line) {
            String[] lineData = line.split("\\s+");

            // Validate length should be at least 3
            if (lineData.length < 3) {
                Logger.getLogger(LOG_APP_TAG).severe(() -> String.format("Invalid route data %s", line));
                throw new DataLoadStateException(String.format("Invalid route data %s", line));
            }

            String routeId = lineData[0];
            Route route = Route.of(routeId);

            for (int i = 1; i < lineData.length ; i++) {
                route.addStop(Stop.of(lineData[i]));
            }
        }
    }
}
