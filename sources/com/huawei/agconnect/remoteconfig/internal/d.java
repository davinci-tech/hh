package com.huawei.agconnect.remoteconfig.internal;

import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private c f1820a;
    private c b;

    byte[] e(String str) {
        c cVar;
        c cVar2 = this.f1820a;
        if (cVar2 == null || !cVar2.containKey(str)) {
            c cVar3 = this.b;
            if (cVar3 == null || !cVar3.containKey(str)) {
                return AGConnectConfig.DEFAULT.BYTE_ARRAY_VALUE;
            }
            cVar = this.b;
        } else {
            cVar = this.f1820a;
        }
        return cVar.getValueAsByteArray(str);
    }

    String d(String str) {
        c cVar;
        c cVar2 = this.f1820a;
        if (cVar2 == null || !cVar2.containKey(str)) {
            c cVar3 = this.b;
            if (cVar3 == null || !cVar3.containKey(str)) {
                return "";
            }
            cVar = this.b;
        } else {
            cVar = this.f1820a;
        }
        return cVar.getValueAsString(str);
    }

    long c(String str) {
        c cVar;
        c cVar2 = this.f1820a;
        if (cVar2 == null || !cVar2.containKey(str)) {
            c cVar3 = this.b;
            if (cVar3 == null || !cVar3.containKey(str)) {
                return 0L;
            }
            cVar = this.b;
        } else {
            cVar = this.f1820a;
        }
        return cVar.getValueAsLong(str).longValue();
    }

    double b(String str) {
        c cVar;
        c cVar2 = this.f1820a;
        if (cVar2 == null || !cVar2.containKey(str)) {
            c cVar3 = this.b;
            if (cVar3 == null || !cVar3.containKey(str)) {
                return 0.0d;
            }
            cVar = this.b;
        } else {
            cVar = this.f1820a;
        }
        return cVar.getValueAsDouble(str).doubleValue();
    }

    boolean a(String str) {
        c cVar;
        c cVar2 = this.f1820a;
        if (cVar2 == null || !cVar2.containKey(str)) {
            c cVar3 = this.b;
            if (cVar3 == null || !cVar3.containKey(str)) {
                return false;
            }
            cVar = this.b;
        } else {
            cVar = this.f1820a;
        }
        return cVar.getValueAsBoolean(str).booleanValue();
    }

    Map<String, Object> a() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.b.c());
        hashMap.putAll(this.f1820a.c());
        return hashMap;
    }

    d(c cVar, c cVar2) {
        this.f1820a = cVar;
        this.b = cVar2;
    }
}
