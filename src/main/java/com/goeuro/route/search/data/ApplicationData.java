package com.goeuro.route.search.data;

import com.goeuro.route.search.model.Route;
import com.goeuro.route.search.model.Stop;
import com.google.common.collect.Maps;

import java.util.Map;

public final class ApplicationData {
    private ApplicationData() {}

    public static Map<Integer, Route> allRoutes = Maps.newHashMap();

    // FIXME: Not using at the moment, can be get rid of
    // However, idea is it can good candidate to check for given input/stops exist in system ?
    public static Map<Integer, Stop> allStops = Maps.newHashMap();

    public static void clearData() {
        allRoutes.clear();
        allStops.clear();
    }
}
