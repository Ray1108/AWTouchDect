package com.awinic.atouchdect.cali;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.awinic.atouchdect.R;
import com.awinic.atouchdect.main.BasicActivity;
import com.awinic.atouchdect.utils.AutoSuitUtil;

public class activity_cali extends BasicActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cali);

        ImageButton ib_single = findViewById(R.id.ib_single);
        ImageButton ib_muti = findViewById(R.id.ib_muti);
        ImageButton ib_quli_ck = findViewById(R.id.ib_quli_test);
        ImageButton ib_self_ck = findViewById(R.id.ib_self_check);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        AutoSuitUtil.AutoModifySize(this, 560f/703f, 0.5f, 60, dm, ib_single, ib_muti, ib_quli_ck, ib_self_ck);
    }
}
