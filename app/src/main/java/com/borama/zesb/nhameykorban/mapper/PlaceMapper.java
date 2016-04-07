package com.borama.zesb.nhameykorban.mapper;

import com.borama.zesb.nhameykorban.realmmodels.PlaceRealm;
import com.borama.zesb.nhameykorban.models.Place;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by phearom on 4/6/16.
 */
public class PlaceMapper {
    public static PlaceRealm toPlaceRealm(Place place) {
        PlaceRealm placeRealm = new PlaceRealm();
        placeRealm.setId(place.getId());
        placeRealm.setName(place.getName());
        placeRealm.setPhoto(place.getPhoto());
        return placeRealm;
    }

    public static RealmList<PlaceRealm> toPlaceRealmList(List<Place> places) {
        RealmList<PlaceRealm> list = new RealmList<>();
        for (Place p : places) {
            list.add(toPlaceRealm(p));
        }
        return list;
    }

    public static Place toPlace(PlaceRealm placeRealm) {
        Place place = new Place();
        place.setId(placeRealm.getId());
        place.setName(placeRealm.getName());
        place.setPhoto(placeRealm.getPhoto());
        return place;
    }

    public static List<Place> toPlaceList(RealmList<PlaceRealm> placeRealms) {
        List<Place> list = new ArrayList<>();
        for (PlaceRealm p : placeRealms) {
            list.add(toPlace(p));
        }
        return list;
    }
}
