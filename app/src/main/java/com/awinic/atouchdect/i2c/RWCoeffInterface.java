package com.awinic.atouchdect.i2c;

public interface RWCoeffInterface {

    /**
     * 读校准系数
     * tip: 读取失败则返回 0 值
     */
    float[] readCoeffs();

    /**
     * 写校准系数
     */
    boolean writeCoeffs(float[] value);

    /**
     * 读模板
     */
    short[] readModel(int index);

    /**
     * 写模板
     */
    int writeModel(int index, short[] value);

    /**
     * （屏幕压感）读取某些固定点的校准系数
     */
    float[] readCoeffsOfFixedPoints();

    /**
     * （屏幕压感）写入某些固定点的校准系数
     */
    boolean writeCoeffsOfFixedPoints(float[] coefs);

    /**
     * （屏幕压感）读取大模板
     */
    float[] readBigModel(byte id);

    /**
     * （屏幕压感）写入大模板
     */
    boolean writeBigModel(byte id, float[] model);
}
