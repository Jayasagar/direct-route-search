package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@EqualsAndHashCode(of = {"id"}) @ToString
public final class Route {
    private final int id;
    private Set<Stop> stops = Sets.newHashSet();

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
        if (ApplicationData.allRoutes.containsKey(id)) {
            // Throw exception
        }
        Route route = new Route(id);
        ApplicationData.allRoutes.put(id, route);
        return route;
    }

    public void addStop(Stop stop) {
        boolean isAdded = stops.add(stop);
        if (!isAdded) {
            // throw exception
        }
    }

    public boolean containsStep(Stop stop) {
        return stops.contains(stop);
    }
}
