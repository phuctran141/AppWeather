package admin.phuc141.com.appweathers.view;

import admin.phuc141.com.appweathers.R;
import admin.phuc141.com.appweathers.Util.AppConstance;
import admin.phuc141.com.appweathers.base.BaseActivity;
import admin.phuc141.com.appweathers.model.business.TempCurrent;
import admin.phuc141.com.appweathers.model.request.WeatheSeachLocationForm;
import admin.phuc141.com.appweathers.viewmodel.MainViewModel;

import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    MainViewModel mMainViewModel;
    EditText medtSeach;
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
//        return R.id.includedLayout;
        return R.layout.layout_progress;
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
                    Toast.makeText(MainActivity.this, "đang load dữ liệu", Toast.LENGTH_SHORT).show();
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
                Log.d("BBB", tempCurrent.toString());
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
                    mMainViewModel.CallWeatherLocation(new WeatheSeachLocationForm(diadiem.replace(" ",""),AppConstance.UNITS,AppConstance.APPID));
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected Context getContext() {
        return MainActivity.this;
    }

    @Override
    protected ViewGroup getViewGroup() {
        return findViewById(R.id.relaytiveLayout);
    }

//    @Override
//    protected ViewGroup getViewGroup() {
//        return relativeLayout;
//    }
//
//    @Override
//    protected Context getContext() {
//        return MainActivity.this;
//    }

}
