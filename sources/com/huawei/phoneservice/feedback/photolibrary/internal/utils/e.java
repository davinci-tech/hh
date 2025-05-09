package com.huawei.phoneservice.feedback.photolibrary.internal.utils;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import com.huawei.phoneservice.faq.base.util.t;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.uhy;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class e {
    public static final e c = new e();

    @JvmStatic
    public static final boolean cez_(EditText editText) {
        int height;
        uhy.e((Object) editText, "");
        int scrollY = editText.getScrollY();
        Layout layout = editText.getLayout();
        if (layout == null || (height = layout.getHeight() - ((editText.getHeight() - editText.getCompoundPaddingTop()) - editText.getCompoundPaddingBottom())) == 0) {
            return false;
        }
        return scrollY > 0 || scrollY < height - 1;
    }

    @JvmStatic
    public static final void a(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context);
        hwColumnSystem.e(1);
        t.cdt_(view, hwColumnSystem.j());
    }

    private e() {
    }
}
