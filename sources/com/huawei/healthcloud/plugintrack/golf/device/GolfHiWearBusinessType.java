package com.huawei.healthcloud.plugintrack.golf.device;

/* loaded from: classes4.dex */
public enum GolfHiWearBusinessType {
    GOLF_GPS_INFO_SEND(8),
    GOLF_COURSE_VERSION_RESPON(9),
    GOLF_COURSE_VERSION_CHECK(10),
    GOLF_COURSE_FILE(11),
    GOLF_COURSE_LIST_FILE(12),
    GOLF_COURSE_MAP_REQUEST(13),
    GOLF_COURSE_PUSH_SHAKE(14),
    GOLF_COURSE_DELETE(15),
    GOLF_LOCAL_COURSE_LIST(16),
    GOLF_DEVICE_PULL_SUCESS(17),
    GOLF_GET_DEVICE_TYPE(18),
    GOLF_CANCEL_COURSE_MAP(19),
    GOLF_COURSE_MAP_KEY(20),
    GOLF_COURSE_CLUB_INFO(23),
    GOLF_COURSE_CLUB_INFO_DEVICE(24);

    int mTypeValue;

    GolfHiWearBusinessType(int i) {
        this.mTypeValue = i;
    }

    public int getTypeValue() {
        return this.mTypeValue;
    }
}
