package com.huawei.hihealth.error;

import android.util.SparseArray;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hihealth.HiHealthStatusCodes;

/* loaded from: classes.dex */
public class HiHealthError {
    private static SparseArray<String> e;

    public static int c(int i) {
        if (i != 0 && i != 1 && i != 2) {
            if (i != 3) {
                if (i != 5) {
                    if (i != 7) {
                        if (i != 50048) {
                            if (i == 100000) {
                                return 0;
                            }
                            switch (i) {
                                case 1001:
                                case 1002:
                                case 1003:
                                    break;
                                default:
                                    if (i >= 2000 || i <= 0) {
                                        return 4;
                                    }
                                    return i;
                            }
                        }
                    }
                }
            }
            return 2;
        }
        return i;
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        e = sparseArray;
        sparseArray.append(0, "success");
        e.append(1, "failed");
        e.append(2, "param invalid");
        e.append(4, "api state exception");
        e.append(5, "device exception");
        e.append(150, "device not connected");
        e.append(153, "device not supported");
        e.append(1001, "api permission exception");
        e.append(HiHealthStatusCodes.USER_OF_BETA_APP_EXCEED_RANGE, "beta scope permission exception");
        e.append(1002, "api scope exception");
        e.append(3, "data validator exception");
        e.append(7, AuthInternalPickerConstant.PARAM_ERROR);
        e.append(33, "third-party exception");
        e.append(101, "repeat error");
        e.append(102, "limit error");
        e.append(1003, "privacy user denied error");
        e.append(1005, "bluetooth not enabled");
        e.append(1006, "location permission denied");
        e.append(1007, "storage permission denied");
        e.append(1008, "HealthKit data sharing not enabled");
        e.append(1021, "Transaction too large");
        e.append(100000, "read heart rate success");
        e.append(201, "healthy living unsubscribed");
        e.append(30, "Health application need to be updated");
        e.append(31, "HiHealthKit service was disconnected");
        e.append(32, "HMS version must be later than or equal to 5.1.0.300");
        e.append(1004, "Network request failed. Please try again later");
        e.append(1022, "Failed to synchronize data");
        e.append(1023, "timeout");
        e.append(1025, "storage permission denied");
    }

    private HiHealthError() {
    }

    public static String e(int i) {
        String str = e.get(i);
        return str == null ? "unknown error" : str;
    }
}
