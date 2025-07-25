package resources;

public enum APIResources {
    // Enum constants must come first
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json");

    private String resource;

    // Constructor
    APIResources(String resource) {
        this.resource = resource;
    }

    // Getter method
    public String getResource() {
        return resource;
    }
}
