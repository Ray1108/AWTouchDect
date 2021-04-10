package com.awinic.atouchdect.comm;

public class comm {
    public static final int I2C_MODE = 0;
    public static final int BT_MODE = 0;
    private static int mCommMode = I2C_MODE;
    public static void setCommMode(int mode) {
        mCommMode = mode;
    }
}
