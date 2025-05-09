package com.tencent.open.b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class c implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap<String, String> f11339a;

    public c(Bundle bundle) {
        this.f11339a = new HashMap<>();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.f11339a.put(str, bundle.getString(str));
            }
        }
    }

    public c(HashMap<String, String> hashMap) {
        this.f11339a = new HashMap<>(hashMap);
    }

    public String toString() {
        return "BaseData{time=" + this.f11339a.get("time") + ", name=" + this.f11339a.get("interface_name") + '}';
    }
}
