package com.goeuro.route.search.controller;

import com.goeuro.route.search.RequestMappingURI;
import com.goeuro.route.search.model.SearchResponse;
import com.goeuro.route.search.service.RouteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappingURI.BASE_URL)
@Description("A controller for handling requests for Patient")
public class SearchController {

    @Autowired
    private RouteSearchService routeSearchService;

    @RequestMapping(value = "direct", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SearchResponse getNextAppointment(@RequestParam int dep_sid, @RequestParam int arr_sid) {
        return routeSearchService.isRouteExist(dep_sid, arr_sid);
    }
}
