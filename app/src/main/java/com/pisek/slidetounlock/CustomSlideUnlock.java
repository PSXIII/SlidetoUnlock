package com.pisek.slidetounlock;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.pisek.slidetounlock.databinding.CustomSlideUnlockBinding;

public class CustomSlideUnlock extends FrameLayout {

    private Drawable indicator;
    private Integer percent = 0;
    private SlideButtonListener listener;
    private CustomSlideUnlockBinding mBinding;

    public CustomSlideUnlock(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public CustomSlideUnlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public CustomSlideUnlock(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = CustomSlideUnlockBinding.inflate(inflater, this, true);
    }

    private void initInstance() {
        mBinding.seekBar.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (indicator.getBounds().contains((int) event.getX(), (int) event.getY()))
                            mBinding.seekBar.onTouchEvent(event);
                        else
                            return false;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mBinding.seekBar.getProgress() > percent)
                            handleSlide();

                        mBinding.seekBar.setProgress(0);
                        break;
                    default:
                        mBinding.seekBar.onTouchEvent(event);
                        break;
                }
                return true;
            }
        });
    }

    private void handleSlide() {
        listener.handleSlide();
    }

    public void setIndicator(Drawable indicator) {
        if (indicator == null)
            this.indicator = getResources().getDrawable(R.mipmap.ic_launcher);
        else
            this.indicator = indicator;
        mBinding.seekBar.setThumb(indicator);
        mBinding.seekBar.setThumbOffset(38);
    }

    public void setPercentAction(Integer percent) {
        if (percent == null)
            this.percent = 0;
        else
            this.percent = percent;
    }

    public void setTitle(String title) {
        if (title == null)
            mBinding.tvTitle.setText("Slide to unlock");
        else
            mBinding.tvTitle.setText(title);
    }

    public void setSlideButtonListener(SlideButtonListener listener) {
        this.listener = listener;
    }

    interface SlideButtonListener {
        void handleSlide();
    }
}