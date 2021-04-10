package com.awinic.atouchdect.i2c;

interface ReadPollingDataInterface {

    void setDebugMode(byte mode);

    int readDataReady();

    void clearDataCnt();

    short[] getData(int len);

    boolean wakeUp();
}
