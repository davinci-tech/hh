package com.huawei.watchface;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.huawei.watchface.utils.ColorPickerUtil;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes9.dex */
public class ct {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10975a = "ct";

    public static int a(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            HwLog.w(f10975a, "obtainWidgetColor -- bitmap or rect is null!");
            return -1;
        }
        int a2 = ColorPickerUtil.a(bitmap, rect);
        HwLog.i(f10975a, "pickedColor ===  " + a2);
        return a2;
    }
}
