LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := awinic_native
LOCAL_SRC_FILES := i2c_awinic.cpp
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)