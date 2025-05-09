package com.huawei.hms.network.embedded;

import com.huawei.profile.coordinator.ProfileRequestConstants;

/* loaded from: classes9.dex */
public final class j9 {
    public static boolean e(String str) {
        return str.equals("POST") || str.equals(ProfileRequestConstants.PUT_TYPE) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean d(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean c(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean b(String str) {
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals(ProfileRequestConstants.PUT_TYPE) || str.equals(ProfileRequestConstants.DELETE_TYPE) || str.equals("MOVE");
    }
}
