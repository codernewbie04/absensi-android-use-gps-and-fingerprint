package id.sch.sman1garut.app.sman1garut.models.models_absensi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAbsensi {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
