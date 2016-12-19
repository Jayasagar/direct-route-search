package com.goeuro.route.search.model;

import com.goeuro.route.search.data.ApplicationData;
import org.junit.Assert;
import org.junit.Test;

public class StopTest {

    @Test
    public void create_a_stop_should_add_to_all_stops() {
        Stop.of(1);

        Assert.assertEquals(1, ApplicationData.allStops.size());
    }

    @Test
    public void adding_same_stop_should_not_add() {
        Stop.of(1); Stop.of(1);

        Assert.assertEquals(1, ApplicationData.allStops.size());
    }
}
