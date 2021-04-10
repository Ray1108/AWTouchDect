package com.awinic.atouchdect.i2c;

public interface RWRegInterface {

    byte[] readReg(byte reg, int length);

    boolean writeReg(byte reg, byte[] content);

    byte[] sendCalibrateCmd();

    byte[] sendCalibrateEndCmd();
}
