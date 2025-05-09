package com.huawei.hms.network.embedded;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.SecurityBase64Utils;
import com.huawei.hms.network.embedded.y1;
import java.io.IOException;

/* loaded from: classes9.dex */
public final class w1 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5553a = "EncryptKey";
    public static byte[] b = null;
    public static final String c = "fruit";
    public static final String d = "APPLE";

    public static byte[] a(boolean z) throws IOException {
        byte[] bArr;
        synchronized (w1.class) {
            byte[] bArr2 = b;
            if (bArr2 == null || bArr2.length == 0) {
                b = a(new PLSharedPreferences(ContextHolder.getAppContext(), c), z);
            }
            bArr = b;
        }
        return bArr;
    }

    public static byte[] a(PLSharedPreferences pLSharedPreferences, boolean z) throws IOException {
        byte[] decode;
        String string = pLSharedPreferences.getString(d, "");
        if (!TextUtils.isEmpty(string)) {
            decode = SecurityBase64Utils.decode(x1.a(string), 0);
        } else {
            if (!z) {
                throw new y1.b("The workKey does not exist");
            }
            decode = x1.a();
            String b2 = x1.b(SecurityBase64Utils.encodeToString(decode, 0));
            SharedPreferences.Editor edit = pLSharedPreferences.edit();
            edit.putString(d, b2);
            edit.apply();
            Logger.i(f5553a, "generate work key success");
        }
        if (decode != null && decode.length != 0) {
            return decode;
        }
        if (!z) {
            throw new y1.b("WorkKey is invalid");
        }
        Logger.w(f5553a, "WorkKey is Empty!");
        SharedPreferences.Editor edit2 = pLSharedPreferences.edit();
        edit2.putString(d, "");
        edit2.apply();
        return a(pLSharedPreferences, true);
    }
}
