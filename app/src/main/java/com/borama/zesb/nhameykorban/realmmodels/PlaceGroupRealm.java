package com.borama.zesb.nhameykorban.realmmodels;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by phearom on 4/6/16.
 */
@RealmClass
public class PlaceGroupRealm extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<PlaceRealm> places;

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

    public RealmList<PlaceRealm> getPlaces() {
        return places;
    }

    public void setPlaces(RealmList<PlaceRealm> places) {
        this.places = places;
    }
}
