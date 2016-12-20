package com.goeuro.route.search.data;

import com.goeuro.route.search.model.Route;
import com.goeuro.route.search.model.Stop;
import com.google.common.collect.Maps;

import java.util.Map;

public class ApplicationData {
    public static Map<Integer, Route> allRoutes = Maps.newHashMap();
    public static Map<Integer, Stop> allStops = Maps.newHashMap();
}