package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes8.dex */
abstract class go<T, V> extends ew<T, V> {
    public go(Context context, T t) {
        super(context, t);
    }

    public final T f() {
        return this.b;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public String getURL() {
        return fc.a() + "/weather/weatherInfo?";
    }
}
