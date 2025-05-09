package com.alipay.android.phone.mrpc.core;

import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* loaded from: classes7.dex */
public final class o extends t {
    public String b;
    public byte[] c;
    public boolean g;
    public ArrayList<Header> e = new ArrayList<>();
    public Map<String, String> f = new HashMap();
    public String d = RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED;

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.b, this.e);
    }

    public final int hashCode() {
        Map<String, String> map = this.f;
        int hashCode = (map == null || !map.containsKey("id")) ? 1 : this.f.get("id").hashCode() + 31;
        String str = this.b;
        return (hashCode * 31) + (str == null ? 0 : str.hashCode());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        byte[] bArr = this.c;
        if (bArr == null) {
            if (oVar.c != null) {
                return false;
            }
        } else if (!bArr.equals(oVar.c)) {
            return false;
        }
        String str = this.b;
        String str2 = oVar.b;
        if (str == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    public final boolean e() {
        return this.g;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final String c() {
        return this.d;
    }

    public final byte[] b() {
        return this.c;
    }

    public final String b(String str) {
        Map<String, String> map = this.f;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public final void a(byte[] bArr) {
        this.c = bArr;
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(String str) {
        this.d = str;
    }

    public final String a() {
        return this.b;
    }

    public o(String str) {
        this.b = str;
    }
}
