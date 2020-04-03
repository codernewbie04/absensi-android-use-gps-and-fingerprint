package id.sch.sman1garut.app.sman1garut.models.models_users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUnique {
  @SerializedName("status")
  @Expose
  private Boolean status;

  @SerializedName("msg")
  @Expose
  private String msg;

  public Boolean getStatus() {
    return status;
  }

  public String getMessage() {
    return msg;
  }
}
