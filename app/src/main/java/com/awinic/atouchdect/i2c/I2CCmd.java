package com.awinic.atouchdect.i2c;

public class I2CCmd {
    // channel number
    public static final byte PHY_CH_NUM = 4;   // 4个物理通道（其中2个是温补通道）
    public static final byte CH_NUM = 2;       // 4个物理通道经过处理合成为上、下2个通道
    // User Command
    public static final byte CMD_REG = (byte) 0x02;
    // 温度 + 温度ADC + 温补系数
    public static final byte CMD_TEMP_DATA = (byte)0x11;
    // Offset
    public static final byte CMD_OFFSET = (byte) 0x13;
    public static final byte CMD_CALI_SET = (byte)0x14;
    public static final byte CMD_MODULES_STATUS = (byte)0x15;
    public static final byte CMD_ADC2VOL_COEF = (byte)0x16;
    public static final byte CMD_DAC2VOL_COEF = (byte)0x17;

    public static final byte CMD_CHIP_ID = (byte)0xb5;
    public static final byte CMD_FW_VER = (byte)0x85;
    public static final byte CMD_MCU_VER = (byte)0x87;
    public static final byte CMD_SW_RESET = (byte)0xff;
}
