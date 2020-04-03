package id.sch.sman1garut.app.sman1garut.models.models_users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseLogin {
  @SerializedName("status")
  @Expose
  private Boolean status;

  @SerializedName("msg")
  @Expose
  private String msg;

  @SerializedName("data")
  @Expose
  private DataUser data = null;

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

  public DataUser getData() {
    return data;
  }

  public void setData(DataUser data) {
    this.data = data;
  }
}

