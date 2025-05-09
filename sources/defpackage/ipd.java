package defpackage;

import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ipd {
    private static Map<Integer, String> e;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(0, "success");
        e.put(1, "failed");
        e.put(4, "api state exception");
        e.put(5, "device exception");
        e.put(150, "device not connected");
        e.put(2, "param invalid");
        e.put(3, "data validator error");
        e.put(7, AuthInternalPickerConstant.PARAM_ERROR);
        e.put(25, "screen locked error");
        e.put(1001, "permission exception");
        e.put(Integer.valueOf(HiHealthStatusCodes.USER_OF_BETA_APP_EXCEED_RANGE), "beta scope permission exception");
        e.put(1002, "scope exception");
        e.put(1003, "user privacy denied error");
        e.put(1004, "network exception");
        e.put(1005, "bluetooth not enabled");
        e.put(1006, "location permission denied");
        e.put(1007, "storage permission denied");
        e.put(1008, "HealthKit data sharing not enabled");
        e.put(1009, "token expired");
        e.put(1021, "Transaction too large");
        e.put(201, "healthy living unsubscribed");
        e.put(30, "Health application need to be updated");
        e.put(31, "HiHealthKit service was disconnected");
        e.put(32, "HMS version must be later than or equal to 5.1.0.300");
        e.put(1022, "Failed to synchronize data");
        e.put(153, "device is not supported");
        e.put(1023, "timeout");
        e.put(1024, "business not need to be executed");
        e.put(1025, "device storage permission denied");
    }

    public static String b(int i) {
        String str = e.get(Integer.valueOf(i));
        return str == null ? "UNKNOWN_ERROR" : str;
    }

    public static List c(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(i));
        return arrayList;
    }
}
