package com.goeuro.route.search.service;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.model.SearchResponse;
import com.goeuro.route.search.model.Stop;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Predicate;

@Service
public class RouteSearchService {

    public SearchResponse isRouteExist(final int departureStopId, final int arrivalStopId) {

        boolean directRouteExist = ApplicationData.allRoutes
                .values()
                .stream()
                .map(route -> route.getStops())
                // Check whether given stops exist in route with sequence order: order matters!!
                .filter(new OrderMatchPredicate(departureStopId, arrivalStopId))
                .findFirst()
                .isPresent();

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setArr_sid(arrivalStopId);
        searchResponse.setDep_sid(departureStopId);
        searchResponse.setDirect_bus_route(directRouteExist);
        return searchResponse;
    }

    class OrderMatchPredicate implements Predicate<Set<Stop>> {
        private int departureStopId;
        private int arrivalStopId;

        OrderMatchPredicate(final int departureStopId, final int arrivalStopId) {
            this.departureStopId = departureStopId;
            this.arrivalStopId = arrivalStopId;
        }

        @Override
        public boolean test(Set<Stop> stops) {
            boolean isExist = stops.contains(Stop.of(departureStopId)) && stops.contains(Stop.of(arrivalStopId));

            // If both stops not exist then return false
            if (!isExist) {
                return false;
            }

            boolean isFirstStopFound = false;
            boolean isSecondStopFound = false;

            for (Stop stop : stops) {
                // first stop not matched ? try then!
                if (!isFirstStopFound) {
                    if(stop == Stop.of(departureStopId)) {
                        isFirstStopFound = true;
                    }
                } else { // If first already found check for the arrival stop
                    if (stop == Stop.of(arrivalStopId)) {
                        isSecondStopFound = true;
                        break;
                    }
                }
            }
            return isSecondStopFound;
        }
    }
}
