package com.huawei.phoneservice.faq.base.util;

import android.content.Context;
import android.widget.Toast;

/* loaded from: classes5.dex */
public class n {
    public static void a(Context context, String str) {
        try {
            Toast makeText = Toast.makeText(context, (CharSequence) null, 0);
            makeText.setText(str);
            makeText.show();
        } catch (Throwable unused) {
            i.c("FaqToastUtils", "toast makeText exception");
        }
    }
}
