package admin.phuc141.com.appweathers.model.request;

public class WeatheSeachLocationForm {
    private String q;
    private String units;
    private String cnt;
    private String appid;

    public WeatheSeachLocationForm() {
    }


    public WeatheSeachLocationForm(String q, String units, String cnt, String appid) {
        this.q = q;
        this.units = units;
        this.cnt = cnt;
        this.appid = appid;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
