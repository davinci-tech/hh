package com.huawei.hms.network.file.a.k.b.j;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.SecurityBase64Utils;
import com.huawei.hms.network.embedded.w1;
import com.huawei.hms.network.file.a.k.b.j.b;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* loaded from: classes4.dex */
final class a {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f5615a;

    static byte[] a(boolean z) {
        byte[] bArr;
        synchronized (a.class) {
            byte[] bArr2 = f5615a;
            if (bArr2 == null || bArr2.length == 0) {
                f5615a = a(new PLSharedPreferences(ContextHolder.getAppContext(), "fmfruit"), z);
            }
            bArr = f5615a;
        }
        return bArr;
    }

    private static byte[] a(PLSharedPreferences pLSharedPreferences, boolean z) {
        byte[] decode;
        String string = pLSharedPreferences.getString("animal", "");
        String rootKeyHex = c.a().getRootKeyHex();
        if (!TextUtils.isEmpty(string)) {
            decode = SecurityBase64Utils.decode(AesGcm.decrypt(string, rootKeyHex), 0);
        } else {
            if (!z) {
                FLogger.e(w1.f5553a, "getKeyImpl KeyNotFoundException");
                throw new b.C0146b("The workKey does not exist");
            }
            decode = EncryptUtil.generateSecureRandom(16);
            String encrypt = AesGcm.encrypt(SecurityBase64Utils.encodeToString(decode, 0), rootKeyHex);
            SharedPreferences.Editor edit = pLSharedPreferences.edit();
            edit.putString("animal", encrypt);
            edit.apply();
            FLogger.i(w1.f5553a, "generate work key success", new Object[0]);
        }
        if (decode != null && decode.length != 0) {
            return decode;
        }
        if (!z) {
            throw new b.C0146b("WorkKey is invalid");
        }
        FLogger.w(w1.f5553a, "WorkKey is Empty!", new Object[0]);
        SharedPreferences.Editor edit2 = pLSharedPreferences.edit();
        edit2.putString("animal", "");
        edit2.apply();
        return a(pLSharedPreferences, true);
    }

    a() {
    }
}
