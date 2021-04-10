package com.awinic.atouchdect.hw_check;

import android.os.Bundle;
import android.widget.Button;

import com.awinic.atouchdect.R;
import com.awinic.atouchdect.i2c.I2CProxy;
import com.awinic.atouchdect.main.BasicActivity;

public class activity_hw_check extends BasicActivity{
    private Button btn_start_test;
    public I2CProxy i2c = I2CProxy.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw_check);

        btn_start_test = (Button) findViewById(R.id.btn_start_test);
        btn_start_test.setOnClickListener(v -> {
            btnStat(false);
            hwCheckStart();
        });
    }

    private void btnStat(boolean stat)
    {
        btn_start_test.setEnabled(stat);
        btn_start_test.setBackgroundResource(stat ? (R.drawable.bg_btn) : (R.drawable.bg_btn_dis));
        btn_start_test.setText(stat ? "开始检测" : "检测中..");
    }

    private void hwCheckStart()
    {
        new Thread(() -> {

            //btnStat(true);
        }).start();
    }

}
