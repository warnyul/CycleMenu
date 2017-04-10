package com.cleveroad.cyclemenusample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cleveroad.cyclemenuwidget.CycleMenuWidget;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        CycleMenuWidget menuWidget = (CycleMenuWidget) findViewById(R.id.cycleMenu);
        menuWidget.setCorner(CycleMenuWidget.CORNER.RIGHT_BOTTOM);
        menuWidget.setRippleColor(Color.argb(100, 200, 100, 100));
        menuWidget.setAdapter(new CircleAdapter(this));
    }
}
