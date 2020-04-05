package admin.phuc141.com.appweathers.api;

import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.business.TempSevenDay;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //    weather?q="+tencountry+"&mode=json&units=metric&appid=53fbf527d52d4d773e828243b90c1f8e
    @GET("weather")
    Observable<TempCurrent> getTempAtLocation(@Query("q") String tencountry,
                                              @Query("units") String metric,
                                              @Query("cnt") String number,
                                              @Query("appid") String id);
//    forecast/daily?q="+data+"&mode=json&units=metric&cnt=7&appid=53fbf527d52d4d773e828243b90c1f8e
    @GET("forecast/daily")
    Observable<TempSevenDay> getTempSevenDay(@Query("q") String tencountry,
                                             @Query("units") String metric,
                                             @Query("cnt") String number,
                                             @Query("appid") String id);

}
