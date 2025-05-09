package defpackage;

import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes5.dex */
public final class kxe {
    public static int b(int i) {
        if (i == 0) {
            return 10;
        }
        return i;
    }

    public static int e(int i) {
        if (i == 2) {
            return 0;
        }
        return i;
    }

    public static int e(String str) {
        if ("beating".equals(str)) {
            return 10;
        }
        if ("timer".equals(str) || "run_time".equals(str)) {
            return 1;
        }
        if ("run_distance".equals(str)) {
            return 0;
        }
        return JsUtil.SCORE.equals(str) ? 101 : -1;
    }

    public static String c(int i) {
        return i != 0 ? i != 1 ? i != 10 ? i != 101 ? "" : JsUtil.SCORE : "beating" : "run_time" : "run_distance";
    }
}
