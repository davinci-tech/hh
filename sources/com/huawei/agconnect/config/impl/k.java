package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.agconnect.config.AesDecrypt;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private final AesDecrypt f1712a;

    public String a(String str, String str2) {
        return this.f1712a.decrypt(str, str2);
    }

    private String a(Context context, String str) {
        String a2 = l.a(context, str, "agc_plugin_", "crypto");
        if (a2 == null) {
            return null;
        }
        try {
            return new String(Hex.decodeHexString(a2), "utf-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            Log.e("ReaderStrategy", "UnsupportedEncodingException" + e.getMessage());
            return null;
        }
    }

    public k(Context context, String str) {
        this.f1712a = !TextUtils.isEmpty(a(context, str)) ? new h(context, str) : new g(context, str);
    }
}
