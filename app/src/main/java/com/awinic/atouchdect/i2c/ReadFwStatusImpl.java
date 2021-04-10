package com.awinic.atouchdect.i2c;

import android.util.Log;
import com.awinic.atouchdect.aw_jni.I2CJni;
import java.util.Arrays;

public class ReadFwStatusImpl implements ReadFwStatusInterface {

    private final static String TAG = "ReadFwStatusImpl";


    @Override
    public byte[] readFwVersion() {
        int wlen = 1;
        int rlen = 14;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_FW_VER;
        int ret = I2CJni.IICWriteRead(wbuf,wlen, rbuf,rlen);
        Log.d(TAG, "ret = "+ret);

        return ret < 0 ? null : rbuf;
    }

    @Override
    public int clearFlag() {
        return 0;
    }

    @Override
    public short readModuleId() {
        return 0;
    }

    @Override
    public byte readModuleStatus() {
        byte result = 0;
        int wlen = 1;
        int rlen = 1;

        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_MODULES_STATUS;

        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        if (ret < 0)
            result = -1;
        else
            result= rbuf[0];
        Log.d(TAG, "result"+result);

        return result;
    }

    @Override
    public short[] readOffset() {
        short[] offset = new short[I2CCmd.PHY_CH_NUM * 2];
        int wlen = 1;
        int rlen = I2CCmd.PHY_CH_NUM * 2;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_OFFSET;
        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        if (ret > 0) {
            for (int i = 0; i < I2CCmd.PHY_CH_NUM; i++) {
                offset[i] = (short) ((rbuf[i * 2 + 1] << 8) & 0xFF00 | (rbuf[i * 2] & 0xFF));
            }
        } else {
            for (int i = 0; i < I2CCmd.PHY_CH_NUM; i++) {
                offset[i] = -1;
            }
        }
        return offset;
    }

    @Override
    public short[] readCalibrateCoef() {
        return new short[0];
    }

    @Override
    public short readTempCoef() {
        short data = 0;
        int wLen = 1;
        int rLen = 2 ;
        byte[] wbuf = new byte[wLen];
        byte[] rbuf = new byte[rLen];
        wbuf[0] = I2CCmd.CMD_TEMP_DATA;
        int ret = I2CJni.IICWriteRead(wbuf, wLen, rbuf, rLen);
        if (ret > 0) {
            data = (short) ((rbuf[1] << 8) & 0xFF00 | (rbuf[0]) & 0xFF);
        } else {
            Log.d(TAG, "temp ret < 0" + "ret = "+ret);
        }
        Log.d(TAG, "temp" + "chip temp:"+data);

        return data;
    }

    @Override
    public double[] sensorGap() {
        return new double[0];
    }

    @Override
    public short readDac2UvCoef() {
        short coef;
        int wlen = 1;
        int rlen = 2;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_DAC2VOL_COEF;
        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        if (ret > 0)
            coef = (short) ((rbuf[1] << 8) & 0xFF00 | (rbuf[0] & 0xFF));
        else
            coef = -1;
        Log.d("readDac2UvCoef", " coef = " + coef);
        return coef;
    }

    @Override
    public int readAdc2UvCoef() {
        int conversionCoef;
        int wlen = 1;
        int rlen = 3;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_ADC2VOL_COEF;
        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        if (ret < 0)
            conversionCoef = -1;
        else
            conversionCoef = (rbuf[2] << 16) & 0xFF0000 | (rbuf[1] << 8) & 0xFF00 | (rbuf[0] & 0xFF);
        Log.d("readConversionCoef", " coef = " + conversionCoef);
        return conversionCoef;
    }

    @Override
    public byte[] afeNoiseSwitch(boolean flag) {
        int wlen = 3;
        int rlen = 0;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_REG;
        if (flag) {
            wbuf[1] = (byte) 0x40;
            wbuf[2] = (byte) 0xC0;  // check-sum
        } else {
            wbuf[1] = (byte) 0x41;
            wbuf[2] = (byte) 0xBF;  // check-sum
        }
        I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);

        sleep(50);

        wlen = 1;
        rlen = 2;
        wbuf = new byte[wlen];
        rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_REG;
        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        Log.d("afeNoiseSwitch", "" + Arrays.toString(rbuf));
        return rbuf;
    }

    @Override
    public short[] readTempBattery() {
        short[] temp;
        int wlen = 1;
        int rlen = 2;
        byte[] wbuf = new byte[wlen];
        byte[] rbuf = new byte[rlen];
        wbuf[0] = I2CCmd.CMD_TEMP_DATA;
        int ret = I2CJni.IICWriteRead(wbuf, wlen, rbuf, rlen);
        if (ret >= 0) {
            temp = new short[rlen];
            temp[0] = (short) ((rbuf[1] << 8) & 0xFF00 | (rbuf[0]) & 0xFF);
        } else
            temp = null;
        Log.d("readTempBattery", " tempBat = " + Arrays.toString(temp));
        return temp;
    }

    @Override
    public int[] readNoiseAfe() {
        return new int[0];
    }

    @Override
    public int[] readNoise() {
        return new int[0];
    }

    private void sleep(long time) {
        try {
            time = time > 0 ? time : 0;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
