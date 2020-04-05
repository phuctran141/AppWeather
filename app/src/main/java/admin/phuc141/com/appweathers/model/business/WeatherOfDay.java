package admin.phuc141.com.appweathers.model.business;

public class Weather {

    private String Date;
    private String Status;
    private int ImageStage;
    private String TempMax;
    private String TempMin;
    public Weather(String date, String status, int imageStage, String tempMax, String tempMin) {
        Date = date;
        Status = status;
        ImageStage = imageStage;
        TempMax = tempMax;
        TempMin = tempMin;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getImageStage() {
        return ImageStage;
    }

    public void setImageStage(int imageStage) {
        ImageStage = imageStage;
    }

    public String getTempMax() {
        return TempMax;
    }

    public void setTempMax(String tempMax) {
        TempMax = tempMax;
    }

    public String getTempMin() {
        return TempMin;
    }

    public void setTempMin(String tempMin) {
        TempMin = tempMin;
    }


}
