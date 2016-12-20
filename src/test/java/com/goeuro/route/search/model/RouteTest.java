package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import com.goeuro.route.search.exceptions.DataLoadStateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RouteTest {
    @BeforeClass
    public static void setup() {
        ApplicationData.clearData();
    }

    @Before
    public void unitSetup() {
        ApplicationData.clearData();
    }

    @Test
    public void create_a_route_should_add_to_all_routes() {
        Route.of(1);

        Assert.assertEquals(1, ApplicationData.allRoutes.size());
    }

    @Test(expected = DataLoadStateException.class)
    public void adding_same_route_should_not_add() {
        Route.of(1); Route.of(1);
    }

    @Test
    public void added_stop_should_contain_route() {
        Route route = Route.of(1);
        route.addStop(Stop.of(3));

        Assert.assertTrue(route.containsStep(Stop.of(3)));
    }
}
