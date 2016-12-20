package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import java.util.logging.Logger;

import static com.goeuro.route.search.Constants.LOG_APP_TAG;

@EqualsAndHashCode(of = {"id"}) @ToString
public final class Route {
    private final int id;
    private Set<Stop> stops = Sets.newLinkedHashSet();

    public Set<Stop> getStops() {
        return stops;
    }

    private Route(int id) {
        this.id = id;
    }

    public static Route of(String id) {
       return of(Integer.parseInt(id.trim()));
    }

    public static Route of(int id) {
        // Same Route being added again
        if (ApplicationData.allRoutes.containsKey(id)) {
            Logger.getLogger(LOG_APP_TAG).severe(() -> String.format("Duplicate Route %d", id));
            throw new DataLoadStateException(String.format("Duplicate Route %d", id));
        }
        Route route = new Route(id);
        ApplicationData.allRoutes.put(id, route);
        return route;
    }

    public void addStop(Stop stop) {
        boolean isAdded = stops.add(stop);
        if (!isAdded) { // Same stop being added again
            Logger.getLogger(LOG_APP_TAG).severe(() -> String.format("Duplicate stop %d in route %d", stop, id));
            throw new DataLoadStateException(String.format("Duplicate stop %d in route %d", stop, id));
        }
    }

    public boolean containsStep(Stop stop) {
        return stops.contains(stop);
    }
}
