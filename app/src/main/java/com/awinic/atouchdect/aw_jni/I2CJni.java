package com.awinic.atouchdect.aw_jni;

public class I2CJni {
    public static native int IICWriteRead(byte[] WriteBuffer, int WriteLength,byte[] ReadBuffer,int ReadLength);
    static {
        System.loadLibrary("awinic_native");
    }
}
