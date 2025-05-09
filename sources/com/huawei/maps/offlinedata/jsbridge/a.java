package com.huawei.maps.offlinedata.jsbridge;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.maps.offlinedata.utils.g;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final a f6445a = new a();

    private a() {
    }

    public static a a() {
        return f6445a;
    }

    public void a(Object obj, long j) {
        a(j, 0, obj);
    }

    public void a(int i, String str, long j) {
        a(j, i, str);
    }

    private void a(long j, int i, Object obj) {
        b.a().a("window.h5proRuntime.bridge.consumeCallback", b(j, i, obj));
    }

    private C0166a b(long j, int i, Object obj) {
        g.b("PromiseInvoker", "triggerCallback enter, callback id:" + j + " errorCode:" + i);
        C0166a c0166a = new C0166a();
        c0166a.f6446a = j;
        c0166a.b = i;
        if (obj instanceof JSONObject) {
            c0166a.c = new Gson().fromJson(obj.toString(), JsonObject.class);
        } else {
            c0166a.c = obj;
        }
        return c0166a;
    }

    /* renamed from: com.huawei.maps.offlinedata.jsbridge.a$a, reason: collision with other inner class name */
    static class C0166a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("callback_id")
        long f6446a;

        @SerializedName("err_code")
        int b;

        @SerializedName("data")
        Object c;

        C0166a() {
        }
    }
}
