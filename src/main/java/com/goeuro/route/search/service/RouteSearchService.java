package com.goeuro.route.search.service;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.model.SearchResponse;
import com.goeuro.route.search.model.Stop;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Service
public class RouteSearchService {

    public SearchResponse isRouteExist(final int dep_sid, final int arr_sid ) {

        boolean directRouteExist = ApplicationData.allRoutes
                .values()
                .stream()
                .map(route -> route.getStops())
                .filter(stops -> {
                    boolean isExist = stops.contains(Stop.of(dep_sid)) && stops.contains(Stop.of(arr_sid));

                    // If both stops not exist then return false
                    if (!isExist) {
                        return false;
                    }

                    boolean isFirstStopFound = false;
                    boolean isSecondStopFound = false;

                    for (Stop stop : stops) {
                        // is already first stop matched ?
                        if (!isFirstStopFound) {
                            if(stop == Stop.of(dep_sid)) {
                                isFirstStopFound = true;
                            }
                        } else { // If first already found check the arrival stop
                            if (stop == Stop.of(arr_sid)) {
                                isSecondStopFound = true;
                            }
                        }
                    }

                    return isSecondStopFound;
                })
                .findFirst()
                .isPresent();

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setArr_sid(arr_sid);
        searchResponse.setDep_sid(dep_sid);
        searchResponse.setDirect_bus_route(directRouteExist);
        return searchResponse;
    }
}
