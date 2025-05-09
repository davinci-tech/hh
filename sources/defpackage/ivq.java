package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiNoonSleepInfo;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ivq {
    public static Map<String, Long> e(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return new HashMap(0);
        }
        HashMap hashMap = new HashMap(2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) != null) {
                HiHealthData hiHealthData = list.get(i);
                if (hashMap.get("core_sleep_start_wake_key") == null && (hiHealthData.getType() == 22104 || hiHealthData.getType() == 22106)) {
                    hashMap.put("core_sleep_start_wake_key", Long.valueOf(hiHealthData.getStartTime()));
                }
                if (hashMap.get("core_sleep_start_time_key") == null && hiHealthData.getType() != 22105 && hiHealthData.getType() != 22003 && hiHealthData.getType() != 22104 && hiHealthData.getType() != 22106) {
                    hashMap.put("core_sleep_start_time_key", Long.valueOf(hiHealthData.getStartTime()));
                }
                int i2 = i + 1;
                if (size > i2 && list.get(i2) != null) {
                    HiHealthData hiHealthData2 = list.get(i2);
                    if (hiHealthData2.getType() != 22106 && hiHealthData2.getType() != 22003 && hiHealthData2.getType() != 22104 && hiHealthData2.getType() != 22105) {
                        hashMap.put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
                    }
                    if (hiHealthData2.getType() == 22106 || hiHealthData2.getType() == 22104) {
                        hashMap.put("core_sleep_end_wake_key", Long.valueOf(hiHealthData2.getEndTime()));
                    }
                }
            }
        }
        return hashMap;
    }

    public static List<HiNoonSleepInfo.TimeInterval> a(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return new ArrayList();
        }
        ArrayList<Map> arrayList = new ArrayList(10);
        HashMap hashMap = new HashMap(16);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                HiHealthData hiHealthData = list.get(i);
                if (hashMap.get("core_sleep_start_time_key") == null) {
                    hashMap.put("core_sleep_start_time_key", hiHealthData);
                }
                int i2 = i + 1;
                if (i2 < list.size()) {
                    if (list.get(i2) != null) {
                        hashMap.put("core_sleep_end_time_key", hiHealthData);
                        if (list.get(i2).getStartTime() - hiHealthData.getEndTime() >= 60000) {
                            hashMap.put("core_sleep_end_time_key", hiHealthData);
                            arrayList.add(hashMap);
                            hashMap = new HashMap(16);
                        }
                    }
                }
                if (i == list.size() - 1) {
                    if (hashMap.get("core_sleep_start_time_key") == null) {
                        hashMap.put("core_sleep_start_time_key", hiHealthData);
                    }
                    hashMap.put("core_sleep_end_time_key", hiHealthData);
                    arrayList.add(hashMap);
                }
            }
        }
        if (HiCommonUtil.d(arrayList)) {
            return new ArrayList();
        }
        ArrayList arrayList2 = new ArrayList(10);
        for (Map map : arrayList) {
            if (map.get("core_sleep_start_time_key") != null || map.get("core_sleep_end_time_key") != null) {
                arrayList2.add(new HiNoonSleepInfo.TimeInterval(((HiHealthData) map.get("core_sleep_start_time_key")).getStartTime(), ((HiHealthData) map.get("core_sleep_end_time_key")).getEndTime(), HiDateUtil.d(list.get(0).getTimeZone())));
            }
        }
        return arrayList2;
    }
}
