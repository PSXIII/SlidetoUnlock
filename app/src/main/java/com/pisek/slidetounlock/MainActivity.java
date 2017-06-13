package com.pisek.slidetounlock;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pisek.slidetounlock.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.customSlideUnlock.setIndicator(getResources().getDrawable(R.drawable.ic_lock_open));
        mBinding.customSlideUnlock.setPercentAction(95);
        mBinding.customSlideUnlock.setSlideButtonListener(new CustomSlideUnlock.SlideButtonListener() {
            @Override
            public void handleSlide() {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
