package ua.com.hureinsys.util;

/**
 * Created by Oleksii on 22.12.2015.
 */
public class CreatedObjResponse {
    private String id;

    public CreatedObjResponse() {
    }

    public CreatedObjResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
