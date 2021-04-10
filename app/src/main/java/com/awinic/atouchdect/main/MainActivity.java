package com.awinic.atouchdect.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.awinic.atouchdect.R;
import com.awinic.atouchdect.cali.activity_cali;
import com.awinic.atouchdect.comm.activity_bt;
import com.awinic.atouchdect.comm.comm;
import com.awinic.atouchdect.dbg.activity_dbg;
import com.awinic.atouchdect.hw_check.activity_hw_check;
import com.awinic.atouchdect.rtd.activity_rtd;
import com.awinic.atouchdect.utils.AutoSuitUtil;
import com.awinic.atouchdect.utils.DensityUtil;

public class MainActivity extends BasicActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton ib_rtd = findViewById(R.id.ib_rtd);
        ImageButton ib_dbg = findViewById(R.id.ib_dbg);
        ImageButton ib_cali = findViewById(R.id.ib_cali);
        ImageButton ib_hardware_check = findViewById(R.id.ib_hardware_check);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        AutoSuitUtil.AutoModifySize(this, 1224f/246f, 1, 40, dm, ib_rtd, ib_cali, ib_dbg, ib_hardware_check);
        CheckBox cb_comm_mode = findViewById(R.id.ck_comm_mode);
        AutoSuitUtil.AutoModifySize(this, 1224f/105f, 1, 40, dm, cb_comm_mode);

        ib_rtd.setOnClickListener(this);
        ib_dbg.setOnClickListener(this);
        ib_cali.setOnClickListener(this);
        ib_hardware_check.setOnClickListener(this);

        cb_comm_mode.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_rtd:
                startActivity(new Intent(MainActivity.this, activity_rtd.class));
                break;
            case R.id.ib_dbg:
                startActivity(new Intent(MainActivity.this, activity_dbg.class));
                break;
            case R.id.ib_cali:
                startActivity(new Intent(MainActivity.this, activity_cali.class));
                break;
            case R.id.ib_hardware_check:
                startActivity(new Intent(MainActivity.this, activity_hw_check.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.ck_comm_mode:
                if(isChecked) {
                    Toast.makeText(this, "通讯方式切换为I2C", Toast.LENGTH_SHORT).show();
                    comm.setCommMode(comm.I2C_MODE);
                } else {
                    Toast.makeText(this, "通讯方式切换为BT", Toast.LENGTH_SHORT).show();
                    comm.setCommMode(comm.BT_MODE);
                    startActivity(new Intent(MainActivity.this, activity_bt.class));
                }
                break;
            default:
                break;
        }
    }
}