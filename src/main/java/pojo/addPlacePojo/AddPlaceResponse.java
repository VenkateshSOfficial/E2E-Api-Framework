package pojo.addPlacePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPlaceResponse {
    private String status;
    private String place_id;
    private String scope;
    private String reference;
    private String id;
}
