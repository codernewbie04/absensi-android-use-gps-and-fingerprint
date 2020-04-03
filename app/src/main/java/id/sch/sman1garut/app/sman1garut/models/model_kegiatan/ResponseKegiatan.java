package id.sch.sman1garut.app.sman1garut.models.model_kegiatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.sch.sman1garut.app.sman1garut.models.model_pengumuman.DataPengumuman;

public class ResponseKegiatan{
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private PecahDataKegiatan data = null;

    public Boolean getStatus() {
        return status;
    }

    public PecahDataKegiatan getData() {
        return data;
    }
}
