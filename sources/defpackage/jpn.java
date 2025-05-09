package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jpn {
    private static int c;
    private static final Object e = new Object();

    public static void a(int i, boolean z) {
        synchronized (e) {
            if (z) {
                c = i | c;
            } else {
                c = (~i) & c;
            }
            LogUtil.a("DeviceDatabaseCacheUtil", "change result : ", Integer.toBinaryString(c));
        }
    }

    public static boolean e(int i) {
        boolean z;
        synchronized (e) {
            LogUtil.a("DeviceDatabaseCacheUtil", "isTagSuccess result : ", Integer.toBinaryString(c));
            z = (c & i) == i;
        }
        return z;
    }
}
