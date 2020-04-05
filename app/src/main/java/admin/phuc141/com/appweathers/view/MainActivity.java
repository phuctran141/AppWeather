package admin.phuc141.com.appweathers.view;

import admin.phuc141.com.appweathers.R;
import admin.phuc141.com.appweathers.Util.AppConstance;
import admin.phuc141.com.appweathers.base.BaseActivity;
import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import admin.phuc141.com.appweathers.model.response.CurrentData.Weather;
import admin.phuc141.com.appweathers.viewmodel.MainViewModel;

import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {
    MainViewModel mMainViewModel;
    EditText medtSeach;
    List<Weather> weathers;
    TextView mtvNameCity, mtvCountry, mtvTemp, mtvState, mtvHumidity, mtvCloud, mtvWind, mtvDayTime;
    Button mbtnSeach, mbtnChangeAct;
    ImageView mimgStage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getLayoutRotateLoading() {
        return R.id.includeActivity;
    }

    @Override
    protected void init() {
        mMainViewModel = new MainViewModel();
    }

    @Override
    protected void mapview() {
        medtSeach =findViewById(R.id.edtSeach);
        mtvNameCity = findViewById(R.id.tvNameCity);
        mtvCountry = findViewById(R.id.tvCountry);
        mtvTemp = findViewById(R.id.tvTemp);
        mtvState = findViewById(R.id.tvState);
        mtvHumidity = findViewById(R.id.tvHumidity);
        mtvCloud = findViewById(R.id.tvCloud);
        mtvWind = findViewById(R.id.tvWind);
        mtvDayTime = findViewById(R.id.tvDayTime);
        mbtnSeach =findViewById(R.id.btnseach);
        mbtnChangeAct =findViewById(R.id.btnChangeAct);
        mimgStage =findViewById(R.id.imgState);
        getLifecycle().addObserver(mMainViewModel);
    }

    @Override
    protected void observer() {
        mMainViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showRotateLoading();
                }
                else hideRotateLoading();
            }

        });
        mMainViewModel.isError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        mMainViewModel.getWeatherLocationSuccess().observe(this, new Observer<TempCurrent>() {
            @Override
            public void onChanged(TempCurrent tempCurrent) {
                String day = tempCurrent.getDt().toString();
                String Status = tempCurrent.getWeather().get(0).getMain();
                mtvState.setText(Status);
                String Country = tempCurrent.getSys().getCountry();
                mtvCountry.setText(Country);
                String NameCity = tempCurrent.getName();
                mtvNameCity.setText("Thành Phố: "+NameCity);
                Log.d("BBB", tempCurrent.getDt().toString());
                Long l = Long.valueOf(day);
                Date date = new Date(l*1000L);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm:ss");
                String Date = simpleDateFormat.format(date);
                mtvDayTime.setText(Date);
                String icon = tempCurrent.getWeather().get(0).getIcon();
                Picasso.get().load("https://openweathermap.org/img/w/"+icon+".png").resize(150,150).centerCrop().error(R.drawable.rain).into(mimgStage);
                String nhietdo = tempCurrent.getMain().getTemp().toString();
                Log.d("BBB",nhietdo);
                Double a = Double.valueOf(nhietdo);
                String Nhietdo = String.valueOf(a.intValue());
                mtvTemp.setText(Nhietdo+ (char) 0x00B0+"C");
                String Humidity = tempCurrent.getMain().getHumidity().toString();
                mtvHumidity.setText(Humidity+"%");
                String Cloud = tempCurrent.getClouds().getAll().toString();
                mtvCloud.setText(Cloud+"%");
                String speed = tempCurrent.getWind().getSpeed().toString();
                mtvWind.setText(speed+"m/s");
            }
        });
    }
    @Override
    protected void listener() {
        mbtnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diadiem = medtSeach.getText().toString();
                if(!diadiem.isEmpty()){
                    mMainViewModel.CallWeatherLocation(new WeatheSeachLocationForm(diadiem.replace(" ",""),AppConstance.UNITS,"",AppConstance.APPID));
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mbtnChangeAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location= medtSeach.getText().toString().replace(" ", "");
                Intent intent = new Intent();
            }
        });

    }

    @Override
    protected Context getContext() {
        return MainActivity.this;
    }


}
