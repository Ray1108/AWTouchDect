package com.awinic.atouchdect.i2c;

public interface ReadFwStatusInterface {

    /**
     * 读取固件版本号
     * @return NDT Main Version + NDT Sub. Version + Customer Main Version + Customer Sub. Version  (Ex. V1.1.1.1)
     */
    byte[] readFwVersion();

    /**
     * 清除forceFlag
     */
    int clearFlag();

    /**
     * 读取 Module ID
     */
    short readModuleId();

    /**
     * 读取模组通断 + 模组通断详细信息
     * @return
     */
    byte readModuleStatus();

    /**
     * 读取offset
     */
    short[] readOffset();

    /**
     * 读取校准系数
     * @return 校准系数 / 1000.0
     */
    short[] readCalibrateCoef();

    /**
     * 读取温度 + 温度ADC + 温补系数
     * @return 温度 / 10.0  温补系数 / 1000.0
     */
    short readTempCoef();

    /**
     * 读取sensor间距
     * @return
     */
    double[] sensorGap();

    /**
     * DAC TO UVOLT COEF
     * @return
     */
    short readDac2UvCoef();

    /**
     * ADC TO UVOLT COEF
     * @return return value / 10000.0f
     */
    int readAdc2UvCoef();

    /**
     * 打开/关闭 内短噪声检测
     * @param b
     * @return
     */
    byte[] afeNoiseSwitch(boolean b);

    /**
     * 读取校准电池温度
     * @return 电池温度, arr[0]：温度
     * if arr[0] + arr[1] == 0  then 写入值
     * else 默认值
     */
    short[] readTempBattery();

    /**
     * 读取noiseAfe
     */
    int[] readNoiseAfe();

    /**
     * 读取noise
     */
    int[] readNoise();

}
