package com.huawei.hms.health;

import android.content.Context;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public final class aaca {
    private static int aab = -1;
    private static final aaba[] aaba;
    private static final List<Integer> aabb = Arrays.asList(0, 1, 2);

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0090, code lost:
    
        if (r5 != 0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x011c, code lost:
    
        if (r8 != 0) goto L72;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int aab(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aaca.aab(android.content.Context):int");
    }

    static final class aaba {
        private int aab;
        private String aaba;
        private boolean aabb;
        private String aabc;
        private String aabd;

        /* synthetic */ aaba(int i, String str, boolean z, String str2, String str3, aab aabVar) {
            this.aab = i;
            this.aaba = str;
            this.aabb = z;
            this.aabc = str2;
            this.aabd = str3;
        }
    }

    public static int aabb(Context context) {
        return aab(context);
    }

    static boolean aaba(Context context) {
        int aab2 = aab(context);
        return aabb.contains(Integer.valueOf(aab2)) || aab2 == -1;
    }

    static {
        boolean z = false;
        String str = null;
        aab aabVar = null;
        boolean z2 = true;
        aab aabVar2 = null;
        aaba = new aaba[]{new aaba(0, "handset", false, "default", null, null), new aaba(1, "pad", z, "tablet", str, aabVar), new aaba(2, DeviceTypeConsts.WATCH, z2, DeviceTypeConsts.WATCH, DeviceTypeConsts.WATCH, aabVar2), new aaba(3, "kidwatch", z, "kidwatch", str, aabVar), new aaba(4, "tv", z2, "tv", "television", aabVar2), new aaba(5, "mobiletv", z, "mobiletv", str, aabVar), new aaba(6, "glass", false, null, null, aabVar2), new aaba(7, "earphone", z, null, str, aabVar), new aaba(8, "car", false, "car", "automotive", null)};
    }
}
