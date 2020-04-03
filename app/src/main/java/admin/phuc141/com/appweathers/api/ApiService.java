package admin.phuc141.com.appweathers.api;

import admin.phuc141.com.appweathers.model.business.TempCurrent;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //    weather?q="+tencountry+"&mode=json&units=metric&appid=53fbf527d52d4d773e828243b90c1f8e
    @GET("weather")
    Observable<TempCurrent> getTempAtLocation(@Query("q") String tencountry,
                                              @Query("units") String metric,
                                              @Query("appid") String id);
}
