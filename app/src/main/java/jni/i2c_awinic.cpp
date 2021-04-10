//
// Created by ray on 21-4-4.
//
#include <errno.h>
#include <fcntl.h>
#include <malloc.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include <jni.h>
#include <android/log.h>

#define LOG_TAG "awinic"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

#define DEVICE_NAME    "/dev/ndt"


static int fd = 0;

int aw8680x_read(unsigned char *rbuf, jint rlen)
{
    int ret;
    fd = open(DEVICE_NAME,O_RDWR);
    if (fd < 0) {
        LOGE("jni open fail for read, fd = %d\n",fd);
        return fd;
    }

    ret = read(fd, rbuf, rlen);
    ret = 1;
    if (ret < 0) {
        LOGE("jni read fail ret = %d\n",ret);
        close(fd);
        return ret;
    }
    close(fd);
    return ret;
}

int aw8680x_write(unsigned char *wbuf, jint wlen)
{
    int ret;
    fd = open(DEVICE_NAME,O_RDWR);
    if (fd < 0) {
        LOGE("jni open device fail, fd = %d\n",fd);
        return -errno;
    }
    ret = write(fd, wbuf, wlen);
    if (ret < 0) {
        LOGE("jni write fail, ret = %d\n", ret);
        ret = -errno;
    } else if (ret != wlen) {
        LOGE("jni write fail, ret = %d,wlen = %d\n",ret,wlen);
        ret = -EAGAIN;
    }

    close(fd);
    return ret;
}

JNIEXPORT jint JNICALL Java_com_awinic_atouchdect_aw_1jni_i2c_1jni_IICWriteRead(JNIEnv *env, jclass __unused c, jbyteArray w_buf, jint wlen, jbyteArray r_buf, jint rlen)
{
    int ret = 0;

    unsigned char *wbuf = (unsigned char *)env->GetByteArrayElements(w_buf,JNI_FALSE);
    unsigned char *rbuf = (unsigned char *)env->GetByteArrayElements(r_buf,JNI_FALSE);
    LOGI("wlen = %d", (int)wlen);
    if(wlen > 1) {
        ret = aw8680x_write((unsigned char*)wbuf, wlen);
    }

    if (rlen > 0) {
        ret = aw8680x_read((unsigned char *)rbuf, rlen);
        env->SetByteArrayRegion(r_buf, 0, rlen, (const jbyte *)rbuf);
    }
    return ret;
}