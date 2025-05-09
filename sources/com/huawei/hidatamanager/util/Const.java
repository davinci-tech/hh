package com.huawei.hidatamanager.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class Const {
    public static final List RAW_DATATYPE_LIST = Collections.unmodifiableList(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 1000L, Long.valueOf(RawDataType.HEALTH_EVENT_RECORD), Long.valueOf(RawDataType.HEALTH_SLEEP_RECORD)));

    public interface HealthMustParams {
        public static final String[] PAIRED_DEVICE = {"DeviceName", "DeviceID", "DeviceType", "DeviceActiveTime"};
        public static final String[] EVENT_RECORD = {"SportType", "SportStartTime", "SportDuration"};
        public static final String[] SLEEP_RECORD = {"SleepStartTime", "SleepEndTime"};
    }

    /* loaded from: classes8.dex */
    public interface RawDataType {
        public static final long COMMUTER_TIME = 5;
        public static final long COMMUTER_WAY = 6;
        public static final long HEALTH_EVENT_RECORD = 1001;
        public static final long HEALTH_PAIRED_DEVICE = 1000;
        public static final long HEALTH_SLEEP_RECORD = 1002;
        public static final long HICARD_CLICK = 1;
        public static final long HOME_LOCATION = 2;
        public static final long WORKING_CALENDER = 4;
        public static final long WORKSHOP_LOCATION = 3;
    }
}
