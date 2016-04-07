package com.borama.zesb.nhameykorban.models;

import java.util.List;

/**
 * Created by phearom on 4/6/16.
 */
public class PlaceGroup {
    private String id;
    private String name;
    private List<Place> places;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
