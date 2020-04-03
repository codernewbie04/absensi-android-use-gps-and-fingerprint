package id.sch.sman1garut.app.sman1garut.models.model_pengumuman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsePengumuman {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private List<DataPengumuman> data = null;

    public Boolean getStatus() {
        return status;
    }

    public List<DataPengumuman> getData() {
        return data;
    }
}
