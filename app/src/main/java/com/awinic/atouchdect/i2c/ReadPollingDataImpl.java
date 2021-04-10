package com.awinic.atouchdect.i2c;

public class ReadPollingDataImpl implements ReadPollingDataInterface {


    @Override
    public void setDebugMode(byte mode) {

    }

    @Override
    public int readDataReady() {
        return 0;
    }

    @Override
    public void clearDataCnt() {

    }

    @Override
    public short[] getData(int len) {
        return new short[0];
    }

    @Override
    public boolean wakeUp() {
        return false;
    }
}
