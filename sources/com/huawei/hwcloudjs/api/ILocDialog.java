package com.huawei.hwcloudjs.api;

import android.app.Dialog;
import android.content.Context;
import com.huawei.hwcloudjs.core.extkit.IExtKit;

/* loaded from: classes9.dex */
public interface ILocDialog extends IExtKit {

    public interface DialogResult {
        void onNeg();

        void onPos();
    }

    Dialog genDialog(Context context, String str, DialogResult dialogResult);
}
