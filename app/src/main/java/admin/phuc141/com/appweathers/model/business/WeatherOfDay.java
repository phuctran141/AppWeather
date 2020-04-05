package admin.phuc141.com.appweathers.model.business;

public class WeatherOfDay {

    private String Date;
    private String Status;
    private String ImageStage;
    private String TempMax;
    private String TempMin;
    public WeatherOfDay(String date, String status, String imageStage, String tempMax, String tempMin) {
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

    public String getImageStage() {
        return ImageStage;
    }

    public void setImageStage(String imageStage) {
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
