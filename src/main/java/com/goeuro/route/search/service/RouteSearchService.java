package com.goeuro.route.search.service;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.model.SearchResponse;
import com.goeuro.route.search.model.Stop;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Service
public class RouteSearchService {

    public SearchResponse isRouteExist(int dep_sid, int arr_sid ) {


        boolean directRouteExist = ApplicationData.allRoutes
                .values()
                .stream()
                .map(route -> route.getStops())
                .filter(stops -> stops.contains(Stop.of(dep_sid)) && stops.contains(Stop.of(arr_sid)))
                .findAny()
                .isPresent();

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setArr_sid(arr_sid);
        searchResponse.setDep_sid(dep_sid);
        searchResponse.setDirect_bus_route(directRouteExist);
        return searchResponse;
    }
}
