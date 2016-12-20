package com.goeuro.route.search.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchResponse {
    private int dep_sid;
    private int arr_sid;
    private boolean direct_bus_route = false;
}
