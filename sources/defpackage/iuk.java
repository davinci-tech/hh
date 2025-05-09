package defpackage;

import android.util.SparseArray;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;

/* loaded from: classes7.dex */
public class iuk {
    private static final SparseArray<String> b;

    static {
        SparseArray<String> sparseArray = new SparseArray<>(19);
        b = sparseArray;
        sparseArray.put(1, "SYNC_EX: CLOUD_NO_ANS ");
        sparseArray.put(99, "SYNC_EX:INTERFACE_NOT_AVAILABLE ");
        sparseArray.put(100, "SYNC_EX:REACH_FLOW_THRESHOLD ");
        sparseArray.put(101, "SYNC_EX:CLOUD_BUSY ");
        sparseArray.put(999, "SYNC_EX:INTERFACE_NEED_UPGRADE ");
        sparseArray.put(1001, "SYNC_EX: ILLEGAL_PARAMETERS ");
        sparseArray.put(1002, "SYNC_EX: AUTH_FAILED ");
        sparseArray.put(1003, "SYNC_EX: SESSION_TIMEOUT ");
        sparseArray.put(1004, "SYNC_EX: TOKEN_EXPIRED ");
        sparseArray.put(1005, "SYNC_EX: AUTH_VERIFY_FAILED ");
        sparseArray.put(30001, "SYNC_EX: NO_HUID_FOUND ");
        sparseArray.put(30002, "SYNC_EX: NO_CUSTOM_ATTRIBUTES ");
        sparseArray.put(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "SYNC_EX: NO_SUCH_PRODUCT_ID ");
        sparseArray.put(30004, "SYNC_EX: NO_DEVICE_FOUND ");
        sparseArray.put(30005, "SYNC_EX: ILLEGAL_DEVICE ");
        sparseArray.put(30006, "SYNC_EX: USER_ATTRIBUTES_ALL_NULL ");
        sparseArray.put(30007, "SYNC_EX: NO_USER_PROFILE ");
        sparseArray.put(201001, "SYNC_EX: NOT_LOGIN_OR_ILLEGAL_HUID ");
        sparseArray.put(9999, "SYNC_EX: SYSTEM_ERROR ");
    }

    static String a(int i) {
        return b.get(i);
    }
}
