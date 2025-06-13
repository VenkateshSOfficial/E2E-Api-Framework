package testData;

import pojo.addPlacePojo.Location;
import pojo.addPlacePojo.SerializationPojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    static SerializationPojo p;
    static Location location;
    public static SerializationPojo addPlacePayload(String name,String language,String address){
        p=new SerializationPojo();
        p.setAccuracy("50");
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("12345677844");
        p.setWebsite("https://www.happy.com");
        p.setName(name);
        List<String> typesValues=new ArrayList<>();
        typesValues.add("shoe park");
        typesValues.add("back yard");
        p.setTypes(typesValues);
        location=new Location();
        location.setLat("34.676789");
        location.setLng("45.2308723");
        p.setLocation(location);
        return p;
    }
}
