package com.goeuro.route.search.boot;

import com.goeuro.route.search.data.ApplicationData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class DataLoaderServiceTest {

    @BeforeClass
    public static void setup() {
        ApplicationData.clearData();
    }

    @Test
    public void boot_data_should_fill_application_data() {
        // Arrange
        String data[] = new String[] {
                "3",
                "0 0 1 2 3 4",
                "1 3 1 6 5",
                "2 0 6 4"
        };

        DataLoaderService dataLoaderService = new DataLoaderService();

        // Act
        dataLoaderService.loadData(Arrays.asList(data).stream());

        // Assert
        Assert.assertEquals(ApplicationData.allRoutes.size(), 3);
        Assert.assertEquals(ApplicationData.allStops.size(), 7);
    }

    @Test
    public void should_throw_exception_on() {

    }

    @Test
    public void should_trim_first_line() {

    }
}
