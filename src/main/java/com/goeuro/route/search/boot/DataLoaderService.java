package com.goeuro.route.search.boot;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import com.goeuro.route.search.model.Route;
import com.goeuro.route.search.model.Stop;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataLoaderService {
    public void loadData(Stream<String> dataStream) throws DataLoadStateException {

        Optional<String> numberOfRoutes = dataStream.findFirst();
        int totalRoutes = Integer.parseInt(numberOfRoutes.orElseThrow(() -> new DataLoadStateException()));

        dataStream
                .skip(1)
                // process each Route
                .forEach(line -> {
                    String[] lineData = line.split("\\s+");

                    // Validate length should be at least 3
                    if (lineData.length < 3) {
                        // TODO: log and add message
                        throw new DataLoadStateException();
                    }

                    String routeId = lineData[0];
                    Route route = Route.of(routeId);

                    for (int i = 1; i < lineData.length ; i++) {
                        route.addStop(Stop.of(lineData[i]));
                    }
                });

        // Validate list of routes match
        if (ApplicationData.allRoutes.size() != totalRoutes) {
            // TODO: log and add message
            throw new DataLoadStateException();
        }

    }
}
