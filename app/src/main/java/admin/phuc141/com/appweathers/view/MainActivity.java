package admin.phuc141.com.appweathers.view;

import admin.phuc141.com.appweathers.R;
import admin.phuc141.com.appweathers.Util.AppConstance;
import admin.phuc141.com.appweathers.base.BaseActivity;
import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.business.WeatherAdapter;
import admin.phuc141.com.appweathers.model.business.WeatherOfDay;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import admin.phuc141.com.appweathers.model.response.CurrentData.Main;
import admin.phuc141.com.appweathers.model.response.CurrentData.Weather;
import admin.phuc141.com.appweathers.viewmodel.MainViewModel;

import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {
    MainViewModel mMainViewModel;
    EditText medtSeach;

    Integer TT=0;
    ScrollView mScrollView;
    LinearLayout mLayoutSadFace;
    TextView mtvNameCity, mtvCountry, mtvTemp, mtvState, mtvHumidity, mtvCloud, mtvWind, mtvDayTime;
    Button mbtnSeach, mbtnChangeAct;
    ImageView mimgStage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medtSeach.setText("saigon");
        showviewstart();

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
        mScrollView=findViewById(R.id.scrollViewLayout);
        mLayoutSadFace=findViewById(R.id.layoutSadface);
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
                Log.d("BBB","Lỗi "+ s);
                hideRotateLoading();
              //  hideSoftKeyBoard(MainActivity.this);
//                if(TT==1){
//                    hideSoftKeyBoard(MainActivity.this);
//                    TT=3;
//                }
                Log.d("BBB", "trạng thái = "+TT);
                mLayoutSadFace.setVisibility(View.VISIBLE);
                mScrollView.setVisibility(View.GONE);
            }
        });
        mMainViewModel.getWeatherLocationSuccess().observe(this, new Observer<TempCurrent>() {
            @Override
            public void onChanged(TempCurrent tempCurrent) {
          //     hideSoftKeyBoard(MainActivity.this);
                mLayoutSadFace.setVisibility(View.GONE);
                mScrollView.setVisibility(View.VISIBLE);

                String Status = tempCurrent.getWeather().get(0).getMain();
                mtvState.setText(Status);

                String Country = tempCurrent.getSys().getCountry().replace("VN","Việt Nam");
                mtvCountry.setText("Quốc gia: "+Country);

                String NameCity = tempCurrent.getName()
                        .replace("City","")
                        .replace("Ho Chi Minh","Hồ Chí Minh")
                        .replace("Turan","Đà Nẵng")
                        .replace("Hanoi","Hà Nội");
                mtvNameCity.setText("Thành phố: "+NameCity);

                String day = tempCurrent.getDt().toString();
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
                medtSeach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            hideSoftKeyBoard(MainActivity.this);
                        }
                    }
                });
             //   hideSoftKeyBoard(MainActivity.this);
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
                String city= medtSeach.getText().toString().replace(" ", "");
                if(city.isEmpty()){
                    city="saigon";
                }
                Intent intent = new Intent(MainActivity.this,SevenDayActivity.class);
                intent.putExtra("name",city);
                startActivity(intent);
            }
        });

    }

    @Override
    protected Context getContext() {
        return MainActivity.this;
    }

    @Override
    protected void getIntentData() {

    }
    public void showviewstart(){
        mMainViewModel.CallWeatherLocation(new WeatheSeachLocationForm("saigon",AppConstance.UNITS,"",AppConstance.APPID));
    }
    public void hideSoftKeyBoard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
        }
    }


}

