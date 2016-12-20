package com.goeuro.route.search.service;

import com.goeuro.route.search.boot.DataLoaderService;
import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.model.SearchResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class RouteSearchServiceTest {

    @BeforeClass
    public static void setup() {
        ApplicationData.clearData();

        String data[] = new String[] {
                "3",
                "0 0 1 2 3 4",
                "1 3 1 6 5",
                "2 0 6 4"
        };
        DataLoaderService dataLoaderService = new DataLoaderService();
        dataLoaderService.loadData(Arrays.asList(data).stream());
    }


    @Test
    public void given_input_should_return_route_exist_true() {
        // Arrange
        RouteSearchService routeSearchService = new RouteSearchService();

        // Act
        SearchResponse routeExist = routeSearchService.isRouteExist(0, 4);

        // Assert
        Assert.assertTrue(routeExist.isDirect_bus_route());
    }

    @Test
    public void given_input_should_return_route_exist_false() {
        // Arrange
        RouteSearchService routeSearchService = new RouteSearchService();

        // Act
        SearchResponse routeExist = routeSearchService.isRouteExist(0, 5);

        // Assert
        Assert.assertFalse(routeExist.isDirect_bus_route());
    }
}
