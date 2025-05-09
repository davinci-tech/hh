package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class fgc {
    public static ffg a() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_RunningPaceZone_Config");
        if (userPreference == null) {
            return null;
        }
        String value = userPreference.getValue();
        LogUtil.a("Track_PaceZoneUtils", "mPaceZoneConfig:", value);
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        ffg ffgVar = new ffg();
        ffgVar.c(value);
        return ffgVar;
    }

    public static ffg b() {
        ffg a2 = a();
        return a2 == null ? new ffg() : a2;
    }

    public static int[] d() {
        return b().f();
    }

    public static int[] e() {
        ffg a2 = a();
        return a2 == null ? new int[0] : a2.f();
    }

    public static int[] c(ffg ffgVar, List<koh> list, long j) {
        int[] iArr = new int[5];
        if (ffgVar == null || koq.b(list)) {
            return null;
        }
        d(list, iArr, sqb.d(list, j), ffgVar);
        if (!e(iArr)) {
            return iArr;
        }
        LogUtil.h("Track_PaceZoneUtils", "hasInvalidValue requestPaceInterDuration");
        return null;
    }

    private static void d(List<koh> list, int[] iArr, int i, ffg ffgVar) {
        int d;
        if (list == null) {
            LogUtil.b("Track_PaceZoneUtils", "paceList is null");
            return;
        }
        if (ffgVar == null) {
            return;
        }
        Iterator<koh> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() != null && (d = ffgVar.d(r0.a())) != -1) {
                iArr[d] = iArr[d] + i;
            }
        }
    }

    private static boolean e(int[] iArr) {
        if (iArr == null) {
            return true;
        }
        boolean z = true;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == -1) {
                z2 = true;
            }
            if (i != 0) {
                z = false;
            }
        }
        return z || z2;
    }
}
