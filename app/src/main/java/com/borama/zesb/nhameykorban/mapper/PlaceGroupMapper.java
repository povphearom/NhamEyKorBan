package com.borama.zesb.nhameykorban.mapper;

import com.borama.zesb.nhameykorban.models.PlaceGroup;
import com.borama.zesb.nhameykorban.realmmodels.PlaceGroupRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by phearom on 4/6/16.
 */
public class PlaceGroupMapper {
    public static PlaceGroupRealm toPlaceGroupRealm(PlaceGroup placeGroup) {
        PlaceGroupRealm placeGroupRealm = new PlaceGroupRealm();
        placeGroupRealm.setId(placeGroup.getId());
        placeGroupRealm.setName(placeGroup.getName());
        placeGroupRealm.setPlaces(PlaceMapper.toPlaceRealmList(placeGroup.getPlaces()));
        return placeGroupRealm;
    }

    public static RealmList<PlaceGroupRealm> toPlaceGroupRealmList(List<PlaceGroup> placeGroups) {
        RealmList<PlaceGroupRealm> list = new RealmList<>();
        for (PlaceGroup p : placeGroups) {
            list.add(toPlaceGroupRealm(p));
        }
        return list;
    }

    public static PlaceGroup toPlaceGroup(PlaceGroupRealm placeGroupRealm) {
        PlaceGroup placeGroup = new PlaceGroup();
        placeGroup.setId(placeGroupRealm.getId());
        placeGroup.setName(placeGroupRealm.getName());
        placeGroup.setPlaces(PlaceMapper.toPlaceList(placeGroupRealm.getPlaces()));
        return placeGroup;
    }

    public static List<PlaceGroup> toPlaceGroupList(RealmList<PlaceGroupRealm> placeGroupRealms) {
        List<PlaceGroup> list = new ArrayList<>();
        for (PlaceGroupRealm p : placeGroupRealms) {
            list.add(toPlaceGroup(p));
        }
        return list;
    }
}
