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
    protected abstract ViewGroup getViewGroup();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
        mapview();
        observer();
        listener();
        //relativeLayout = findViewById(R.id.relaytiveLayout);
//        layoutRotateLoading = LayoutInflater.from(getContext()).inflate(getLayoutRotateLoading(),getViewGroup(),false);
//        frameLayout = layoutRotateLoading.findViewById(R.id.framelayout);
//        rotateLoading =layoutRotateLoading.findViewById(R.id.rotateloading);
//        hideRotateLoading();
    }

    public void showRotateLoading (){
//        layoutRotateLoading.setVisibility(View.VISIBLE);
//        relativeLayout.addView(layoutRotateLoading);
//        getViewGroup().addView(layoutRotateLoading);
//        frameLayout.setVisibility(View.VISIBLE);
//        rotateLoading.start();
    }
    public void hideRotateLoading(){
//        layoutRotateLoading.setVisibility(View.GONE);
//        frameLayout.setVisibility(View.GONE);
//        rotateLoading.stop();
    }
}
