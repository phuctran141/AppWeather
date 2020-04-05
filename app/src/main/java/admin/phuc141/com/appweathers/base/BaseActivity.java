package admin.phuc141.com.appweathers.base;

import admin.phuc141.com.appweathers.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.victor.loading.rotate.RotateLoading;

public abstract class BaseActivity extends AppCompatActivity {
    private View layoutRotateLoading;
    private RotateLoading rotateLoading;
    private FrameLayout frameLayout;
    protected abstract int getLayoutId();
    protected abstract int getLayoutRotateLoading();
    protected abstract void init();
    protected abstract void mapview();
    protected abstract void observer();
    protected abstract void listener();
    protected abstract Context getContext();
    protected abstract void getIntentData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getIntentData();
        init();
        mapview();
        observer();
        listener();
        if(getLayoutRotateLoading()>0){
            layoutRotateLoading = findViewById(getLayoutRotateLoading());
            rotateLoading= layoutRotateLoading.findViewById(R.id.rotateloading);
            hideRotateLoading();
        }

//        layoutRotateLoading = LayoutInflater.from(getContext()).inflate(getLayoutRotateLoading(),getViewGroup(),false);
//        frameLayout = layoutRotateLoading.findViewById(R.id.framelayout);
//        rotateLoading =layoutRotateLoading.findViewById(R.id.rotateloading);

    }

    public void showRotateLoading (){
        if(layoutRotateLoading!=null){
            layoutRotateLoading.setVisibility(View.VISIBLE);
            rotateLoading.start();
        }

    }
    public void hideRotateLoading(){
        if(layoutRotateLoading!=null){
            layoutRotateLoading.setVisibility(View.GONE);
            rotateLoading.stop();
        }

    }
}
