package com.tencent.open.b;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class g {
    public static void a(String str) {
    }

    public static void a(String str, List<Serializable> list) {
    }

    static void a() {
        Context a2 = com.tencent.open.utils.g.a();
        if (a2 == null) {
            return;
        }
        a2.deleteDatabase("sdk_report.db");
    }

    public static List<Serializable> b(String str) {
        return Collections.synchronizedList(new ArrayList());
    }
}
