package id.sch.sman1garut.app.sman1garut.models.models_users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImage {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {
        return msg;
    }

    public Boolean getStatus() {
        return status;
    }
}
