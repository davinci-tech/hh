package com.huawei.agconnect.config.impl;

import android.content.Context;
import com.huawei.agconnect.config.ConfigReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
class m implements ConfigReader {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, String> f1713a = new ConcurrentHashMap();
    private final k b;

    public String toString() {
        return "SecurityResourcesReader{mKey=, encrypt=true}";
    }

    @Override // com.huawei.agconnect.config.ConfigReader
    public String getString(String str, String str2) {
        String str3 = this.f1713a.get(str);
        if (str3 != null) {
            return str3;
        }
        String a2 = this.b.a(str, str2);
        if (a2 == null) {
            return str2;
        }
        this.f1713a.put(str, a2);
        return a2;
    }

    m(Context context, String str) {
        this.b = new k(context, str);
    }
}
