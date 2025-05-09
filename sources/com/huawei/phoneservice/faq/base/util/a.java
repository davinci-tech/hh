package com.huawei.phoneservice.faq.base.util;

import android.app.Dialog;

/* loaded from: classes5.dex */
public class a {
    public static void cdd_(Dialog dialog) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        dialog.show();
    }
}
