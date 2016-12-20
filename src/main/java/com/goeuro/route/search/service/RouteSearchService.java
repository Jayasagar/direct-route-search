package com.goeuro.route.search.service;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.model.SearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class RouteSearchService {

    public SearchResponse isRouteExist(int dep_sid, int arr_sid ) {

        boolean directRouteExist = ApplicationData.allRoutes
                .values()
                .stream()
                .map(route -> route.getStops())
                .allMatch(stops -> stops.contains(dep_sid) && stops.contains(arr_sid));

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setArr_sid(arr_sid);
        searchResponse.setDep_sid(dep_sid);
        searchResponse.setDirect_bus_route(directRouteExist);
        return searchResponse;
    }
}
