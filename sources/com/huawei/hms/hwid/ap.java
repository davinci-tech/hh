package com.huawei.hms.hwid;

import java.util.Collection;

/* loaded from: classes4.dex */
public class ap {
    public static Boolean a(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static Boolean b(Collection collection) {
        return Boolean.valueOf(!a(collection).booleanValue());
    }
}
