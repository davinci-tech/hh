package com.huawei.watchface;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.DensityUtil;

/* loaded from: classes7.dex */
public final class ds {

    /* renamed from: a, reason: collision with root package name */
    private static final Handler f10994a = new Handler(Looper.getMainLooper());
    private static Toast b;

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        b(str, 0);
    }

    private static void b(final CharSequence charSequence, final int i) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c(charSequence, i);
        } else {
            f10994a.post(new Runnable() { // from class: com.huawei.watchface.ds.1
                @Override // java.lang.Runnable
                public void run() {
                    ds.c(charSequence, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(CharSequence charSequence, int i) {
        Toast toast = b;
        if (toast != null) {
            toast.cancel();
        }
        Context a2 = Environment.a();
        if (a2 != null) {
            Toast makeText = Toast.makeText(a2, charSequence, i);
            b = makeText;
            makeText.show();
        }
    }

    private static void a(int i, int i2) {
        b(DensityUtil.getStringById(i), i2);
    }

    public static void a(int i) {
        a(i, 0);
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        b(str, 1);
    }
}
