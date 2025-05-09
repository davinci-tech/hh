package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nhx {
    private static final Map<Integer, Integer> b = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: nhx.1
        private static final long serialVersionUID = 3655020105415806976L;

        {
            put(1, 22101);
            put(2, 22102);
            put(3, 22103);
            put(4, 22104);
            put(5, 22105);
        }
    });

    public static List<HiHealthData> d(String str, nhv nhvVar, JSONObject jSONObject) {
        String a2 = nhvVar.a();
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("CoreSleepDetailUtil", "deviceUuid is ", a2);
        long d = d(nhvVar, jSONObject);
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 16;
            String substring = str.substring(i, i2);
            int a3 = a(substring);
            int e = e(substring);
            LogUtil.a("CoreSleepDetailUtil", "sleepStatusStr is ", substring, ", duration is ", Integer.valueOf(e), ", status ", Integer.valueOf(a3));
            if (e == 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "index is ", Integer.valueOf(i), ", duration is 0");
            } else {
                if (e % 60 != 0) {
                    ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "index is ", Integer.valueOf(i), ", duration is not multiple of 60");
                }
                if (a3 == 0) {
                    ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "index is ", Integer.valueOf(i), ", status is 0");
                } else {
                    Integer num = b.get(Integer.valueOf(a3));
                    if (num == null) {
                        ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "index is ", Integer.valueOf(i), ", status is error: ", Integer.valueOf(a3));
                    } else {
                        int i3 = e / 60;
                        if (i3 > 1440) {
                            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "index is ", Integer.valueOf(i), ", one day hours over 24, minute:", Integer.valueOf(i3));
                        } else {
                            for (int i4 = 0; i4 < i3; i4++) {
                                HiHealthData hiHealthData = new HiHealthData();
                                hiHealthData.setDeviceUuid(a2);
                                hiHealthData.setType(num.intValue());
                                hiHealthData.setStartTime(d);
                                d += 60000;
                                hiHealthData.setEndTime(d);
                                LogUtil.a("CoreSleepDetailUtil", "HiHealthData ", hiHealthData.toString());
                                arrayList.add(hiHealthData);
                            }
                        }
                    }
                }
            }
            i = i2;
        }
        return arrayList;
    }

    private static int a(String str) {
        String substring = str.substring(8, 10);
        LogUtil.a("CoreSleepDetailUtil", "statusStr is ", substring);
        try {
            return Integer.parseInt(substring, 16);
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c("CoreSleepDetailUtil", "getStatus NumberFormatException :", ExceptionUtils.d(e));
            return 0;
        }
    }

    private static int e(String str) {
        String str2 = str.substring(6, 8) + str.substring(4, 6) + str.substring(2, 4) + str.substring(0, 2);
        LogUtil.a("CoreSleepDetailUtil", "durationStr is ", str2);
        try {
            return Integer.parseInt(str2, 16);
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c("CoreSleepDetailUtil", "getDurationValue NumberFormatException :", ExceptionUtils.d(e));
            return 0;
        }
    }

    private static long d(nhv nhvVar, JSONObject jSONObject) {
        long e = nhvVar.e();
        if (!nib.a()) {
            return e;
        }
        if (nhvVar.b() == -1 || nhvVar.b() == 65535) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "validData is -1");
            return e;
        }
        try {
            long j = jSONObject.getLong("bedTime");
            if (j % 60 != 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepDetailUtil", "bedTime not whole minute");
                j = (j / 60) * 60;
            }
            return 1000 * j;
        } catch (JSONException e2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepDetailUtil", "get bedTime error: ", ExceptionUtils.d(e2));
            return e;
        }
    }
}
