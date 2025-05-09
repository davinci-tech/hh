package com.tencent.open.b;

import com.tencent.open.utils.i;

/* loaded from: classes7.dex */
public class f {
    public static int a(String str) {
        int a2;
        if (com.tencent.open.utils.g.a() == null || (a2 = i.a(com.tencent.open.utils.g.a(), str).a("Common_BusinessReportFrequency")) == 0) {
            return 100;
        }
        return a2;
    }

    public static int a() {
        int a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_HttpRetryCount");
        if (a2 == 0) {
            return 2;
        }
        return a2;
    }
}
