package admin.phuc141.com.appweathers.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin.phuc141.com.appweathers.R;
import admin.phuc141.com.appweathers.Util.AppConstance;
import admin.phuc141.com.appweathers.base.BaseActivity;
import admin.phuc141.com.appweathers.model.business.TempSevenDay;
import admin.phuc141.com.appweathers.model.business.WeatherAdapter;
import admin.phuc141.com.appweathers.model.business.WeatherOfDay;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import admin.phuc141.com.appweathers.model.response.SevenData.Temp;
import admin.phuc141.com.appweathers.model.response.SevenData.Weather;
import admin.phuc141.com.appweathers.viewmodel.MainViewModel;

public class SevenDayActivity extends BaseActivity {
    RecyclerView mRrecyclerView;
    Toolbar toolbar;
    MainViewModel mMainViewModel;
    List<WeatherOfDay> mWeatherArray;
    WeatherAdapter mWeatherAdapter;
    TextView mNameCity;
    String data;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_seven_day;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int getLayoutRotateLoading() {
        return R.id.includeActivity;

    }

    @Override
    protected void init() {
        toolbar=findViewById(R.id.toolbar);
//        getSupportActionBar().hide();
        setActionBar(toolbar);
        getActionBar().setTitle("Thời tiết thành phố");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setBackgroundColor(Color.BLUE);
        mMainViewModel = new MainViewModel();

    }

    @Override
    protected void mapview() {
        mRrecyclerView= findViewById(R.id.recyclerview);
        mNameCity = findViewById(R.id.tvNameCity2);
        mWeatherArray = new ArrayList<WeatherOfDay>();
//        mWeatherArray.add(new WeatherOfDay("Thứ 3","ôn dinh","04n","30","40"));
        mWeatherAdapter = new WeatherAdapter(SevenDayActivity.this, mWeatherArray);
        mRrecyclerView.setAdapter(mWeatherAdapter);
        mRrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLifecycle().addObserver(mMainViewModel);
    }

    @Override
    protected void observer() {
//        mMainViewModel.getTempSevenDaySuccess().observe(this, new Observer<TempSevenDay>() {
//            @Override
//            public void onChanged(TempSevenDay tempSevenDay) {
//                String name =tempSevenDay.getCity().getName();
//                Log.d("BBB", "thanh pho" + name);
//                mNameCity.setText("Thành Phố "+ name);
//                for(int i=0; i<tempSevenDay.getList().size();i++){
//                    String Day = tempSevenDay.getList().get(i).getTemp().toString();
//                    Long l = Long.valueOf(Day);
//                    Date date= new Date(l*1000L);
//                    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("EEEE yyyy-MM-dd");
//                    String Date = simpleDateFormat.format(date);
//
//                    Weather weather = (Weather) tempSevenDay.getList().get(i).getWeather().get(0);
//                    String Status = weather.getDescription();
//                    String icon = weather.getIcon();
//
//                    Temp temp = tempSevenDay.getList().get(i).getTemp();
//
//                    String tempmax=temp.getMax().toString();
//                    Double a = Double.valueOf(tempmax);
//                    String TempMax = String.valueOf( a.intValue());
//
//                    String tempmin = temp.getMin().toString();
//                    Double b = Double.valueOf(tempmin);
//                    String TempMin = String.valueOf(b.intValue());
//
//                    mWeatherArray.add(new WeatherOfDay(Date,Status,icon,TempMax,TempMin));
//                }
//                mWeatherAdapter.notifyDataSetChanged();
//            }
//        });
    }

    @Override
    protected void listener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        if (data.isEmpty()){
//            Log.d("BBB", "toang");
//        }else {
//            mMainViewModel.CallTempSevenDay(new WeatheSeachLocationForm("saigon", AppConstance.UNITS,AppConstance.CNT,AppConstance.APPID));
//        }

    }

    @Override
    protected Context getContext() {
        return null;
    }

    @Override
    protected void getIntentData() {
        Intent intent =getIntent();
        data = intent.getStringExtra("name");

    }


}
