package com.huawei.hms.aaid.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.opendevice.l;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;

/* loaded from: classes4.dex */
public class PushEncrypter {
    public static String decrypter(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : AesCbc.decrypt(str, l.b(context));
    }

    public static String encrypter(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : AesCbc.encrypt(str, l.b(context));
    }

    public static String encrypterOld(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : AesCbc.encrypt(str, l.a(context));
    }
}
