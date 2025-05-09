package com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy;

import android.content.ContentValues;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import defpackage.cvu;
import defpackage.kiz;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface SyncStrategy {
    public static final long FETCH_WAIT_MAX_TIME = 5000;
    public static final String KEY_CLOUD_LAST_SYNC_TIME = "KEY_CLOUD_LAST_SYNC_TIME";
    public static final String KEY_INSERT_LAST_SYNC_TIME = "KEY_INSERT_LAST_SYNC_TIME";
    public static final int PARSE_RADIX_10 = 10;
    public static final String SP_KEY_RECONNECT_LAST_SYNC_TIME = "SP_KEY_RECONNECT_LAST_SYNC_TIME";
    public static final String SRC_PKG_NAME = "hw.sport.config";
    public static final long SYNC_INTERVAL_30_MINUTE = 1800000;
    public static final long SYNC_INTERVAL_FOREGROUND = 1000;
    public static final long SYNC_INTERVAL_ONE_MINUTE = 60000;
    public static final long SYNC_INTERVAL_SCREEN_OFF = 180000;
    public static final String WEAR_PKG_NAME = "in.huawei.motion";

    List<HiHealthData> fetchData(List<HiAggregateOption> list);

    boolean isNeedSync(DeviceInfo deviceInfo, long j);

    List<cvu> preProcess(List<HiHealthData> list);

    void syncDataToDevice(DeviceInfo deviceInfo, List<cvu> list);

    default List<HiHealthData> transAggregateToRead(HiHealthData hiHealthData) {
        int a2;
        long j;
        ArrayList arrayList = new ArrayList();
        long startTime = hiHealthData.getStartTime();
        ContentValues valueHolder = hiHealthData.getValueHolder();
        Iterator<Map.Entry<String, Object>> it = valueHolder.valueSet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            Double asDouble = valueHolder.getAsDouble(key);
            if (asDouble != null) {
                if (key.startsWith("WriteBackKey_HALFHOUR_")) {
                    a2 = CommonUtil.a(key.replace("WriteBackKey_HALFHOUR_", ""), 10);
                    j = 1800000;
                } else if (key.startsWith("WriteBackKey_HOUR_")) {
                    a2 = CommonUtil.a(key.replace("WriteBackKey_HOUR_", ""), 10);
                    j = 3600000;
                } else if (key.startsWith("WriteBackKey_DAY_")) {
                    a2 = CommonUtil.a(key.replace("WriteBackKey_DAY_", ""), 10);
                    j = 86400000;
                }
                if (a2 != -1) {
                    HiHealthData hiHealthData2 = new HiHealthData();
                    hiHealthData2.setTimeInterval(kiz.b(startTime, j), kiz.c(startTime, j));
                    hiHealthData2.setValue(asDouble.doubleValue());
                    if (a2 == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value() && j == 3600000) {
                        hiHealthData2.setType(kiz.d(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value(), 1800000L));
                        HiHealthData copyData = hiHealthData2.copyData();
                        long startTime2 = hiHealthData2.getStartTime() + 1800000;
                        hiHealthData2.setEndTime(startTime2);
                        arrayList.add(hiHealthData2);
                        copyData.setStartTime(startTime2);
                        arrayList.add(copyData);
                    } else {
                        hiHealthData2.setType(kiz.d(a2, j));
                        arrayList.add(hiHealthData2);
                    }
                }
            }
        }
        return arrayList;
    }
}
