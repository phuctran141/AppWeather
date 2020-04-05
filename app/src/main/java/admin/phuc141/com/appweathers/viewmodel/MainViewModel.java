package admin.phuc141.com.appweathers.viewmodel;

import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.business.TempSevenDay;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import admin.phuc141.com.appweathers.respository.WeatherRespository;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements LifecycleObserver {
    private WeatherRespository mweatherRespository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<TempCurrent> mWeatherLocation;
    private MutableLiveData<TempSevenDay>mWeatherSevenDay;
    private MutableLiveData<String> mError;
    private MutableLiveData<Boolean> mLoading;

    public MainViewModel() {
        this.mweatherRespository = WeatherRespository.getInstance();
        this.compositeDisposable = new CompositeDisposable();
        this.mWeatherLocation = new MutableLiveData<>();
        this.mWeatherSevenDay=new MutableLiveData<>();
        this.mError = new MutableLiveData<>();
        this.mLoading = new MutableLiveData<>();
    }
    public LiveData<Boolean> isLoading(){
        return mLoading;
    }
    public LiveData<String> isError(){
        return mError;
    }
    public LiveData<TempCurrent> getWeatherLocationSuccess(){
        return mWeatherLocation;
    }
    public void CallWeatherLocation(WeatheSeachLocationForm locationForm){
        mLoading.setValue(true);
        mweatherRespository.getTempAtLocation(locationForm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TempCurrent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }
                    @Override
                    public void onNext(TempCurrent tempCurrent) {
                        mWeatherLocation.setValue(tempCurrent);
                    }
                    @Override
                    public void onError(Throwable e) {
                        mError.setValue(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        mLoading.setValue(false);
                    }
                });
    }
    public LiveData<TempSevenDay> getTempSevenDaySuccess(){
        return null;
    }
    public void CallTempSevenDay(WeatheSeachLocationForm SevenDay){
        mLoading.setValue(true);
        mweatherRespository.getTempSevenDay(SevenDay).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TempSevenDay>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);

            }

            @Override
            public void onNext(TempSevenDay tempSevenDay) {
                mWeatherSevenDay.setValue(tempSevenDay);
            }

            @Override
            public void onError(Throwable e) {
                mError.setValue(e.getMessage());

            }

            @Override
            public void onComplete() {
                mLoading.setValue(false);

            }
        });
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void clear(){
        compositeDisposable.clear();
    }

}
