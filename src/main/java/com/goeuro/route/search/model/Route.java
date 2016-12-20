package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

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
       return of(Integer.parseInt(id));
    }

    public static Route of(int id) {
        // Same Route being added again
        if (ApplicationData.allRoutes.containsKey(id)) {
            // TODO: log and add message
            throw new DataLoadStateException();
        }
        Route route = new Route(id);
        ApplicationData.allRoutes.put(id, route);
        return route;
    }

    public void addStop(Stop stop) {
        boolean isAdded = stops.add(stop);
        if (!isAdded) { // Same stop being added again
            // TODO: log and add message
            throw new DataLoadStateException();
        }
    }

    public boolean containsStep(Stop stop) {
        return stops.contains(stop);
    }
}
