package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(of = {"id"}) @ToString
public final class Stop {
    private final int id;

    private Stop(int id) {
        this.id = id;
    }

    public static Stop of(String id) {
        return of(Integer.parseInt(id.trim()));
    }

    public static Stop of(int id) {
        if (!ApplicationData.allStops.containsKey(id)) {
            Stop stop = new Stop(id);
            ApplicationData.allStops.put(id, stop);
            return stop;
        }
        return ApplicationData.allStops.get(id);

    }
}
