package com.huawei.operation.h5pro.jsmodules.compact;

import android.content.Context;
import android.widget.Toast;
import com.huawei.operation.adapter.ToastCallback;

/* loaded from: classes5.dex */
public class ToastCompact implements ToastCallback {
    private Context mContext;

    public ToastCompact(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.operation.adapter.ToastCallback
    public void onToast(String str, String str2) {
        Toast.makeText(this.mContext, str, 0).show();
    }
}
