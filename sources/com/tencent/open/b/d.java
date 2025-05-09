package com.tencent.open.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;

/* loaded from: classes7.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static String f11340a;
    private static String b;

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f11340a)) {
            return f11340a;
        }
        if (context == null) {
            return "";
        }
        f11340a = "";
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager != null) {
            f11340a = windowManager.getDefaultDisplay().getWidth() + "x" + windowManager.getDefaultDisplay().getHeight();
        }
        return f11340a;
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }
}
