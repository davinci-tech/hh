package com.huawei.hms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class mac {
    /* JADX WARN: Removed duplicated region for block: B:4:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.os.Bundle b(android.os.Bundle r2, java.lang.String r3, android.os.Parcelable r4) {
        /*
            if (r2 == 0) goto L21
            java.lang.String r0 = "map_state"
            android.os.Bundle r2 = r2.getBundle(r0)     // Catch: java.lang.Exception -> L9
            goto L22
        L9:
            r2 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getOrgMapState exception "
            r0.<init>(r1)
            java.lang.String r2 = r2.getMessage()
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            java.lang.String r0 = "MapStateUtils"
            android.util.Log.e(r0, r2)
        L21:
            r2 = 0
        L22:
            if (r2 != 0) goto L29
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
        L29:
            java.lang.Class<com.huawei.hms.maps.internal.mac> r0 = com.huawei.hms.maps.internal.mac.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r2.setClassLoader(r0)
            r2.putParcelable(r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.maps.internal.mac.b(android.os.Bundle, java.lang.String, android.os.Parcelable):android.os.Bundle");
    }

    public static void a(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(mac.class.getClassLoader());
        bundle.putBundle("map_state", b(bundle, str, parcelable));
    }

    private static <T extends Parcelable> T a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(mac.class.getClassLoader());
        Bundle bundle2 = (Bundle) bundle.get("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(mac.class.getClassLoader());
        return (T) bundle2.getParcelable(str);
    }

    public static Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle == null) {
            return bundle2;
        }
        Parcelable a2 = a(bundle, "HuaweiMapOptions");
        if (a2 != null) {
            a(bundle2, "HuaweiMapOptions", a2);
        }
        Parcelable a3 = a(bundle, "StreetViewOptions");
        if (a3 != null) {
            a(bundle2, "StreetViewOptions", a3);
        }
        Parcelable a4 = a(bundle, "CameraState");
        if (a4 != null) {
            a(bundle2, "CameraState", a4);
        }
        if (bundle.containsKey("position")) {
            bundle2.putString("position", bundle.getString("position"));
        }
        return bundle2;
    }

    private mac() {
    }
}
