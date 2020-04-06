package admin.phuc141.com.appweathers.respository;

import admin.phuc141.com.appweathers.api.ApiService;
import admin.phuc141.com.appweathers.api.NetworkModule;
import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.business.TempSevenDay;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import io.reactivex.Observable;

public class WeatherRespository {
    private ApiService apiService;
    private static WeatherRespository weatherRespository = null;

    private WeatherRespository(){
        apiService = NetworkModule.getInstance();

    }
    public static WeatherRespository getInstance(){
        if (weatherRespository ==null){
            weatherRespository = new WeatherRespository();
        }
        return weatherRespository;
    }
    public Observable<TempCurrent> getTempAtLocation(WeatheSeachLocationForm weatheSeachLocationForm){
        return apiService.getTempAtLocation(
                weatheSeachLocationForm.getQ(),
                weatheSeachLocationForm.getUnits(),
                weatheSeachLocationForm.getCnt(),
                weatheSeachLocationForm.getAppid());
    }
    public Observable<TempSevenDay> getTempSevenDay(String NameCountry, String matrics, int NumberofDay, String Appid ){
        return apiService.getTempSevenDay(NameCountry,matrics,NumberofDay,Appid);
    }

}
