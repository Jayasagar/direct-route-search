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

import static com.goeuro.route.search.RequestMappingURI.BASE_URL;
import static com.goeuro.route.search.RequestMappingURI.DIRECT_MAPPING;

@RestController
@RequestMapping(BASE_URL)
@Description("Search route endpoint")
public class SearchController {

    @Autowired
    private RouteSearchService routeSearchService;

    @RequestMapping(value = DIRECT_MAPPING, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SearchResponse isDirectRouteExist(@RequestParam int dep_sid, @RequestParam int arr_sid) {
        return routeSearchService.isRouteExist(dep_sid, arr_sid);
    }
}
