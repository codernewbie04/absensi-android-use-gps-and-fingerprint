package id.sch.sman1garut.app.sman1garut.SPreferenced;


public class SPref {
    private static String ID_USER="ID_USER";
    private static String USERNAME="USERNAME";
    private static String NIK="NIK";
    private static String NAME="NAME";
    private static String EMAIL="EMAIL";
    private static String LAT="LAT";
    private static String LNG="LNG";
    private static String PHOTO="PHOTO";
    private static String LAST_UPDATE="LAST_UPDATE";
    private static String ALAMAT="ALAMAT";
    private static String TOKEN="ACC_TOKEN";
    private static String PASSWORD="PASSWORD";
    private static String CARTS="CARTS";

    public static String getCARTS() {
        return CARTS;
    }

    public static void setCARTS(String CARTS) {
        SPref.CARTS = CARTS;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        SPref.PASSWORD = PASSWORD;
    }

    public static String getUser_id() {
        return ID_USER;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getNama() {
        return NAME;
    }
    public static String getNIK() {
        return NIK;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getLat() {
        return LAT;
    }

    public static String getLng() {
        return LNG;
    }

    public static String getPHOTO() {
        return PHOTO;
    }

    public static String getLastUpdate() {
        return LAST_UPDATE;
    }

    public static String getToken() {
        return TOKEN;
    }
}

