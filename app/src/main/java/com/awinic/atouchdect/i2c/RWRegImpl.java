package com.awinic.atouchdect.i2c;

import android.util.Log;

import com.awinic.atouchdect.aw_jni.I2CJni;

import java.util.Arrays;

public class RWRegImpl implements RWRegInterface {

    @Override
    public byte[] readReg(byte reg, int length) {
        wakeUp();
        byte[] wBuf = new byte[1];
        byte[] rBuf = new byte[length];
        wBuf[0] = reg;
        int ret = I2CJni.IICWriteRead(wBuf, 1, rBuf, length);
        Log.d("RWRegImpl", Arrays.toString(rBuf));
        if (ret < 0) {
            return null;
        } else {
            return rBuf;
        }
    }
    @Override
    public boolean writeReg(byte reg, byte[] content) {
        wakeUp();
        int len = content.length + 1;
        byte[] wBuf = new byte[len];
        byte[] rBuf = new byte[0];
        wBuf[0] = reg;
        for (int i = 1; i < len; i++) {
            wBuf[i] = content[i - 1];
        }
        int ret = I2CJni.IICWriteRead(wBuf, len, rBuf, 0);
        return ret >= 0;
    }

    @Override
    public byte[] sendCalibrateCmd() {
        return new byte[0];
    }

    @Override
    public byte[] sendCalibrateEndCmd() {
        return new byte[0];
    }

    private boolean wakeUp() {
        int wLen = 2;
        int rLen = 0;
        byte[] wBuf = new byte[wLen];
        byte[] rBuf = new byte[rLen];
        wBuf[0] = 0x1F;
        wBuf[1] = 0x0A;
        int ret = -1;
        int cnt = 50;
        while (ret < 0 && cnt > 0) {
            ret = I2CJni.IICWriteRead(wBuf, wLen, rBuf, rLen);
            cnt--;
        }
        return ret >= 0;
    }

}
