package com.huawei.hwcloudjs.core;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/* loaded from: classes9.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6197a = "JsArguments";

    public <T> String a(T t) {
        return new GsonBuilder().setExclusionStrategies(new a()).create().toJson(t);
    }

    public <T> T a(String str, Class<T> cls) {
        if (str == null || cls == null) {
            return null;
        }
        try {
            return (T) new GsonBuilder().create().fromJson(str, (Class) cls);
        } catch (JsonSyntaxException unused) {
            com.huawei.hwcloudjs.f.d.b(f6197a, "JsonSyntaxException", true);
            return null;
        }
    }
}
