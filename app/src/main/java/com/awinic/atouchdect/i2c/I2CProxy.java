package com.awinic.atouchdect.i2c;

import android.util.Log;

import com.awinic.atouchdect.aw_jni.I2CJni;


// i2c代理类，屏蔽所有与i2c通信的具体实现，仅向外提供接口
public class I2CProxy {

    private static final String TAG = "I2CProxy";

    // 读写任意寄存器的接口
    private RWRegInterface rwReg = new RWRegImpl();

    // 读取Sensor数据的接口
    private ReadPollingDataInterface pollingData;

    // 读写校准系数的接口
    private RWCoeffInterface rwCoeff;

    // 读取硬件状态的接口
    private ReadFwStatusInterface readFwStatus;

    // 单例模式
    private I2CProxy() {
    }

    private boolean opened = false;

    // I2C 实例
    private static com.awinic.atouchdect.i2c.I2CProxy instance = new com.awinic.atouchdect.i2c.I2CProxy();

    // 获取 I2C 实例
    public static com.awinic.atouchdect.i2c.I2CProxy getInstance() {
        return instance;
    }

    // 打开设备节点
    public void open() {
//        if (!opened) {
//            opened = I2CJni.OpenDeviceNode() > 0;
//            if (opened) {
//                pollingData = new ReadPollingDataImpl();
//                rwCoeff = new RWCoeffImpl();
//                readFwStatus = new ReadFwStatusImpl();
//            }
//        }
//        Log.d(TAG, "NDT JNI Device Node " + (opened ? "opened." : "closed."));
    }

    // 关闭设备节点
    public void close() {
//        if (opened) {
//            opened = I2CJni.CloseDeviceNode() > 0;
//        }
//        Log.d(TAG, "NDT JNI Device Node " + (opened ? "opened." : "closed."));
    }

    // 检查I2C节点状态
    public boolean isOpened() {
        return opened;
    }

    // 设置轮询时的 DebugMode
    public void setPollingDataDebugMode(byte mode) {
        pollingData.setDebugMode(mode);
    }

    // 轮询时读 DataReady
    public int readPollingDataReady() {
        return pollingData.readDataReady();
    }

    // 清除轮询时的 DataReady
    public void clearPollingDataReady() {
        pollingData.clearDataCnt();
    }

    // 获取轮询类数据
    public short[] getPollingData(int len) {
        return pollingData.getData(len);
    }

    /**
     * 写校准系数
     */
    public boolean writeCoeffs(float[] value) {
        return rwCoeff.writeCoeffs(value);
    }

    /**
     * 读校准系数
     */
    public float[] readCoeffs() {
        return rwCoeff.readCoeffs();
    }

    /**
     * 读任意寄存器
     */
    public byte[] readReg(byte reg, int length) {
        return rwReg.readReg(reg, length);
    }

    /**
     * 写任意寄存器
     */
    public boolean writeReg(byte reg, byte[] content) {
        return rwReg.writeReg(reg, content);
    }

    /**
     * 发送内短噪声检测命令
     *
     * @param b 使能/不能 内短噪声检测
     * @return 返回操作结果   [0, 0]:成功， [写入的值]:失败
     */
    public byte[] afeNoiseSwitch(boolean b) {
        return readFwStatus.afeNoiseSwitch(b);
    }

    /**
     * 读取固件版本号
     */
    public byte[] readFwVersion() {
        return readFwStatus.readFwVersion();
    }

    /**
     * 清除forceFlag
     */
    public int clearFlag() {
        return readFwStatus.clearFlag();
    }

    /**
     * sensor间距
     */
    public double[] sensorGap() {
        return readFwStatus.sensorGap();
    }

    /**
     * 写某个通道的模板
     */
    public int writeModel(int index, short[] model) {
        return rwCoeff.writeModel(index, model);
    }

    /**
     * 读某个通道的模板
     */
    public short[] readModel(int index) {
        return rwCoeff.readModel(index);
    }

    /**
     * 读取模组通断 + 模组通断详情
     */
    public byte readModuleDetail() {
        return readFwStatus.readModuleStatus();
    }

    /**
     * ADC TO UVOLT COEF
     */
    public int readAdc2UvCoef() {
        return readFwStatus.readAdc2UvCoef();
    }

    /**
     * DAC TO UVOLT COEF
     */
    public short readDac2UvCoef() {
        return readFwStatus.readDac2UvCoef();
    }

    /**
     * 读取offset
     */
    public short[] readOffset() {
        return readFwStatus.readOffset();
    }

    /**
     * 读取温度 + 温度ADC + 温补系数
     */
    public short readTempCoef() {
        return readFwStatus.readTempCoef();
    }

    /**
     * 读取校准系数
     */
    public short[] readCalibrateCoef() {
        return readFwStatus.readCalibrateCoef();
    }

    public boolean wakeUp() {
        return pollingData.wakeUp();
    }

    /**
     * 读取 Module ID
     */
    public short readModuleId() {
        return readFwStatus.readModuleId();
    }

    /**
     * 校准测试前发送命令
     */
    public byte[] sendCalibrateCmd() {
        return rwReg.sendCalibrateCmd();
    }

    /**
     * 校准测试结束后发送结束命令
     */
    public byte[] sendCalibrateEndCmd() {
        return rwReg.sendCalibrateEndCmd();
    }

    /**
     * 读取校准电池温度
     *
     * @return 电池温度, arr[0]：温度
     * if arr[0] + arr[1] == 0  then 写入值
     * else 默认值
     */
    public short[] readTempBattery() {
        return readFwStatus.readTempBattery();
    }

    /**
     * 读取noiseAfe，在固件内部完成内短噪声检测
     */
    public int[] readNoiseAfe() {
        return readFwStatus.readNoiseAfe();
    }

    /**
     * 读取noise，在固件内部完成噪声检测
     */
    public int[] readNoise() {
        return readFwStatus.readNoise();
    }

    /**
     * （屏幕压感）读取某些固定点的校准系数
     */
    public float[] readCoeffsOfFixedPoints() {
        return rwCoeff.readCoeffsOfFixedPoints();
    }

    /**
     * （屏幕压感）写入某些固定点的校准系数
     */
    public boolean writeCoeffsOfFixedPoints(float[] coefs) {
        return rwCoeff.writeCoeffsOfFixedPoints(coefs);
    }

    /**
     * （屏幕压感）读取大模板
     */
    public float[] readBigModel(byte id) {
        return rwCoeff.readBigModel(id);
    }

    /**
     * （屏幕压感）写入大模板
     */
    public boolean writeBigModel(byte id, float[] model) {
        return rwCoeff.writeBigModel(id, model);
    }
}
