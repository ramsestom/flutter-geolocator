package com.baseflow.geolocator.data;

import android.location.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressMapper {

  public static List<Map<String, Object>> toHashMapList(List<Address> addresses) {
    List<Map<String, Object>> hashMaps = new ArrayList<>(addresses.size());

    for (Address address : addresses) {
      Map<String, Object> hashMap = AddressMapper.toHashMap(address);
      hashMaps.add(hashMap);
    }

    return hashMaps;
  }

  private static Map<String, Object> toHashMap(Address address) {
    Map<String, Object> placemark = new HashMap<>();

    placemark.put("name", address.getFeatureName());
    // final String feature_name = address.getFeatureName();
    // if (feature_name != null && !feature_name.equals(address.getSubThoroughfare()) &&
    //         !feature_name.equals(address.getThoroughfare()) &&
    //         !feature_name.equals(address.getLocality())) {
    //   placemark.put("feature", feature_name);
    // }
    // else {
    //   placemark.put("feature", null);
    // }
    placemark.put("subThoroughfare", address.getSubThoroughfare());//=streetNumber
    placemark.put("thoroughfare", address.getThoroughfare()); //=streetName
    placemark.put("postalCode", address.getPostalCode());
    placemark.put("locality", address.getLocality());
    placemark.put("subLocality", address.getSubLocality());
    placemark.put("administrativeArea", address.getAdminArea());
    placemark.put("subAdministrativeArea", address.getSubAdminArea());
    placemark.put("isoCountryCode", address.getCountryCode());
    placemark.put("country", address.getCountryName());
    placemark.put("locale", address.getLocale().toString());
    placemark.put("phone", address.getPhone());
    placemark.put("url", address.getUrl());
    placemark.put("premises", address.getPremises());
   
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
        if (i > 0) {
            sb.append("\n");
        }
        sb.append(address.getAddressLine(i));
    }
    placemark.put("formattedAddress", sb.toString());
    

    if (address.hasLatitude() && address.hasLongitude()) {
      Map<String, Double> positionMap = new HashMap<>();

      positionMap.put("latitude", address.getLatitude());
      positionMap.put("longitude", address.getLongitude());

      placemark.put("position", positionMap);
    }

    return placemark;
  }
}
