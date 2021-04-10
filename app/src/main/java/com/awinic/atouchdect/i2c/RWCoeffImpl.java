package com.awinic.atouchdect.i2c;

public class RWCoeffImpl implements RWCoeffInterface {

    @Override
    public float[] readCoeffs() {
        return new float[0];
    }

    @Override
    public boolean writeCoeffs(float[] value) {
        return false;
    }

    @Override
    public short[] readModel(int index) {
        return new short[0];
    }

    @Override
    public int writeModel(int index, short[] value) {
        return 0;
    }

    @Override
    public float[] readCoeffsOfFixedPoints() {
        return new float[0];
    }

    @Override
    public boolean writeCoeffsOfFixedPoints(float[] coefs) {
        return false;
    }

    @Override
    public float[] readBigModel(byte id) {
        return new float[0];
    }

    @Override
    public boolean writeBigModel(byte id, float[] model) {
        return false;
    }
}
