package com.qh.xzm.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private WaterView waterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img_icon);
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img.getLayoutParams();
        waterView = findViewById(R.id.water_view);
        waterView.setAnimationListener(new WaterView.AnimationListener() {
            @Override
            public void animation(float y) {
                layoutParams.setMargins(0, 0, 0, (int) y - 50);
                img.setLayoutParams(layoutParams);
            }
        });
    }


}
