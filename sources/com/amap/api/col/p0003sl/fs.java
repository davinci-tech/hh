package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes8.dex */
abstract class fs<T, V> extends ew<T, V> {
    public fs(Context context, T t) {
        super(context, t);
    }

    protected static boolean c(String str) {
        return str == null || str.equals("") || str.equals("[]");
    }
}
